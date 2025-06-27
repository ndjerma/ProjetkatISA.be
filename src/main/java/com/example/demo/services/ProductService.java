package com.example.demo.services;

import com.example.demo.exceptions.BaseException;
import com.example.demo.exceptions.user.UserException;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;


    @Override
    public List<ProductModel> findAll() {
        return ProductMapper.toModelList(productRepository.findAll());
    }

    @Override
    public ProductModel findByTitle(String title) {
        return ProductMapper.toModel(productRepository.findProductByTitle(title));
    }

    @Override
    public ProductModel create(ProductModel productModel) {

        var movie = ProductMapper.toEntity(productModel);
        var existingMovie = productRepository.findProductByTitle(movie.getTitle());

        if(existingMovie != null)
            throw new BaseException("Movie titled " + productModel.getTitle() + " already exists");

        // Cuvamo movie entitet u bazi, vracamo ga nazad u DTO
        // i vracamo ga konacno nazad u kontroler
        return ProductMapper.toModel(productRepository.save(movie));
    }

    public ProductModel update(ProductModel productModel) {
        var entity = ProductMapper.toEntity(productModel);
        try {
            var result = productRepository.save(entity);
            return ProductMapper.toModel(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer productId) {
        var entity = productRepository.findById(productId).orElseThrow(() -> new BaseException("User Not Found"));
        productRepository.delete(entity);
    }




}
