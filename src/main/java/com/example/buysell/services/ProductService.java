package com.example.buysell.services;

import com.example.buysell.models.Product;
import com.example.buysell.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    //Если есть title то ищем
    // Если  в заголовке ничего нет,  возвращаем  список .
    public  List<Product>listProducts(String title){
        if(title!=null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        log.info("Сохранен новый {}", product);
        //Cохраняем товар и логируем.
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id); //Удаление товара
    }

    public Product getProductById(Long id) {//Возвращаем товар, если товар не найден возвращаем нул
        return productRepository.findById(id).orElse(null);
    }
}
