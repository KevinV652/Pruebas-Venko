package com.example.demo.controller;

import com.example.demo.model.paciente;
import com.example.demo.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class pacienteController {

    private PacienteService pacienteService;

    @Autowired
    public void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<paciente> obtenerTodos() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{numeroDocumento}")
    public Optional<paciente> obtenerPorDoc(@PathVariable String numeroDocumento) {
        return pacienteService.busquedaPorDoc(numeroDocumento);
    }

    @PostMapping
    public String agregar(@RequestBody paciente Paciente) {
        return pacienteService.agregarPaciente(Paciente);
    }

    @DeleteMapping("/{numeroDocumento}")
    public String eliminar(@PathVariable String numeroDocumento) {
        return pacienteService.eliminarPaciente(numeroDocumento);
    }

    @PutMapping("/{numeroDocumento}")
    public String editar(@PathVariable String numeroDocumento, @RequestBody paciente datosNuevos) {
        return pacienteService.editarPaciente(numeroDocumento, datosNuevos);
    }

}
