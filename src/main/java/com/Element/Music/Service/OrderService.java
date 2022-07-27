package com.Element.Music.Service;

import com.Element.Music.IdProducer.OrderId;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.TradeDAO.ConsumerOrder;
import java.util.List;

public interface OrderService {
    OrderId generateOrderId ();
    int addNewOrder(Long songId, Long consumerId);
//    List<ConsumerOrder> getOrderByConsumerId(Long consumerId);
    List<ConsumerOrder> getAllOrder();
//    List<Song> getSongIdByConsumerId(Long consumerId);
}
