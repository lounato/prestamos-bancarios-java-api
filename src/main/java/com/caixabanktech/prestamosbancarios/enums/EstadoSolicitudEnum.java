package com.caixabanktech.prestamosbancarios.enums;

public enum EstadoSolicitudEnum {
    PENDIENTE("Pendiente"),
    APROBADA("Aprobada"),
    RECHAZADA("Rechazada"),
    CANCELADA("Cancelada");

    private final String value;

    EstadoSolicitudEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
