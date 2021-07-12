package com.florian935.redispublisher.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class Product {

    int id;
    String name;
    int quantity;
    long price;
}
