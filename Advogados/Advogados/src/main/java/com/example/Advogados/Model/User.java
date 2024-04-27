package com.example.Advogados.Model;

import java.sql.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
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

    @Email
    @NotBlank
    private String email;

    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @NotBlank
    private String password;

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

}