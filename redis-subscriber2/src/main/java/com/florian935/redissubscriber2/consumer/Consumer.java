package com.florian935.redissubscriber2.consumer;

public interface Consumer<T> {

    void handleMessage(T message);
}
