package com.example.Advogados.Model.DTO.Lawyer;

import java.math.BigDecimal;
import java.util.Currency;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class updateLawyerDTO {
    private String imgDTO;
    private String descricionDTO;
    private String titleLawyerDTO;
    private String specializedAirDTO;
    private BigDecimal priceDTO;

    public String getImgDTO() {
        return imgDTO;
    }

    public void setImgDTO(String imgDTO) {
        this.imgDTO = imgDTO;
    }

    public String getDescricionDTO() {
        return descricionDTO;
    }

    public void setDescricionDTO(String descricionDTO) {
        this.descricionDTO = descricionDTO;
    }

    public BigDecimal getPriceDTO() {
        return priceDTO;
    }

    public void setPriceDTO(BigDecimal priceDTO) {
        this.priceDTO = priceDTO;
    }

    public String getTitleLawyerDTO() {
        return titleLawyerDTO;
    }

    public void setTitleLawyerDTO(String titleLawyerDTO) {
        this.titleLawyerDTO = titleLawyerDTO;
    }

    public String getSpecializedAirDTO() {
        return specializedAirDTO;
    }

    public void setSpecializedAirDTO(String specelizedAirDTO) {
        this.specializedAirDTO = specelizedAirDTO;
    }
}
