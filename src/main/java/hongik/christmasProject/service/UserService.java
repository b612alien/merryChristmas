package hongik.christmasProject.service;

import hongik.christmasProject.entity.User;
import hongik.christmasProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}