package hongik.christmasProject.repository;

import hongik.christmasProject.entity.Order;
import hongik.christmasProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user); //사용자 주문 조회
}