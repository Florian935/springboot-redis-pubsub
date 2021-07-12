package com.florian935.redispublisher.config;

import lombok.AccessLevel;
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
import org.springframework.data.redis.serializer.GenericToStringSerializer;

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
    RedisTemplate<String, Object> redisTemplate() {

        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));

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
