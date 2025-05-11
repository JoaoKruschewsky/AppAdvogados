package com.example.Advogados.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    private String name;

    public long getId() {
        return roleId;
    }

    public void setId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Values {
        USER(1L),
        LAWYER(2L);

        long roleID;

        Values(long roleID) {
            this.roleID = roleID;
        }

        public long getRoleId() {
            return roleID;
        }

    }

}
