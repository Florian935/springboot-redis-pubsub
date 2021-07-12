# Spring Boot application with Redis PubSub

The goal of this project is to demonstrate how to use `Redis as a broker` with a `Pub / Sub pattern`. This pattern is often used in the `Event Driven Architecture`.

You can find the `redis-publisher package` which publish messages to broker and the `redis-subscriber package` which consume asynchonously the messages. 