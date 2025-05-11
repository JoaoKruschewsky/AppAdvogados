package com.example.Advogados.domains;

import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    private String cpf;
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
