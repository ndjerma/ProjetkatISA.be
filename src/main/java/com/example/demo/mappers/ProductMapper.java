package com.example.demo.mappers;

import com.example.demo.entities.Product;
import com.example.demo.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    // za citanje ::[entity => model]
    public static ProductModel toModel(Product entity){
       return ProductModel.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .userId(entity.getUserId())
                .description(entity.getDescription())
                .releaseYear(entity.getReleaseYear())
                .director(entity.getDirector())
                .rating(entity.getRating())
                .durationMinutes(entity.getDurationMinutes())
                .imageUrl(entity.getImageUrl())
                .trailerUrl(entity.getTrailerUrl())
                .build();
    }

    public static List<ProductModel> toModelList(List<Product> entities){
        var list = new ArrayList<ProductModel>();

        for (var entity : entities) {
            list.add(toModel(entity));
        }
        return list;
    }

    // za upis ::[model => entity]
    public static Product toEntity(ProductModel model){
        Product product = new Product();
        product.setId(model.getId());       // ovo prolazi ovaj put
        product.setTitle(model.getTitle());
        product.setUserId(model.getUserId());
        product.setDescription(model.getDescription());
        product.setReleaseYear(model.getReleaseYear());
        product.setDirector(model.getDirector());
        product.setRating(model.getRating());
        product.setDurationMinutes(model.getDurationMinutes());
        product.setImageUrl(model.getImageUrl());
        product.setTrailerUrl(model.getTrailerUrl());

        return product;
    }



}
