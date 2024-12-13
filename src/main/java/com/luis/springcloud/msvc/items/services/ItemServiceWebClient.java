package com.luis.springcloud.msvc.items.services;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import com.luis.springcloud.msvc.items.models.Item;
import com.luis.springcloud.msvc.items.models.Product;

@Primary
@Service
public class ItemServiceWebClient implements ItemService{

    private final WebClient.Builder client;

    public ItemServiceWebClient(Builder client) {
        this.client = client;
    }

    @Override
    public List<Item> findAll() {
        return this.client.build()
        .get()
        .accept(org.springframework.http.MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(Product.class)
        .map(product -> new Item(product, new Random().nextInt(10) +1))
        .collectList()
                .block();
    }

    @Override
    public Optional<Item> findbyId(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        try {
            return Optional.of(client.build().get().uri("/{id}", params)
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .map(product -> new Item(product, new Random().nextInt(10) +1))
                .block());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
