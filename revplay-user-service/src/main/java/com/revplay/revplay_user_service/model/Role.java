package com.revplay.revplay_user_service.model;

import com.revplay.revplay_user_service.Enum.RoleName;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    public Role() {}

    public Role(Long id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public static class RoleBuilder {
        private Long id;
        private RoleName name;

        public RoleBuilder id(Long id) { this.id = id; return this; }
        public RoleBuilder name(RoleName name) { this.name = name; return this; }

        public Role build() {
            return new Role(id, name);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public RoleName getName() { return name; }
    public void setName(RoleName name) { this.name = name; }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleName name;
}
