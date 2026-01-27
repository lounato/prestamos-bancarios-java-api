package com.caixabanktech.prestamosbancarios.controller;

import com.caixabanktech.prestamosbancarios.dto.EstadoDTO;
import com.caixabanktech.prestamosbancarios.dto.SolicitudPrestamoDTO;
import com.caixabanktech.prestamosbancarios.entity.SolicitudPrestamo;
import com.caixabanktech.prestamosbancarios.service.SolicitudPrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/solicitudPrestamo")
public class SolicitudPrestamoController {

    public SolicitudPrestamoService solicitudPrestamoService;

    public SolicitudPrestamoController(SolicitudPrestamoService solicitudPrestamoService) {
        this.solicitudPrestamoService = solicitudPrestamoService;
    }

    @GetMapping("/")
    public String home() {
        return "API Solicitudes Préstamos Bancarios";
    }

    /**
     * Método para listar todas las solicitudes
     * @return listado solicitudes
     */
    @GetMapping("/all")
    public List<SolicitudPrestamo> listadoSolicitudes() {
        return solicitudPrestamoService.listarSolicitudes();
    }

    /**
     * Método que devuelve la solicitud con el id indicado
     * @param id solicitud
     * @return solicitud
     */
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudPrestamo> obtenerSolicitudPorId(@PathVariable Long id){

        return solicitudPrestamoService.findSolicitudeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    /**
     * Método para crear una nueva solicitud
     * @param nuevaSolicitud
     * @return solicitud creada
     */
    @PostMapping("/")
    public ResponseEntity<SolicitudPrestamo> crearSolicitud(@Valid @RequestBody SolicitudPrestamoDTO nuevaSolicitud) {
        SolicitudPrestamo solicitudRegistrada = solicitudPrestamoService.crearSolicitud(nuevaSolicitud);

        return ResponseEntity.status(HttpStatus.CREATED).body(solicitudRegistrada);

    }

    /**
     * Método para actualizar el estado de una solicitud existente
     * @param id solicitud
     * @param estado nuevo estado
     * @return solicitud modificada
     */
    @PutMapping("/{id}/estado")
    public ResponseEntity<SolicitudPrestamo> actualizarEstadoSolicitud(@PathVariable Long id, @RequestBody EstadoDTO estado){

        SolicitudPrestamo solicitudModificada = solicitudPrestamoService.actualizarEstadoSolicitud(id, estado.getEstadoSolicitud());

        return ResponseEntity.status(HttpStatus.OK).body(solicitudModificada);

    }
}
