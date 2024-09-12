package com.sauvlives.clinic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="validation")
public class Validation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private Instant creation;
    private Instant expiration;
    private Instant activation;
    private String activationCode;
    @OneToOne(cascade=CascadeType.ALL)
    private User user;
}
