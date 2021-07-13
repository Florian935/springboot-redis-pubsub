package com.florian935.redissubscriber2.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@ConfigurationProperties(prefix = "redis.config")
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class RedisProperties {

    String host;
    int port;
    int databaseIndex;
    String channel;
}
