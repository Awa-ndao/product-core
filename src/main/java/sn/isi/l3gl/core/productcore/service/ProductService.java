package sn.isi.l3gl.core.productcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.isi.l3gl.core.productcore.model.Product;
import sn.isi.l3gl.core.productcore.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public List<Product> listProducts() {
        return productRepository.findAll();
    }


    public Product updateQuantity(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        product.setQuantity(quantity);
        return productRepository.save(product);
    }


    public long countLowStockProducts() {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getQuantity() <= 5)
                .count();
    }
}