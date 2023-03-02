package com.nassim.usermanagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Account {

    @Id
    private String id = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)  //JPA stocke la chaîne de caractères correspondant à la valeur de l'enum dans la base de données plutôt que la valeur numérique par défaut.
    private Role role;
    private String firstname;
    private String lastname;
    @Column(name = "email", unique = true)
    private String email;
    private String password;


}
