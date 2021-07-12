package com.florian935.redispublisher.service.impl;

import com.florian935.redispublisher.domain.Product;
import com.florian935.redispublisher.service.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductPublisher implements Publisher<Product> {

    RedisTemplate<String, Product> redisTemplate;
    ChannelTopic channelTopic;

    @Override
    public void publish(Product product) {

        redisTemplate.convertAndSend(channelTopic.getTopic(), product.toString());
    }
}
