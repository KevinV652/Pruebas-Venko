package com.example.demo.service;

import com.example.demo.model.medico;
import com.example.demo.model.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final List<paciente> pacienteList = new ArrayList<>();

    public List<paciente> listarPacientes() {
        return pacienteList;
    }

    public Optional<paciente> busquedaPorDoc(String numeroDocumento) {
        return pacienteList.stream().filter(m -> m.getNumeroDocumento().equals(numeroDocumento)).findFirst();
    }

    public String agregarPaciente(paciente paciente) {
        if (busquedaPorDoc(paciente.getNumeroDocumento()).isPresent()
                || MedicoService.busquedaPorDoc(paciente.getNumeroDocumento()).isPresent()) {
            return "Error: Ya existe un paciente o un médico con este número de documento.";
        }
        pacienteList.add(paciente);
        return "Paciente agregado correctamente";
    }

    public String eliminarPaciente(String numeroDocumento) {
        return pacienteList.removeIf(m -> m.getNumeroDocumento().equals(numeroDocumento)) ? "Paciente eliminado."
                : "No se encontró el paciente";
    }

    public String editarPaciente(String numeroDocumento, paciente datosNuevos) {
        Optional<paciente> pacienteExistente = busquedaPorDoc(numeroDocumento);

        if (pacienteExistente.isPresent()) {
            paciente paciente = pacienteExistente.get();
            // Actualizar los datos excepto el número de documento
            paciente.setPrimerNombre(datosNuevos.getPrimerNombre());
            paciente.setSegundoNombre(datosNuevos.getSegundoNombre());
            paciente.setPrimerApellido(datosNuevos.getPrimerApellido());
            paciente.setSegundoApellido(datosNuevos.getSegundoApellido());
            paciente.setTipoDocumento(datosNuevos.getTipoDocumento());
            paciente.setFechaExpedicionDoc(datosNuevos.getFechaExpedicionDoc());
            paciente.setEnfermedad(datosNuevos.getEnfermedad());
            return "Paciente actualizado correctamente.";
        } else {
            return "No se encontró el paciente con el número de documento especificado.";
        }
    }

    private medicoService MedicoService;

    @Autowired
    public void setMedicoService(medicoService medicoService) {
        MedicoService = medicoService;
    }
}
