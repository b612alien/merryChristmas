package hongik.christmasProject.controller;

import hongik.christmasProject.entity.Cart;
import hongik.christmasProject.entity.User;
import hongik.christmasProject.service.CartService;
import hongik.christmasProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    //장바구니에 상품 담기
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity,
                            @RequestParam String username) {
        try {
            User user = userService.findByUsername(username);
            cartService.addToCart(user, productId, quantity);
            return "redirect:/products";  //상품 목록으로
        }
        catch (Exception e) {
            return "redirect:/login";  //안되면 로그인 페이지
        }
    }

    //장바구니 목록 보기
    @GetMapping("/cart")
    public String viewCart(@RequestParam String username, Model model) {
        User user = userService.findByUsername(username);
        List<Cart> cartItems = cartService.getCartItems(user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("username", username);
        return "cart";
    }


    //장바구니 상품 삭제
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id,
                                 HttpSession session) {
        cartService.removeFromCart(id);
        //삭제하고 장바구니로 다시 돌아옴(username포함)
        String username = (String) session.getAttribute("username");
        return "redirect:/cart?username=" + username;
    }
}