package com.example.centinela_api.modelos;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String contrasena;
}
