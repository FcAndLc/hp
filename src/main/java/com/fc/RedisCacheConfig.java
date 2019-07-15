package com.fc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableCaching
@EnableRedisHttpSession
public class RedisCacheConfig extends CachingConfigurerSupport {
    Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private int database;


    @Bean
    public RedisConnectionFactory redisPoolFactory(JedisPoolConfig jedisPoolConfig) {
        logger.info("JedisPool注入成功！！");
        JedisConnectionFactory jedisPool = new JedisConnectionFactory();
        jedisPool.setPoolConfig(jedisPoolConfig);
        jedisPool.setDatabase(database);
        jedisPool.setPassword(password);
        jedisPool.setPort(port);
        jedisPool.setHostName(host);
        jedisPool.setUsePool(true);
        jedisPool.setTimeout(timeout);
        jedisPool.setUseSsl(false);
        jedisPool.afterPropertiesSet();
        return jedisPool;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return jedisPoolConfig;
    }


    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true);// 开启redis事务
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //默认超时时间,单位秒
        cacheManager.setDefaultExpiration(60*60*24);
        //根据缓存名称设置超时时间,0为不超时
        Map<String,Long> expires = new ConcurrentHashMap<>();
        cacheManager.setExpires(expires);
        return cacheManager;
    }

   /* @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory, MessageListener messageListener){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        Topic topic = new PatternTopic("__key*__:expired");
        Collection<Topic> topics = new ArrayList<>();
        topics.add(topic);
        container.setMessageListeners(new HashMap<MessageListener,Collection<? extends Topic>>(){{
            this.put(messageListener,topics);
        }});
        return container;
    }
*/
   /* @Bean
    public MessageListener messageListener(RedisExpireListenerDelegate delegate){
        return new MessageListenerAdapter(delegate);
    }
*/
//MessageListenerAdapter
}