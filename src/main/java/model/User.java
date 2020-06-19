package model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @NonNull private String name;

    @Column(name = "surname", nullable = false)
    @NonNull private String surname;

    @Column(name = "age", nullable = false)
    @NonNull private int age;

    public User(@NonNull String name, @NonNull String surname, @NonNull int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User() {
    }
}
