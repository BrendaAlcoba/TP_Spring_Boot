package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    @Override
    public void run(String... args) {

        // 1️ Mensaje de bienvenida según profile
        mensajeService.mostrarBienvenida();

        // 2️ Mostrar configuración cargada desde las properties
        tareaService.mostrarConfiguracion();

        // 3️ Listar tareas iniciales
        System.out.println("\n📌 TAREAS INICIALES:");
        tareaService.listar().forEach(System.out::println);

        // 4️ Agregar una nueva tarea
        System.out.println("\n Agregando nueva tarea...");
        tareaService.agregar("Preparar entrega del TP", Prioridad.ALTA);

        // 5️ Listar tareas pendientes
        System.out.println("\n TAREAS PENDIENTES:");
        tareaService.pendientes().forEach(System.out::println);

        // 6️ Marcar una tarea como completada
        System.out.println("\n✔ Marcando tarea con ID 1 como completada...");
        tareaService.completar(1L);

        // 7️ Mostrar estadísticas
        System.out.println("\n ESTADÍSTICAS:");
        System.out.println(tareaService.estadisticas());

        // 8️ Listar tareas completadas
        System.out.println("\n TAREAS COMPLETADAS:");
        tareaService.completadas().forEach(System.out::println);

        // 9️ Mensaje final
        mensajeService.mostrarDespedida();
    }
}

