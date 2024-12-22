package hongik.christmasProject.repository;

import hongik.christmasProject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String keyword); //상품명으로 검색하기
}