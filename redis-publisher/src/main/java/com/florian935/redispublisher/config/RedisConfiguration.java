package com.florian935.redispublisher.config;

import com.florian935.redispublisher.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RedisConfiguration {

    RedisProperties redisProperties;

    @Bean
    JedisConnectionFactory connectionFactory() {

        final RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabaseIndex());

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    Jackson2JsonRedisSerializer<Product> jackson2JsonRedisSerializer() {

        return new Jackson2JsonRedisSerializer<>(Product.class);
    }

    @Bean
    RedisTemplate<String, Product> redisTemplate() {

        final RedisTemplate<String, Product> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    ChannelTopic channelTopic() {

        return new ChannelTopic(redisProperties.getChannel());
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter() {

        return new MessageListenerAdapter();
    }

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer() {

        RedisMessageListenerContainer redisMessageListenerContainer =
                new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(connectionFactory());
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter(), channelTopic());

        return redisMessageListenerContainer;
    }
}
