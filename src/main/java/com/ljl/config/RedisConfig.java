package com.ljl.config;

import com.ljl.constant.CacheModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * <P>
 *     redis配置
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 上午9:42
 */
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 对于普通K-V操作时，key采取的序列化策略
        template.setKeySerializer(new StringRedisSerializer());
        // value采取的序列化策略
        template.setValueSerializer(serializer);
        // 在hash数据结构中，hash-key的序列化策略
        template.setHashKeySerializer(serializer);
        // 在hash数据结构中，hash-key的序列化策略
        template.setHashValueSerializer(serializer);
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(3600l), // 默认策略，未配置的key会使用这个
                this.getRedisCacheConfigurationMap() // 指定key策略
        );
    }

    /**
     * 获取自定义缓存配置
     * @return
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        //CacheModule进行过期时间配置
        redisCacheConfigurationMap.put(CacheModule.MIN5, this.getRedisCacheConfigurationWithTtl(5*60l));
        redisCacheConfigurationMap.put(CacheModule.MIN10, this.getRedisCacheConfigurationWithTtl(10*60l));
        redisCacheConfigurationMap.put(CacheModule.MIN30, this.getRedisCacheConfigurationWithTtl(30*60l));
        redisCacheConfigurationMap.put(CacheModule.HOUR1, this.getRedisCacheConfigurationWithTtl(3600l));
        redisCacheConfigurationMap.put(CacheModule.DAY1, this.getRedisCacheConfigurationWithTtl(24*3600l));
        redisCacheConfigurationMap.put(CacheModule.WEEK1, this.getRedisCacheConfigurationWithTtl(7*24*3600l));
        return redisCacheConfigurationMap;
    }

    /**
     * 获取缓存时间配置
     * @param seconds
     * @return
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Long seconds) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer())
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }
}
