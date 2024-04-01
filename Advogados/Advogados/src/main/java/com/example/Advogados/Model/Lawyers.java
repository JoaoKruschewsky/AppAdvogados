package com.example.Advogados.Model;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Lawyers")
public class Lawyers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String cpf;

    private String name;
    private String titleLawyers;
    private String specializedAir;
    private String descricion;
    private String img_profile;
    private String price;

    @OneToMany(mappedBy = "lawyer")
    private List<LawyerClientRelationship> clientRelationships;

    // @Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")
    private String phoneNumber;

    // @NotBlank
    private String validationOAB;

    @Email
    @NotBlank
    private String email;

    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @NotBlank
    private String password;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_Profile() {
        return img_profile;
    }

    public void setImg_Profile(String img_Profile) {
        this.img_profile = img_Profile;
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

    public String getValidationOAB() {
        return validationOAB;
    }

    public void setValidationOAB(String validationOAB) {
        this.validationOAB = validationOAB;
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

    public String getTitleLawyers() {
        return titleLawyers;
    }

    public void setTitleLawyers(String titleLawyers) {
        this.titleLawyers = titleLawyers;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSpecializedAir() {
        return specializedAir;
    }

    public void setSpecializedAir(String specializedAir) {
        this.specializedAir = specializedAir;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public List<LawyerClientRelationship> getClientRelationships() {
        return clientRelationships;
    }

    public void setClientRelationships(List<LawyerClientRelationship> clientRelationships) {
        this.clientRelationships = clientRelationships;
    }
}
