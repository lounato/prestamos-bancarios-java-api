package com.caixabanktech.prestamosbancarios.enums;

public enum DivisaEnum {
    USD("$"),
    EUR("€"),
    MXN("MX$"),
    GBP("£");

    private final String simbolo;

    DivisaEnum(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}

