package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.TipoRequisitoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRequisitoDAO extends JpaRepository<TipoRequisitoEntidad, Long> {
}