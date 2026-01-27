package com.caixabanktech.prestamosbancarios.model;

import com.caixabanktech.prestamosbancarios.enums.DivisaEnum;
import com.caixabanktech.prestamosbancarios.enums.EstadoSolicitudEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SolicitudPrestamo {

    private String nombreSolicitante;

    private BigDecimal importeSolitado;

    private DivisaEnum divisa;

    private String documentoIdentidad;

    private Date fechaCreacion;

    private EstadoSolicitudEnum estadoSolicitud;

}
