package com.florian935.redissubscriber.consumer.impl;

import com.florian935.redissubscriber.consumer.Consumer;
import com.florian935.redissubscriber.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductConsumer implements Consumer<Product> {

    @Override
    public void handleMessage(Product product) {

        System.out.println(product);
    }
}
