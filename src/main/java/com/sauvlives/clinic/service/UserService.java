package com.sauvlives.clinic.service;

import com.sauvlives.clinic.entity.Role;
import com.sauvlives.clinic.entity.RoleType;
import com.sauvlives.clinic.entity.User;
import com.sauvlives.clinic.entity.Validation;
import com.sauvlives.clinic.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private  UserRepository userRepository;
    private  BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;

    public void subscribe(User user){
        if(!user.getEmail().contains("@")) throw new RuntimeException("invalid email");
        Optional userOptional = this.userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) throw new RuntimeException("User exists already");
        String encodedPassword=this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role userRole=new Role();
        userRole.setRoleName(RoleType.USER);
        user.setRole(userRole);
        user=userRepository.save(user);
        this.validationService.validate(user);
    };

    public void activate(Map<String,String> activationCodeMap) {
        String activationCode=activationCodeMap.get("activationCode");
        Optional<Validation> validation = validationService.findValidationByActivationCode(activationCode);
        if(!validation.isPresent()){
            throw new RuntimeException("Unknown Activation Code");
        }
        if(Instant.now().isAfter(validation.get().getExpiration())){
            throw new RuntimeException("Delai d'activation dépassé");
        }
        User user=userRepository.findById(validation.get().getUser().getId()).orElseThrow(()->new RuntimeException());
        user.setActiv(true);
        userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(username);
        return byEmail.orElseThrow(()->  new UsernameNotFoundException(username));
    }
}
