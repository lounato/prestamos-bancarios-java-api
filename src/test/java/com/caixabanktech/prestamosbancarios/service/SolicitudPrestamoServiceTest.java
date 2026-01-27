package com.caixabanktech.prestamosbancarios.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import com.caixabanktech.prestamosbancarios.dto.SolicitudPrestamoDTO;
import com.caixabanktech.prestamosbancarios.entity.SolicitudPrestamo;
import com.caixabanktech.prestamosbancarios.enums.DivisaEnum;
import com.caixabanktech.prestamosbancarios.enums.EstadoSolicitudEnum;
import com.caixabanktech.prestamosbancarios.service.impl.SolicitudPrestamoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SolicitudPrestamoServiceTest {
    @InjectMocks
    private SolicitudPrestamoServiceImpl solicitudPrestamoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearSolicitud() throws Exception {
        // Preparar datos
        SolicitudPrestamoDTO dto = new SolicitudPrestamoDTO();
        dto.setNombreSolicitante("Juan Perez");
        dto.setImporteSolicitado(new BigDecimal("1000"));
        dto.setDivisa(DivisaEnum.USD);
        dto.setDocumentoIdentidad("12345678");

        // Crear solicitud
        SolicitudPrestamo resultado = solicitudPrestamoService.crearSolicitud(dto);

        // Validaciones
        assertNotNull(resultado.getId(), "El ID no debe ser null");
        assertEquals("Juan Perez", resultado.getNombreSolicitante());
        assertEquals(new BigDecimal("1000"), resultado.getImporteSolicitado());
        assertEquals(EstadoSolicitudEnum.PENDIENTE, resultado.getEstadoSolicitud());
        assertNotNull(resultado.getFechaCreacion(), "La fecha de creación no debe ser null");
    }

    @Test
    public void testActualizarEstadoSolicitudValido() throws Exception {
        // Crear solicitud
        SolicitudPrestamoDTO dto = new SolicitudPrestamoDTO();
        dto.setNombreSolicitante("Ana");
        dto.setImporteSolicitado(new BigDecimal("500"));
        dto.setDivisa(DivisaEnum.EUR);
        dto.setDocumentoIdentidad("87654321");

        SolicitudPrestamo solicitud = solicitudPrestamoService.crearSolicitud(dto);

        // Actualizar estado
        SolicitudPrestamo actualizado = solicitudPrestamoService.actualizarEstadoSolicitud(
                solicitud.getId(), EstadoSolicitudEnum.APROBADA
        );

        // Validaciones
        assertEquals(EstadoSolicitudEnum.APROBADA, actualizado.getEstadoSolicitud());
    }
}
