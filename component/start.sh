#!/bin/bash


# 创建目录
mkdir -p /root/persistence/rocketmq/rmqs/logs
mkdir -p /root/persistence/rocketmq/rmqs/store
mkdir -p /root/persistence/rocketmq/rmq/logs
mkdir -p /root/persistence/rocketmq/rmq/store

mkdir -p /root/persistence/mysql
mkdir -p /root/persistence/redis

# 设置目录权限
chmod -R 777 /root/persistence/rocketmq/rmqs/logs
chmod -R 777 /root/persistence/rocketmq/rmqs/store
chmod -R 777 /root/persistence/rocketmq/rmq/logs
chmod -R 777 /root/persistence/rocketmq/rmq/store

# 下载并启动容器，且为 后台 自动启动
docker-compose up -d

# 显示 rocketmq 容器
docker-compose ps
