package com.caixabanktech.prestamosbancarios;

import com.caixabanktech.prestamosbancarios.entity.SolicitudPrestamo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class PrestamosBancariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamosBancariosApplication.class, args);
    }

}
