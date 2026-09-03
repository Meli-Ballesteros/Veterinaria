package edu.co.icesi.veterinaria.service;

import edu.co.icesi.veterinaria.model.Mascota;
import edu.co.icesi.veterinaria.model.Veterinario;
import java.util.List;

// Definición del "Contrato": Indica qué acciones puede realizar la capa web sin exponer el cómo
public interface MascotaService {
    List<Mascota> listarMascotas();
    List<Veterinario> listarVeterinarios();
    void registrarMascota(String nombre, String especie, String idVeterinario);
    void cambiarEstadoConsulta(String idMascota);
}