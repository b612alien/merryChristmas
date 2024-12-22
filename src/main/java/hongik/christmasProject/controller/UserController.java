package hongik.christmasProject.controller;

import hongik.christmasProject.entity.User;
import hongik.christmasProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        try {
            userService.signup(user);
            return "redirect:/login";
        } catch (IllegalStateException e) {
            return "redirect:/signup";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session
    ) {
        User user = userService.login(username,password);
        if (user!=null) {
            model.addAttribute("username",username);
            session.setAttribute("username",username); //session에 username 저장
            return "loginSuccess"; //그러면 로그인 성공
        }
        else {
            return "redirect:/login";
        }
    }
}