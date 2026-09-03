package edu.co.icesi.veterinaria.repositoy;

import edu.co.icesi.veterinaria.model.Mascota;
import edu.co.icesi.veterinaria.model.Veterinario;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// @Repository: Marca la clase como un Bean de persistencia para que Spring la gestione automáticamente
@Repository
public class VetRepository {

    // Listas internas en memoria que simulan las tablas de una base de datos
    private final List<Veterinario> veterinarios = new ArrayList<>();
    private final List<Mascota> mascotas = new ArrayList<>();

    // @PostConstruct: Garantiza que Spring ejecute este método justo después de instanciar la clase
    @PostConstruct
    public void cargarDatosIniciales() {
        // 1. Instanciamos dos veterinarios para tenerlos disponibles en el sistema
        Veterinario vet1 = new Veterinario("V1", "Dr. Carlos Pérez", "Cirugía");
        Veterinario vet2 = new Veterinario("V2", "Dra. Laura Gómez", "Rehabilitación");

        veterinarios.add(vet1);
        veterinarios.add(vet2);

        // 2. Registramos mascotas asociándolas directamente con su respectivo objeto Veterinario
        mascotas.add(new Mascota("M1", "Coco", "Perro", false, vet1));
        mascotas.add(new Mascota("M2", "Michi", "Gato", true, vet2));

        System.out.println("¡[OK] Datos iniciales de la Veterinaria cargados correctamente!");
    }

    // Retorna la lista completa de veterinarios registrados en el sistema
    public List<Veterinario> findAllVeterinarios() {
        return veterinarios;
    }

    // Retorna la lista completa de mascotas registradas
    public List<Mascota> findAllMascotas() {
        return mascotas;
    }

    // Recorre la lista de veterinarios y retorna aquel cuyo ID coincida exactamente con el parámetro recibido
    public Veterinario findVeterinarioById(String id) {
        for (Veterinario v : veterinarios) {
            // Si coincide el identificador, retornamos inmediatamente el objeto hallado
            if (v.getId().equals(id)) return v;
        }
        // Si no se encuentra ningún registro con dicho ID, retorna null
        return null;
    }

    // Recorre la lista de mascotas y retorna aquella cuyo ID coincida con el solicitado
    public Mascota findMascotaById(String id) {
        for (Mascota m : mascotas) {
            // Compara los identificadores de tipo String
            if (m.getId().equals(id)) return m;
        }
        // Si no existe la mascota buscada, retorna null
        return null;
    }

    // Añade una nueva instancia de Mascota a la lista en memoria (simula un "INSERT INTO" en SQL)
    public void saveMascota(Mascota mascota) {
        mascotas.add(mascota);
    }
}