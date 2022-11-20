package bdd.demo.appjava.role;

import bdd.demo.appjava.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Role extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

}
