package com.example.com_guigu_service1.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.com_guigu_service1.bean.User;
import com.example.com_guigu_service1.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class Mysql {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/selectFromMysql")
    @SentinelResource("selectFromMysql")
    public String selectFromMysql(Map<String, Object> map ) {
        List<User> list = this.userDao.findAll();
        for (User users : list) {
            System.out.println(users);
        }
        map.put("hello", "HELLO123");
        map.put("datas", list);
        return "mysql_show";
    }


    // 127.0.0.1:8081/insertToMysql?name=lixiaoyao&addr=addr1&remark=remark1&country=country1&age=19
    @RequestMapping(value = "/insertToMysql", params = {"name","age","remark", "addr", "country"},method = RequestMethod.GET)
    @ResponseBody
    @SentinelResource("insertToMysql")
    public String insertToMysql(HttpServletRequest request){

        User user = new User();
        user.setAge(Integer.valueOf(request.getParameter("age")));
        user.setName(request.getParameter("name"));
        user.setRemark(request.getParameter("remark"));
        user.setAddr(request.getParameter("addr"));
        user.setCountry(request.getParameter("country"));
        this.userDao.save(user);

        return "insert ok";
    }
}
