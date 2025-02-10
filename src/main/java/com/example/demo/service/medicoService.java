package com.example.demo.service;

import com.example.demo.model.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class medicoService {

    private List<medico> medicoList = new ArrayList<>();

    public List<medico> listarMedicos() {
        return medicoList;
    }

    public Optional<medico> busquedaPorDoc(String numeroDocumento) {
        return medicoList.stream().filter(m -> m.getNumeroDocumento().equals(numeroDocumento)).findFirst();
    }

    public String agregarMedico(medico medico) {
        if (busquedaPorDoc(medico.getNumeroDocumento()).isPresent()
                || pacienteService.busquedaPorDoc(medico.getNumeroDocumento()).isPresent()) {
            return "Error: Ya existe un medico o un paciente con este número de documento.";
        }
        medicoList.add(medico);
        return "Medico agregado correctamente";
    }

    public String eliminarMedico(String numeroDocumento) {
        return medicoList.removeIf(m -> m.getNumeroDocumento().equals(numeroDocumento)) ? "Medico eliminado."
                : "No se encontró el médico";
    }

    public String editarMedico(String numeroDocumento, medico datosNuevos) {
        Optional<medico> medicoExistente = busquedaPorDoc(numeroDocumento);

        if (medicoExistente.isPresent()) {
            medico medico = medicoExistente.get();
            // Actualizar los datos excepto el número de documento
            medico.setPrimerNombre(datosNuevos.getPrimerNombre());
            medico.setSegundoNombre(datosNuevos.getSegundoNombre());
            medico.setPrimerApellido(datosNuevos.getPrimerApellido());
            medico.setSegundoApellido(datosNuevos.getSegundoApellido());
            medico.setTipoDocumento(datosNuevos.getTipoDocumento());
            medico.setFechaExpedicionDoc(datosNuevos.getFechaExpedicionDoc());
            medico.setEspecialidad(datosNuevos.getEspecialidad());
            return "Medico actualizado correctamente.";
        } else {
            return "No se encontró el medico con el número de documento especificado.";
        }
    }

    private PacienteService pacienteService;

    @Autowired
    public void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
}
