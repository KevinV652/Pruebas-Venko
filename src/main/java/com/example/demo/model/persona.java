package com.example.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public abstract class persona {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaExpedicionDoc;
}
