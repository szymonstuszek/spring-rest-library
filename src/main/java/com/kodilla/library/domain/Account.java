package com.kodilla.library.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNTS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "USERNAME"
        }),
        @UniqueConstraint(columnNames = {
                "EMAIL"
        })
})
public class Account {
    private Long id;
    private String username;
    private String email;
    private String password;
    private User user;
    private Set<Role> roles = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @NotBlank
    @Size(min = 4, max = 50)
    public String getUsername() {
        return username;
    }

    @NotBlank
    @Size(min = 3, max = 50)
    @Email
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    @NotBlank
    @Size(min = 6, max = 100)
    public String getPassword() {
        return password;
    }

    @OneToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ACCOUNT_ROLES",
            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    public Set<Role> getRoles() {
        return roles;
    }

    public Account() {}

    public Account(String email, String password, String username, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
