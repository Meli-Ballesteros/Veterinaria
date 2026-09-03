package edu.co.icesi.veterinaria.servlet;

import edu.co.icesi.veterinaria.config.AppConfig;
import edu.co.icesi.veterinaria.model.Mascota;
import edu.co.icesi.veterinaria.model.Veterinario;
import edu.co.icesi.veterinaria.service.MascotaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/veterinaria")
public class VetServlet extends HttpServlet {

    // Inyectamos la INTERFAZ, no la clase concreta (Principio de Desacoplamiento)
    private MascotaService mascotaService;

    @Override
    public void init() {
        // Carga el contexto de Spring utilizando la clase de configuración @Configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Le pedimos a Spring que nos entregue la implementación de MascotaService
        this.mascotaService = context.getBean(MascotaService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Clínica Veterinaria</title></head><body>");
        out.println("<h1>Gestión de Pacientes Veterinarios</h1>");

        // --- FORMULARIO 1: REGISTRAR MASCOTA (Con selección de 1 a N de Veterinario) ---
        out.println("<h3>Registrar Nueva Mascota</h3>");
        out.println("<form action='veterinaria' method='POST'>");
        out.println("  <input type='hidden' name='action' value='create' />");
        out.println("  Nombre: <input type='text' name='nombre' required /> ");
        out.println("  Especie: <input type='text' name='especie' required /> ");

        // Select dinámico cargado con los Veterinarios del sistema
        out.println("  Veterinario: <select name='idVeterinario'>");
        for (Veterinario v : mascotaService.listarVeterinarios()) {
            out.println("    <option value='" + v.getId() + "'>" + v.getNombre() + " (" + v.getEspecialidad() + ")</option>");
        }
        out.println("  </select> ");

        out.println("  <button type='submit'>Registrar</button>");
        out.println("</form>");

        out.println("<hr>");

        // --- LISTADO DE MASCOTAS Y BOTONES DE ACCIÓN ---
        out.println("<h2>Pacientes Registrados:</h2><ul>");
        for (Mascota m : mascotaService.listarMascotas()) {
            out.println("<li style='margin-bottom: 10px;'>");
            out.println("<b>" + m.getNombre() + "</b> (" + m.getEspecie() + ")");
            out.println(" - <i>Atendido por: " + m.getVeterinarioAsignado().getNombre() + "</i>");
            out.println(" - Estado: <b>" + (m.isAtendido() ? "ATENDIDO" : "EN ESPERA") + "</b> ");

            // FORMULARIO 2: Botón individual para alternar el estado
            out.println("  <form action='veterinaria' method='POST' style='display:inline;'>");
            out.println("    <input type='hidden' name='action' value='toggle' />");
            out.println("    <input type='hidden' name='id' value='" + m.getId() + "' />");
            out.println("    <button type='submit'>" + (m.isAtendido() ? "Marcar En Espera" : "Atender Paciente") + "</button>");
            out.println("  </form>");

            out.println("</li>");
        }
        out.println("</ul></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("toggle".equals(action)) {
            String id = req.getParameter("id");
            mascotaService.cambiarEstadoConsulta(id);
        } else if ("create".equals(action)) {
            String nombre = req.getParameter("nombre");
            String especie = req.getParameter("especie");
            String idVeterinario = req.getParameter("idVeterinario");

            mascotaService.registrarMascota(nombre, especie, idVeterinario);
        }

        // Redirección PRG para refrescar la vista
        resp.sendRedirect("./veterinaria");
    }
}