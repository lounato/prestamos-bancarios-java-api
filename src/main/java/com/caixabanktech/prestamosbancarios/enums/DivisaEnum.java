package com.caixabanktech.prestamosbancarios.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DivisaEnum {
    USD,
    EUR,
    MXN,
    GBP;

    @JsonValue
    public String nameValue() {
        return this.name();
    }
}

