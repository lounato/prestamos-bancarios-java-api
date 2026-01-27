package com.caixabanktech.prestamosbancarios.service;

import com.caixabanktech.prestamosbancarios.dto.SolicitudPrestamoDTO;
import com.caixabanktech.prestamosbancarios.entity.SolicitudPrestamo;
import com.caixabanktech.prestamosbancarios.enums.EstadoSolicitudEnum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SolicitudPrestamoService {

    List<SolicitudPrestamo> listarSolicitudes();

    Optional<SolicitudPrestamo> findSolicitudeById(Long id);

    SolicitudPrestamo crearSolicitud(SolicitudPrestamoDTO nuevaSolicitud);

    SolicitudPrestamo actualizarEstadoSolicitud(Long idSolicitud, EstadoSolicitudEnum nuevoEstado);
}
