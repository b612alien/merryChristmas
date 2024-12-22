package hongik.christmasProject.service;

import hongik.christmasProject.entity.Cart;
import hongik.christmasProject.entity.Order;
import hongik.christmasProject.entity.User;
import hongik.christmasProject.repository.CartRepository;
import hongik.christmasProject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    //장바구니애 담아놓은 상품들 주문하기
    @Transactional
    public void createOrder(User user) {
        //장바구니 목록 가져오기
        List<Cart> cartItems = cartRepository.findByUser(user);

        //장바구니 상품을 주문으로 바꾸기
        for (Cart cart : cartItems) {
            Order order = new Order();
            order.setUser(user);
            order.setProduct(cart.getProduct());
            order.setQuantity(cart.getQuantity());
            order.setOrderDate(LocalDateTime.now());
            order.setTotalPrice(cart.getProduct().getPrice() * cart.getQuantity());

            orderRepository.save(order);
        }
        //주문 완료된 장바구니 상품은 없애기
        cartRepository.deleteAll(cartItems);
    }
    //사용자의 주문 목록 조회
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}