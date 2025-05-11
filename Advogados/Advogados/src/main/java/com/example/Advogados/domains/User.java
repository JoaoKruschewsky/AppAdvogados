package com.example.Advogados.domains;

import com.example.Advogados.Model.DTO.LoginDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(unique = true)
    private String cpf;

    private String name;

    // @Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")
    private String phoneNumber;

    private String img_profile;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<LawyerClientRelationship> lawyerRelationships;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Requests> requests;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public List<LawyerClientRelationship> getLawyerRelationships() {
        return lawyerRelationships;
    }

    public void setLawyerRelationships(List<LawyerClientRelationship> lawyerRelationships) {
        this.lawyerRelationships = lawyerRelationships;
    }

    public String getImg_Profile() {
        return img_profile;
    }

    public void setImg_Profile(String img_Profile) {
        this.img_profile = img_Profile;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cPF) {
        cpf = cPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Requests> getRequests() {
        return requests;
    }

    public void setRequests(List<Requests> requests) {
        this.requests = requests;
    }

    public boolean isLoginCorrect(LoginDTO loginDTO, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginDTO.getPassword(), this.password);
    }

}