package com.sauvlives.clinic.service;

import com.sauvlives.clinic.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;
@AllArgsConstructor
@Service
public class JwtService {
    UserService userService;
    public Map<String, String> generate(String userName){
        User user=this.userService.loadUserByUsername(userName);
        return this.generateJwt(user);
    }

    private Map<String, String> generateJwt(User user) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30 * 60 * 1000;
        return null;
    }
}
