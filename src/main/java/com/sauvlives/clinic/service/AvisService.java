package com.sauvlives.clinic.service;
import lombok.extern.slf4j.Slf4j;
import com.sauvlives.clinic.entity.Avis;
import com.sauvlives.clinic.repository.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Slf4j
@AllArgsConstructor
@Service
public class AvisService {
    private final AvisRepository avisRepository;
    public void creerAvis(Avis avis){
        this.avisRepository.save(avis);
    }
}
