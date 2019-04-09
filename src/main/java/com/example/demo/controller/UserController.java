package com.example.demo.controller;

import com.example.demo.bean.ResponseMap;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class UserController {
    //依赖注入，自动创建指定类的对象
    @Autowired
    UserService userService;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseMap login(String username,String password) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String url= String.valueOf(request.getRequestURL());
        HttpServletResponse response = servletRequestAttributes.getResponse();
        if (response!=null){
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        }else {
            System.out.println("获取response失败");
        }
        //调用dao层
        ResponseMap responseMap=new ResponseMap();
        if (username==null||username.equals("")){
            responseMap.setCode(-1);
            responseMap.setMsg("用户名不能为空");
            return  responseMap;
        }
        User u = userService.getUser(username);
        if(u==null){
            responseMap.setCode(-1);
            responseMap.setMsg("用户不存在！");
            return  responseMap;
        }
        if (u.getPassWord().equals(password)){
            responseMap.setCode(0);
            responseMap.setMsg("登陆成功");
            HashMap<String,User> res=new HashMap<>();
            res.put("userInfo",u);
            responseMap.setRes(res);
            HttpSession session=request.getSession();
            session.setAttribute("userInfo",u);
            return responseMap;
        }
        responseMap.setCode(-1);
        responseMap.setMsg("密码错误");
        return responseMap;
    }
}
