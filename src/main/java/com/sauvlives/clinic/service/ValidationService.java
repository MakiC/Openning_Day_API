package com.sauvlives.clinic.service;


import com.sauvlives.clinic.entity.User;
import com.sauvlives.clinic.entity.Validation;
import com.sauvlives.clinic.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class ValidationService {

    private final ValidationRepository validationRepository;
    private final NotificationService notificationService;
    public void validate(User user){
        Validation validation=new Validation();
        Instant creation= Instant.now();
        Instant expiration=creation.plus(10, ChronoUnit.MINUTES);
        validation.setUser(user);
        validation.setCreation(creation);
        validation.setExpiration(expiration);

        Random random=new Random();
        int randomInteger=random.nextInt(999999);
        String activationCode=String.format("%06d",randomInteger);
        validation.setActivationCode(activationCode);
        validationRepository.save(validation);
        this.notificationService.send(validation);
    }

    public Optional<Validation> findValidationByActivationCode(String actvationCode) {
       return validationRepository.findValidationByActivationCode(actvationCode);
    }
}
