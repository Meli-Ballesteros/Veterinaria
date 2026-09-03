package edu.co.icesi.veterinaria.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @Configuration: Le indica a Spring que esta clase reemplaza al archivo applicationContext.xml
@Configuration
// @ComponentScan: Escanea automáticamente todo tu paquete para buscar clases anotadas con @Repository y @Service
@ComponentScan(basePackages = "edu.co.icesi.veterinaria")
public class AppConfig {
    // No necesita código adentro, las anotaciones hacen todo el trabajo
}