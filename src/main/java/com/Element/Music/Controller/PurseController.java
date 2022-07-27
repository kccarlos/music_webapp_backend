package com.Element.Music.Controller;


import com.Element.Music.Exception.ConsumerException;
import com.Element.Music.Exception.PurseException;
import com.Element.Music.Model.DAO.UserDAO.Consumer;
import com.Element.Music.Service.PurseService;
import com.Element.Music.Model.DAO.TradeDAO.Purse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/purse")

public class PurseController {
    private final PurseService purseService;

    public PurseController(PurseService purseService) { this.purseService = purseService; }

    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public Object BalanceOfUserId(HttpServletRequest req) {
        String id = req.getParameter("id");
        return purseService.getBalanceByID(Long.parseLong(id));
    }

    @ResponseBody
    @RequestMapping(value = "/addBalance", method = RequestMethod.POST)
    public Object addBalance(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String addValue = req.getParameter("addValue").trim();
        if (id.equals("") || addValue.equals("")) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "用户名或增值额为空");
            return jsonObject;
        }
        Purse addBalanceRes = purseService.addBalanceById(Long.parseLong(id), Double.parseDouble(addValue));
        if (addBalanceRes != null) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "增值成功");
        } else {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "增值失败");
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/withdrawBalance", method = RequestMethod.POST)
    public Object withdrawBalance(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String withdrawValue = req.getParameter("withdrawValue").trim();
        if (id.equals("") || withdrawValue.equals("")) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "用户名或支付额为空");
            return jsonObject;
        }
        Purse addBalanceRes = purseService.withdrawBalanceById(Long.parseLong(id), Double.parseDouble(withdrawValue));
        if (addBalanceRes != null) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "支付成功");
        } else {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "支付失败");
        }
        return jsonObject;
    }
} 
