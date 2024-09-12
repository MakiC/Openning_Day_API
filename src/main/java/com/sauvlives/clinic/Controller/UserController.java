package com.sauvlives.clinic.Controller;


import com.sauvlives.clinic.entity.AuthenticationDTO;
import com.sauvlives.clinic.entity.User;
import com.sauvlives.clinic.entity.Validation;
import com.sauvlives.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("subscription")
    public void subscribe(@RequestBody User user){
    userService.subscribe(user);
    }
    @PostMapping("activation")
    public void activation(@RequestBody Map<String,String> activationCodeMap){
        userService.activate(activationCodeMap);
    }
    @PostMapping("connexion")
    public Map<String,String> connexion(@RequestBody AuthenticationDTO authenticationDTO ){
            return null;
    }
}
