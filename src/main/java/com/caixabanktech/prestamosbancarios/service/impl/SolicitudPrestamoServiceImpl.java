package com.caixabanktech.prestamosbancarios.service.impl;

import com.caixabanktech.prestamosbancarios.dto.SolicitudPrestamoDTO;
import com.caixabanktech.prestamosbancarios.entity.SolicitudPrestamo;
import com.caixabanktech.prestamosbancarios.enums.EstadoSolicitudEnum;
import com.caixabanktech.prestamosbancarios.service.SolicitudPrestamoService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudPrestamoServiceImpl implements SolicitudPrestamoService {

    @Override
    public List<SolicitudPrestamo> listarSolicitudes()  {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File("data/solicitudes.json");
            if(!file.exists()) {
                // Crear archivo vacío si no existe
                objectMapper.writeValue(file, new ArrayList<SolicitudPrestamo>());
            }
            return objectMapper.readValue(file, new TypeReference<List<SolicitudPrestamo>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error al leer las solicitudes: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<SolicitudPrestamo> findSolicitudeById(Long id) {
        List<SolicitudPrestamo> solicitudes = listarSolicitudes();

        return solicitudes.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

    }

    @Override
    public SolicitudPrestamo crearSolicitud(SolicitudPrestamoDTO nuevaSolicitud) {
        //Nuevos datos
        SolicitudPrestamo solicitudARegistrar = new SolicitudPrestamo();

        BeanUtils.copyProperties(nuevaSolicitud, solicitudARegistrar);

        //Solicitudes existentes
        List<SolicitudPrestamo> solicitudes = listarSolicitudes();

        Long nuevoId = solicitudes.stream()
                .map(SolicitudPrestamo::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
        solicitudARegistrar.setId(nuevoId);

        // Fecha de creación y estado por defecto
        solicitudARegistrar.setFechaCreacion(LocalDateTime.now());
        solicitudARegistrar.setEstadoSolicitud(EstadoSolicitudEnum.PENDIENTE);

        // Agregar a la lista
        solicitudes.add(solicitudARegistrar);

        //Escribir en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new File("data/solicitudes.json"),
                solicitudes
        );

        return solicitudARegistrar;
    }

    @Override
    public SolicitudPrestamo actualizarEstadoSolicitud(Long idSolicitud, EstadoSolicitudEnum nuevoEstado) {
        //Solicitudes existentes
        List<SolicitudPrestamo> solicitudes = listarSolicitudes();

        SolicitudPrestamo solicitudAActualizar = solicitudes.stream()
                .filter(s -> s.getId().equals(idSolicitud))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if(esCambioEstadoValido(solicitudAActualizar.getEstadoSolicitud(), nuevoEstado)){
            solicitudAActualizar.setEstadoSolicitud(nuevoEstado);
        } else {
            throw new RuntimeException("Transición de estado no permitida: "
                    + solicitudAActualizar.getEstadoSolicitud() + " -> " + nuevoEstado);
        }

        // Guardar cambios en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new File("data/solicitudes.json"),
                solicitudes
        );

        return solicitudAActualizar;
    }

    public boolean esCambioEstadoValido(EstadoSolicitudEnum antiguoEstado, EstadoSolicitudEnum nuevoEstado){
        return (antiguoEstado == EstadoSolicitudEnum.PENDIENTE && (nuevoEstado == EstadoSolicitudEnum.APROBADA || nuevoEstado == EstadoSolicitudEnum.RECHAZADA)) || (antiguoEstado == EstadoSolicitudEnum.APROBADA && nuevoEstado == EstadoSolicitudEnum.CANCELADA);
    }

}
