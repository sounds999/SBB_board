package com.example.demo.Users;

import com.example.demo.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    // 직접 구현체를 넣으면 passwordEncode방식을 바꿀 때 일일이 코드를 수정해주어야 함
    // 그래서 SecurityConfig class에 구현체 넣는 메서드 선언 후, spring이 의존선 주입을 수행하도록 함
    private final PasswordEncoder passwordEncoder;

    public SiteUser getUser(String userName) {
        Optional<SiteUser> user = this.userRepository.findByusername(userName);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new DataNotFoundException(userName);
        }
    }

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
}
