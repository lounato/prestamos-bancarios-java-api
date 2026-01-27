package com.caixabanktech.prestamosbancarios.dto;

import com.caixabanktech.prestamosbancarios.enums.DivisaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SolicitudPrestamoDTO {

    @NotBlank
    private String nombreSolicitante;

    @Positive
    private BigDecimal importeSolicitado;

    private DivisaEnum divisa;

    private String documentoIdentidad;
}
