package com.utn.tareas.repository;

import com.utn.tareas.model.Tarea;
import com.utn.tareas.model.Prioridad;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public TareaRepository() {
        tareas.add(new Tarea(idGen.getAndIncrement(), "Estudiar Spring", false, Prioridad.ALTA));
        tareas.add(new Tarea(idGen.getAndIncrement(), "Hacer ejercicio", true, Prioridad.MEDIA));
        tareas.add(new Tarea(idGen.getAndIncrement(), "Leer un libro", false, Prioridad.BAJA));
    }

    public Tarea guardar(Tarea t) {
        t.setId(idGen.getAndIncrement());
        tareas.add(t);
        return t;
    }

    public List<Tarea> obtenerTodas() {
        return tareas;
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public void eliminarPorId(Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}
