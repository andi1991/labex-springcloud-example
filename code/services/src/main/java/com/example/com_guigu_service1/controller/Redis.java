package com.example.com_guigu_service1.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class Redis {
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/selectFromRedis")
    @SentinelResource("selectFromRedis")
    public String selectFromRedis(Map<String, Object> map ) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        Set<String> keys = redisTemplate.keys("*");
        Map<String, String> datas = new HashMap<String, String>(){};
        for (String key: keys) {
            String value = redisTemplate.opsForValue().get(key).toString();
            datas.put(key, value);
        }
        map.put("datas", datas);
        //System.out.println(redisTemplate.opsForValue().get("name"));
        return "redis_show";
    }
}
