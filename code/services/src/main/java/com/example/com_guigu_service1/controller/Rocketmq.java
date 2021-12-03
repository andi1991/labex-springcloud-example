package com.example.com_guigu_service1.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Rocketmq {
    @Value("${rocketmq.endpoint}")  // 获取自定义配置的值
    private String endpoint;
    private int flag = 0;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/syncMessage")
    @ResponseBody
    @SentinelResource("syncMessage")
    public String  syncMessage() throws Exception {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        if (flag == 0) {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
            consumer.setNamesrvAddr(endpoint);
            consumer.subscribe("TopicTest", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    String content = new String(msgs.get(0).getBody());
                    redisTemplate.opsForValue().set(msgs.get(0).getMsgId(), content);
                    //System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs.toString());
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            flag = 1;
            return "first rsync success";
        }
        else {
            return "Sync ing";
        }
    }

    @RequestMapping("/sendMessage")
    @ResponseBody   // 如果没有这个注释，返回值默认表示 Template 下面的模板页面
    @SentinelResource("sendMessage")
    public String sendMessage() throws Exception{
        System.out.println("endpoint: " + endpoint);

        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr(endpoint);
        //Launch the instance.
        producer.start();
        List<String> res = new ArrayList<String>(){};
        for (int i = 0; i < 3; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest",
                    "TagA",
                    ("Hello LabEx " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
            res.add(sendResult.toString());
        }
        producer.shutdown();
        return res.toString();
    }
}
