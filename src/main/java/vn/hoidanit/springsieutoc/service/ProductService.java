package vn.hoidanit.springsieutoc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.domain.Product;
import vn.hoidanit.springsieutoc.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

}
