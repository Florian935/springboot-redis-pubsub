package com.florian935.redissubscriber.consumer;

public interface Consumer<T> {

    void handleMessage(T message);
}
