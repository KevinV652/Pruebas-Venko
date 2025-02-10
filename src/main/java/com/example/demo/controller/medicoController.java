package com.example.demo.controller;

import com.example.demo.model.medico;
import com.example.demo.service.medicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class medicoController {

    private medicoService MedicoService;

    @Autowired
    public void setMedicoService(medicoService medicoService) {
        MedicoService = medicoService;
    }

    @GetMapping
    public List<medico> obtenerTodos() {
        return MedicoService.listarMedicos();
    }

    @GetMapping("/{numeroDocumento}")
    public Optional<medico> obtenerPorDoc(@PathVariable String numeroDocumento) {
        return MedicoService.busquedaPorDoc(numeroDocumento);
    }

    @PostMapping
    public String agregar(@RequestBody medico Medico) {
        return MedicoService.agregarMedico(Medico);
    }

    @DeleteMapping("/{numeroDocumento}")
    public String eliminar(@PathVariable String numeroDocumento) {
        return MedicoService.eliminarMedico(numeroDocumento);
    }

    @PutMapping("/{numeroDocumento}")
    public String editar(@PathVariable String numeroDocumento, @RequestBody medico datosNuevos) {
        return MedicoService.editarMedico(numeroDocumento, datosNuevos);
    }

}
