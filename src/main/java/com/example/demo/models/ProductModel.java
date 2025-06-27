package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class ProductModel {
    private Integer id;
    private String title;
    private Integer userId;
    private String description;
    private Integer releaseYear;
    private String director;
    private BigDecimal rating;
    private Integer durationMinutes;
    private String imageUrl;
    private String trailerUrl;
//    private Timestamp createdAt;

}
