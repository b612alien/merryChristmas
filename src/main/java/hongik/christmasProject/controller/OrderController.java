package hongik.christmasProject.controller;

import hongik.christmasProject.entity.Order;
import hongik.christmasProject.entity.User;
import hongik.christmasProject.service.OrderService;
import hongik.christmasProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    //주문하기
    @PostMapping("/order")
    public String order(@RequestParam String username) {
        User user = userService.findByUsername(username);
        orderService.createOrder(user);
        return "redirect:/orders?username="+username;
    }

    //주문 목록 보기
    @GetMapping("/orders")
    public String orderList(@RequestParam String username, Model model) {
        User user = userService.findByUsername(username);
        List<Order> orders = orderService.getOrdersByUser(user);
        model.addAttribute("orders",orders);
        model.addAttribute("username",username);
        return "orderList";
    }
}