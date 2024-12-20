package com.luis.springcloud.msvc.items.models;

import java.time.LocalDate;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private LocalDate createAT;
    private int port;

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public LocalDate getCreateAT() {
        return createAT;
    }
    public void setCreateAt(LocalDate createAT) {
        this.createAT = createAT;
    }
}
