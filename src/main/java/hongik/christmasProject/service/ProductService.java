package hongik.christmasProject.service;

import hongik.christmasProject.entity.Product;
import hongik.christmasProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    //상품 등록
    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    //상품 전체 보기
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //상품 개별로 보기
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    //상품명 검색
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    //상품 수정
    @Transactional
    public Product updateProduct(Long id, Product updateProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setDescription(updateProduct.getDescription());
        product.setStock(updateProduct.getStock());
        product.setImageUrl(updateProduct.getImageUrl());

        return product;
    }

    //상품 삭제
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}