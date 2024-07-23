package com.productservice.productservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "price")
@AllArgsConstructor
@NoArgsConstructor
public class Price extends BaseModel {
    private String currency;
    private double value;
}
