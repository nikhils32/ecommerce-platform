package com.productservice.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDTO {
    public long id;
    public String title;
    public int price;
    public String category;
    public String description;
    public String image;
}
