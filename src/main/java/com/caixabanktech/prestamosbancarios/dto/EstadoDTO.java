package com.caixabanktech.prestamosbancarios.dto;

import com.caixabanktech.prestamosbancarios.enums.EstadoSolicitudEnum;
import lombok.Data;

@Data
public class EstadoDTO {
    private EstadoSolicitudEnum estadoSolicitud;
}
