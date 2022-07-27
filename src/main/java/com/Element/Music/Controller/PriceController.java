package com.Element.Music.Controller;


import com.Element.Music.Emun.Genre;
import com.Element.Music.Emun.Sex;
import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.Element.Music.Service.PriceService;
import com.alibaba.fastjson.JSONObject;
import org.apache.maven.shared.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.Element.Music.Service.PriceService;
import com.Element.Music.Model.DAO.TradeDAO.Purse;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.DoubleToLongFunction;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/price")

public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) { this.priceService = priceService; }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addPrice(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String originalPrice = req.getParameter("originalPrice").trim();
        String rate = req.getParameter("rate").trim();

        if (id.equals("")) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "用户名为空");
            return jsonObject;
        }

        Price addPriceRes = priceService.initializePrice(Long.parseLong(id), StringUtils.isNotBlank(originalPrice) ?
                        Double.parseDouble(originalPrice) : 0.0,
                StringUtils.isNotBlank(rate) ? Double.parseDouble(rate) : 0.15);
        if (addPriceRes != null) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "增加歌曲价格成功");
        } else {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "增加歌曲价格失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getPriceById", method = RequestMethod.GET)
    public Object PriceOfSongId(HttpServletRequest req) {
        String id = req.getParameter("id");
        return priceService.getDisplayPriceById(Long.parseLong(id));
    }
    
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public Object allPrice() {
        return priceService.getAllPrice();
    }

}
