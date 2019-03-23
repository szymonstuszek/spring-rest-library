package com.kodilla.library.domain;

import com.kodilla.library.domain.enums.RoleName;
import javax.persistence.*;

@Entity
@Table(name = "ROLES")
public class Role {

    private Long id;
    private RoleName roleName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    public RoleName getRoleName() {
        return roleName;
    }

    public Role() {}

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
