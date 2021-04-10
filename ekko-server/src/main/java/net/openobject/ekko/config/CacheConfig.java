package net.openobject.ekko.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * CacheConfig.java
 * <br/>
 * cache 설정
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
@Configuration
public class CacheConfig {

	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	@Autowired
	RedisConnectionFactory connectionFactory;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Value("${ekkoserver.app.jwt-expiration-ms}")
	public String rjwtExpirationMs;

	@Bean
	public CacheManager redisCacheManager() {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMillis(Long.parseLong(rjwtExpirationMs)))
				.serializeKeysWith(
						RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(connectionFactory).cacheDefaults(redisCacheConfiguration).build();
		return redisCacheManager;
	}

}