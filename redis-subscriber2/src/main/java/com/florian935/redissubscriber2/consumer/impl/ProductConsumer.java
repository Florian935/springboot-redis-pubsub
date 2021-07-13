package com.florian935.redissubscriber2.consumer.impl;

import com.florian935.redissubscriber2.consumer.Consumer;
import com.florian935.redissubscriber2.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductConsumer implements Consumer<Product> {

    @Override
    public void handleMessage(Product product) {

        log.info("Consumed event :: {}", product);
    }
}
