package hongik.christmasProject.repository;

import hongik.christmasProject.entity.Cart;
import hongik.christmasProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user); //로그인된 유저의 장바구니만 보기
}