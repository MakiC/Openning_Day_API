package com.sauvlives.clinic.Controller;

import com.sauvlives.clinic.entity.Avis;
import com.sauvlives.clinic.service.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("avis")
public class AvisController {
    private final AvisService avisService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creerAvis(@RequestBody Avis avis){
        avisService.creerAvis(avis);
    }

}
