package com.fc;

import redis.clients.jedis.Jedis;

public class RedisConnectTest {
    public static void main(String[] args) {
        try {
            Jedis jedis = new Jedis("47.93.28.41", 6379);
            jedis.auth("123456");
            jedis.select(1);
            System.out.println("Connection to server sucessfully");
            //查看服务是否运行
            System.out.println("Server is running: " + jedis.ping());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
