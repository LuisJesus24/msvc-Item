package com.luis.springcloud.msvc.items.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.luis.springcloud.msvc.items.models.Item;
import com.luis.springcloud.msvc.items.services.ItemService;

@RestController
public class ItemController {

    final private ItemService service;

    public ItemController (ItemService service){
        this.service = service;
    } 

    @GetMapping
    public List<Item> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<Item> itemOptional = service.findbyId(id);
        if(itemOptional.isPresent()){
            return ResponseEntity.ok(itemOptional.get());
        }
        return ResponseEntity.status(404).body(Collections.singletonMap("message", "No existe el producto en el microservicio msvc-products"));
    }
}
