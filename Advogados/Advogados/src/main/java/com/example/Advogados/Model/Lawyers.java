package com.example.Advogados.Model;

import java.math.BigDecimal;
import java.nio.Buffer;
import java.util.List;
import java.util.Set;

import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Advogados.Model.DTO.LoginDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Lawyers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lawyers extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String titleLawyers;
    private String specializedAir;
    private String descricion;


    private BigDecimal price;

    @OneToMany(mappedBy = "lawyer")
    @JsonIgnore
    private List<LawyerClientRelationship> clientRelationships;

    @OneToMany(mappedBy = "lawyer")
    @JsonIgnore
    private List<Requests> requests;

    // @Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")


    // @NotBlank
    private String validationOAB;



    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_lawyer_roles", joinColumns = @JoinColumn(name = "lawyer_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public boolean isLoginCorrect(LoginDTO loginDTO, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginDTO.getPassword(), this.getPassword());
    }


    public  void updateLawyers(UpdateLawyerDTO updateLawyerDTO) {
        this.titleLawyers = updateLawyerDTO.getTitleLawyerDTO();
        this.specializedAir = updateLawyerDTO.getSpecializedAirDTO();
        this.descricion = updateLawyerDTO.getDescricionDTO();
        this.price = updateLawyerDTO.getPriceDTO();
    }
}
