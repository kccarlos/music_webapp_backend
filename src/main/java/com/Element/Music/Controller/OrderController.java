package com.Element.Music.Controller;


import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.TradeDAO.ConsumerOrder;
import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Service.OrderService;
import com.alibaba.fastjson.JSONObject;
import org.apache.maven.shared.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addOrder(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String consumerId = req.getParameter("consumerId").trim();
        String songId = req.getParameter("songId").trim();
        if (consumerId.equals("") || songId.equals("")){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "歌曲或用户为空");
            return jsonObject;
        }
        //TODO: use enum for "res"
        int res = orderService.addNewOrder(Long.parseLong(songId), Long.parseLong(consumerId));
        if (res == -1){
            jsonObject.put("code", 2);
            jsonObject.put("msg", "余额不足");
        } else if (res == -2){
            jsonObject.put("code", 3);
            jsonObject.put("msg", "无法新增订单");
        } else if (res == 1){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "交易成功");
        }
        return jsonObject;
    }

//    @RequestMapping(value = "/getOrderById", method = RequestMethod.GET)
//    public Object orderOfConsumerId(HttpServletRequest req) {
//        String consumerId = req.getParameter("consumerId");
//        return orderService.getOrderByConsumerId(Long.parseLong(consumerId));
//    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object allOrder() {
        return orderService.getAllOrder();
    }

//    @RequestMapping(value = "/getOrderSong", method = RequestMethod.GET)
//    public Object orderSongOfConsumerId(HttpServletRequest req) {
//        String consumerId = req.getParameter("consumerId");
//        return orderService.getSongIdByConsumerId(Long.parseLong(consumerId));
//    }

}
