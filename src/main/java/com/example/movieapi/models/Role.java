package com.example.movieapi.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Role is used to represent a role in the database
 * It implements GrantedAuthority which is used by Spring Security to authenticate roles for users.
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="role_id")
    private Integer roleId;

    private String authority;


    public Role(String authority){
        this.authority = authority;
    }




}
