package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoFinalEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitoFinalDAO extends JpaRepository<RequisitoFinalEntidad, Long> { }