package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoTipoRequisitoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitoTipoRequisitoDAO extends JpaRepository<RequisitoTipoRequisitoEntidad, Long> { }