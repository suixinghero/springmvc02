package com.springmvc.controller;

import com.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xujin
 * @package-name com.springmvc.controller
 * @createtime 2019-09-09 18:32
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping(value = "/testString")
    public String testString(String username){
        System.out.println("testString执行成功..."+username);
        return "success";
    }

    /**
     * 不带返回值
     * 默认是: /springmvc02/WEB-INF/pages/user/testVoid.jsp
     */
    @RequestMapping(value = "/testVoid")
    public void  testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testString执行成功...");
        request.getRequestDispatcher("../WEB-INF/pages/success.jsp").forward(request,response);
    }

    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
        System.out.println("testModelAndView执行成功...");
        ModelAndView modelAndView=new ModelAndView();
        User user=new User();
        user.setUsername("李四");
        user.setPassword("123");
        user.setAge(21);
        modelAndView.setViewName("success");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "/testForWardOrRedirect")
    public String testForWardOrRedirect() {
        System.out.println("testForWardOrRedirect执行成功...");
        return "forward:/WEB-INF/pages/success.jsp";
    }

    @RequestMapping(value = "/testPut",method = RequestMethod.GET)
    public String testPut() {
        System.out.println("testPut执行了...");
        return "success";
    }

    @RequestMapping(value = "/testAjax")
    public @ResponseBody User testAjax(User user) {
        System.out.println("testAjax执行了...");
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/returnJson")
    public @ResponseBody User returnJson() {
        User user=new User();
        user.setUsername("张三");
        user.setPassword("123");
        user.setAge(21);
        return user;
    }
}
