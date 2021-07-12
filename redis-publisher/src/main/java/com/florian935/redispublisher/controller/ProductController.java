package com.florian935.redispublisher.controller;

import com.florian935.redispublisher.domain.Product;
import com.florian935.redispublisher.service.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/products")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductController {

    Publisher<Product> productPublisher;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    String publishProduct(@RequestBody Product product) {

        productPublisher.publish(product);

        return "Event published !";
    }
}
