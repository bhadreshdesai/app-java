package bdd.demo.appjava.role;

import javax.persistence.*;

@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Data
@lombok.NoArgsConstructor
@Entity
public class Role {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

}
