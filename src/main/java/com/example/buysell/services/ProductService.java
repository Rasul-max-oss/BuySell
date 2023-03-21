package com.example.buysell.services;

import com.example.buysell.models.Image;
import com.example.buysell.models.Product;
import com.example.buysell.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize()!=0){
            image1=toImage(file);// преобразуем в изображение
        }


        log.info("Сохранен новый {}", product);
        //Cохраняем товар и логируем.
        productRepository.save(product);
    }

    //Метод преобразования
    private  Image toImage(MultipartFile file) throws IOException{
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id); //Удаление товара
    }

    public Product getProductById(Long id) {//Возвращаем товар, если товар не найден возвращаем нул
        return productRepository.findById(id).orElse(null);
    }
}
