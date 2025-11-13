package com.utn.tareas.service;

import com.utn.tareas.model.*;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repo;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    @Value("${app.nombre}")
    private String nombreApp;

    public TareaService(TareaRepository repo) {
        this.repo = repo;
    }

    public Tarea agregar(String descripcion, Prioridad prioridad) {
        if (repo.obtenerTodas().size() >= maxTareas) {
            throw new RuntimeException("Se alcanzó el límite máximo de tareas");
        }
        return repo.guardar(new Tarea(null, descripcion, false, prioridad));
    }

    public List<Tarea> listar() {
        return repo.obtenerTodas();
    }

    public List<Tarea> pendientes() {
        return repo.obtenerTodas()
                .stream().filter(t -> !t.isCompletada()).toList();
    }

    public List<Tarea> completadas() {
        return repo.obtenerTodas()
                .stream().filter(Tarea::isCompletada).toList();
    }

    public void completar(Long id) {
        Tarea t = repo.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("No existe la tarea"));
        t.setCompletada(true);
    }

    public String estadisticas() {
        if (!mostrarEstadisticas) return "Estadísticas deshabilitadas";

        long total = repo.obtenerTodas().size();
        long comp = completadas().size();
        long pend = pendientes().size();

        return "Total: " + total + " | Completadas: " + comp + " | Pendientes: " + pend;
    }

    public void mostrarConfiguracion() {
        System.out.println("Aplicación: " + nombreApp);
        System.out.println("Máx tareas: " + maxTareas);
        System.out.println("Mostrar estadísticas: " + mostrarEstadisticas);
    }
}
