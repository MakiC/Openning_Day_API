package com.sauvlives.clinic.repository;

import com.sauvlives.clinic.entity.User;
import com.sauvlives.clinic.entity.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation,Integer> {
 public Optional<Validation> findValidationByActivationCode(String activationCode);
}
