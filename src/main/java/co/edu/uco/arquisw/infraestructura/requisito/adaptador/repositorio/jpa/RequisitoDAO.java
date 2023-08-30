package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitoDAO extends JpaRepository<RequisitoEntidad, Long> { }