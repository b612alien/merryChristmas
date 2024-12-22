package hongik.christmasProject.controller;

import hongik.christmasProject.entity.Product;
import hongik.christmasProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //상품 검색 - 로그인 체크 제거
    @GetMapping("/products")
    public String productList(@RequestParam(required=false) String keyword, Model model) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchProducts(keyword);
        } else {
            products = productService.getAllProducts();  // 모든 상품 가져오기
        }
        model.addAttribute("products", products);
        return "products";
    }

    //판매제품 상세페이지
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model, HttpSession session) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);

        //username 갖고오기
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "productDetail";
    }
}