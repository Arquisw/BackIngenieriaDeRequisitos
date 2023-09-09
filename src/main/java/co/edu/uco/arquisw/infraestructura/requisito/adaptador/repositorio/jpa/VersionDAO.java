package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.VersionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionDAO extends JpaRepository<VersionEntidad, Long> {
}