package com.florian935.redispublisher.service;

public interface Publisher<T> {

    void publish(T message);
}
