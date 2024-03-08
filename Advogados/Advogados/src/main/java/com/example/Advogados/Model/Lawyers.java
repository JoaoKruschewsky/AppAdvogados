package com.example.Advogados.Model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @CPF
    @NotBlank
    private String cpf;

    private String name;
    private String titleLawyers;
    private String specializedAir;
    private String descricion;

    // @Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")
    private String phoneNumber;

    @NotBlank
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
}
