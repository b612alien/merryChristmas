package hongik.christmasProject.service;

import hongik.christmasProject.entity.Cart;
import hongik.christmasProject.entity.Product;
import hongik.christmasProject.entity.User;
import hongik.christmasProject.repository.CartRepository;
import hongik.christmasProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    //장바구니에 상품 담기
    @Transactional
    public void addToCart(User user,Long productId,int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다"));

        //이미 장바구니에 있는 상품인지 확인해서 있으면 수량만 올리기
        List<Cart> userCarts = cartRepository.findByUser(user);
        for (Cart cart : userCarts) {
            if (cart.getProduct().getId().equals(productId)) {
                //이미 있으면 수량 증가
                cart.setQuantity(cart.getQuantity() + quantity);
                cartRepository.save(cart);
                return;
            }
        }

        //없으면 새로 추가
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public List<Cart> getCartItems(User user) {
        return cartRepository.findByUser(user);
    }

    @Transactional
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}