package com.caixabanktech.prestamosbancarios.entity;

import com.caixabanktech.prestamosbancarios.enums.DivisaEnum;
import com.caixabanktech.prestamosbancarios.enums.EstadoSolicitudEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SolicitudPrestamo {

    private Long id;

    private String nombreSolicitante;

    private BigDecimal importeSolicitado;

    private DivisaEnum divisa;

    private String documentoIdentidad;

    private LocalDateTime fechaCreacion;

    private EstadoSolicitudEnum estadoSolicitud;

}
