package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerMinDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    List<SaleMinDTO> sales = new ArrayList<>();

    public SellerMinDTO() {
    }

    public SellerMinDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public SellerMinDTO(Seller seller) {
        id = seller.getId();
        name = seller.getName();
        email = seller.getEmail();
        sales = seller.getSales().stream().map(x -> new SaleMinDTO(x)).collect(Collectors.toList());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public List<SaleMinDTO> getSales() {
        return sales;
    }
}
