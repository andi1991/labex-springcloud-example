package com.example.com_guigu_service1.dao;


import com.example.com_guigu_service1.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    //方法名称命名规则
    //List<User> findByUsernameIs(String string);
    //List<User> findByUsernameLike(String string);
    //List<User> findByUsernameAndUserageGreaterThanEqual(String name,Integer age);
}
