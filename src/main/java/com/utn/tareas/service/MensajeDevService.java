package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("🌱 Bienvenido al entorno de desarrollo (DEV)");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("👋 Finalizando ejecución en entorno DEV");
    }
}
