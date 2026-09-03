package edu.co.icesi.veterinaria.model;

public class Mascota {
    private String id;
    private String nombre;
    private String especie;
    private boolean atendido;

    // RELACIÓN 1 a Muchos: La Mascota guarda la referencia directa al Veterinario que la atiende
    private Veterinario veterinarioAsignado;

    public Mascota(String id, String nombre, String especie, boolean atendido, Veterinario veterinarioAsignado) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.atendido = atendido;
        this.veterinarioAsignado = veterinarioAsignado;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public boolean isAtendido() { return atendido; }
    public void setAtendido(boolean atendido) { this.atendido = atendido; }
    public Veterinario getVeterinarioAsignado() { return veterinarioAsignado; }
}
