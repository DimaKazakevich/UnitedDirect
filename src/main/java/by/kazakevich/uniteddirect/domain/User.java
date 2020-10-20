package by.kazakevich.uniteddirect.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "UserId", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "RoleId", referencedColumnName = "id")})
    private List<Role> roles;
}
