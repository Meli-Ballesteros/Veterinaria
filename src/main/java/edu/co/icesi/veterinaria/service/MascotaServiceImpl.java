package edu.co.icesi.veterinaria.service;

import edu.co.icesi.veterinaria.model.Mascota;
import edu.co.icesi.veterinaria.model.Veterinario;
import edu.co.icesi.veterinaria.repositoy.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service: Registra la clase como el Bean de negocio dentro de Spring
@Service
public class MascotaServiceImpl implements MascotaService {

    // @Autowired: Inyecta automáticamente el Bean de VetRepository sin usar new
    @Autowired
    private VetRepository vetRepository;

    @Override
    public List<Mascota> listarMascotas() {
        return vetRepository.findAllMascotas();
    }

    @Override
    public List<Veterinario> listarVeterinarios() {
        return vetRepository.findAllVeterinarios();
    }

    @Override
    public void registrarMascota(String nombre, String especie, String idVeterinario) {
        // Validamos la existencia del veterinario seleccionado
        Veterinario vet = vetRepository.findVeterinarioById(idVeterinario);
        if (vet != null && nombre != null && !nombre.trim().isEmpty()) {
            // Generamos un ID único en milisegundos
            String id = "M" + System.currentTimeMillis();
            Mascota nuevaMascota = new Mascota(id, nombre.trim(), especie.trim(), false, vet);
            vetRepository.saveMascota(nuevaMascota);
        }
    }

    @Override
    public void cambiarEstadoConsulta(String idMascota) {
        Mascota m = vetRepository.findMascotaById(idMascota);
        if (m != null) {
            // Inversión del estado booleano
            m.setAtendido(!m.isAtendido());
        }
    }
}
