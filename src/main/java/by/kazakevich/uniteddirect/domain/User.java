package by.kazakevich.uniteddirect.domain;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;
    private String password;

    @Column(name = "username")
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    @LastModifiedDate
    private Date updated;
}
