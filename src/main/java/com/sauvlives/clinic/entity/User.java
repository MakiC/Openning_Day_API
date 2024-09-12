package com.sauvlives.clinic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="User")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private int id;
    private String password;
    @Column(name="First_Name")
    private String firstName;
    private String lastName;
    @Column(name="Email")
    private String email;
    @Column(name="Is_Activ")
    private boolean isActiv=false;
    @OneToOne(cascade=CascadeType.PERSIST)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.getRole().getRoleName()));
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    @JsonProperty("firstName")
    @Column(name="First_Name")
    public String getUsername() {
        return this.firstName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return this.isActiv;
    }
    @Override
    public boolean isAccountNonLocked() {
        return this.isActiv;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isActiv;
    }
    @Override
    public boolean isEnabled() {
        return this.isActiv;
    }
}
