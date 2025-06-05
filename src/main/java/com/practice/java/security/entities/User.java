package com.practice.java.security.entities;

import com.practice.java.security.entities.other.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "\"user\"")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter
    private String name;

    private String username;

    @Column(nullable = false, updatable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Getter
    private Role roles;

//    @JsonIgnoreProperties(allowSetters=true)
//    @ManyToMany
//    @JoinTable(name = "product_user",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "product_id")
//        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"})
//    )
//    @Getter
//    private List<Product> product_id;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = this.roles.getListRole()
                .stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        String prefix = "ROLE_";
        authorityList.add(new SimpleGrantedAuthority(prefix + this.roles.name()));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
