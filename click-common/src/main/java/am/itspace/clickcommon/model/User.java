package am.itspace.clickcommon.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @OneToOne
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "passport_id")
    private String  passportId;

    @Enumerated(EnumType.STRING)
    @Column
    private UserType userType=UserType.USER;

    @Column
    private boolean isEnable;

    @Column
    private String token;

    @OneToOne
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;


    public User(String email, String password, List<GrantedAuthority> authorityList) {
    }
}
