package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.proyecto.ProyectoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoDAO extends JpaRepository<ProyectoEntidad, Long> { }