package com.lijie.websocket.controlelr;

import com.lijie.websocket.dto.MessageDto;
import com.lijie.websocket.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 * 第一种：三个客户端分别向服务器发送消息。
 * 启动项目后，分别访问下面的三个url可以拿到三个客户端，就可以给服务器发送消息了。
 * http://localhost:8080/socket/getClient?userId=98
 * http://localhost:8080/socket/getClient?userId=99
 * http://localhost:8080/socket/getClient?userId=100
 *
 * 第二种：通过调用下面的接口就可以实现服务器给客户端发送消息了
 * 请求URL：http://localhost:8080/socket/push
 * 请求方式：POST
 * 请求参数：分别执行下面的四个参数，观察98，99和100这两个客户端的页面情况
 * {
 *     "message":"哈哈哈"
 * }
 * {
 *     "message":"嘿嘿嘿",
 *     "clientId":"100"
 * }
 * {
 *     "message":"呵呵呵",
 *     "clientId":"99"
 * }
 * {
 *     "message":"略略路",
 *     "clientId":"98"
 * }
 *
 * @author: lijie
 * @date: 2021/7/1 9:06
 * @version: V1.0
 */
@Controller
@RequestMapping("/socket")
public class SystemController {

    //推送数据接口
    @ResponseBody
    @PostMapping("/push")
    public Map pushToWeb(@RequestBody MessageDto messageDto) {
        Map<String,Object> result = new HashMap<>();
        try {
            WebSocketServer.sendInfo(messageDto.getMessage(), messageDto.getClientId());
            result.put("code", messageDto.getClientId());
            result.put("msg", messageDto.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //通过传入不同的userId来跳转到不同的界面，或者说是不同的客户端
    @GetMapping("/getClient")
    public ModelAndView socket(String userId) {
        if("99".equals(userId)){
            ModelAndView mav = new ModelAndView("redirect:/99Client.html");//重定向到index界面
            mav.addObject("userId", userId);
            return mav;
        }else if("100".equals(userId)){
            ModelAndView mav = new ModelAndView("redirect:/100Client.html");//重定向到index界面
            mav.addObject("userId", userId);
            return mav;
        }else{
            ModelAndView mav = new ModelAndView("redirect:/index.html");//重定向到index界面
            mav.addObject("userId", userId);
            return mav;
        }
    }
}
