package bdd.demo.appjava.user;

import bdd.demo.appjava.base.BaseEntity;
import bdd.demo.appjava.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class User extends BaseEntity<Long> {

    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @lombok.Builder.Default
    private Set<Role> roles = new HashSet<>();
}