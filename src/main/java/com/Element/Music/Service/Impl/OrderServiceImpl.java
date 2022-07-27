package com.Element.Music.Service.Impl;

import com.Element.Music.Model.DAO.UserDAO.Consumer;
import com.Element.Music.Repository.TradeRepository.OrderRepository;
import com.Element.Music.Repository.TradeRepository.PurseRepository;
import com.Element.Music.Model.DAO.TradeDAO.*;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Service.*;
import org.springframework.stereotype.Service;
import com.Element.Music.IdProducer.OrderId;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final PriceService priceService;

    private final ConsumerService consumerService;

    private final PurseService purseService;

    public OrderServiceImpl(OrderRepository orderRepository, PriceService priceService, ConsumerService consumerService, PurseService purseService) throws NoSuchAlgorithmException {

        this.orderRepository = orderRepository;

        this.priceService = priceService;

        this.consumerService = consumerService;

        this.purseService = purseService;

    }

    @Override
    public OrderId generateOrderId (){
        Random rd = new Random();
        long workerId = rd.nextInt(31), datacenterId = rd.nextInt(31); //工作id，数据中心ID

        //获取当前ip,生成工作id
        String ip = "47.97.121.189";
        if(ip != null) {
            workerId = Long.parseLong(ip.replaceAll("\\.", ""));
            workerId = workerId % 32; //因为占用5位，模32
        }
        return new OrderId(workerId, datacenterId);
    }

    @Override
    public int addNewOrder(Long songId, Long consumerId) {
        Double consumerBalance = purseService.getBalanceByID(consumerId);
        Price songPrice = priceService.getPriceById(songId);
        if (songPrice.getShowPrice() > consumerBalance) {
            return -1;
        } //Error Code -1: 余额不够
        OrderId orderId = generateOrderId();
        Consumer consumer = consumerService.getConsumerByID(consumerId);

        ConsumerOrder order = new ConsumerOrder();
        order.setConsumer(consumer);
        order.setDiscount(0.0);
        order.setPayPrice(songPrice.getShowPrice());
        order.setPrice(songPrice);
//        order.setSong(songService.getSongById(songId));
        order.setOrderCode(orderId.nextId());
        ConsumerOrder returnOrder = orderRepository.save(order);
        Purse consumerPurse = purseService.withdrawBalanceById(consumerId, songPrice.getShowPrice());
        if(consumerPurse == null) return -1; //用户余额不够
//        Song purchasedSong = songPrice.getSong();
        consumerService.addToMySong(consumerId, songId);
        return 1; //新增订单成功
    }


//    @Override
//    public List<ConsumerOrder> getOrderByConsumerId(Long consumerId){
//        return orderRepository.findAllByConsumerIdAndDeletedIsFalse(consumerId);
//    }
//
//    @Override
//    public List<Song> getSongIdByConsumerId(Long consumerId){
//        List<ConsumerOrder> orderRes = orderRepository.findAllByConsumerIdAndDeletedIsFalse(consumerId);
//        List<Song> returnSong = new ArrayList<>();
//
//        for(ConsumerOrder consumerOrder: orderRes){
//            returnSong.add(priceService.getSongById(consumerOrder.getSongId()));
//        }
//
//        return returnSong;
//    }

    public List<ConsumerOrder> getAllOrder(){
        return orderRepository.findAll();
    }
}
