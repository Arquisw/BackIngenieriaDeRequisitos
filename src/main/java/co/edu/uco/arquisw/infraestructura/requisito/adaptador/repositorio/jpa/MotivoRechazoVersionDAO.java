package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.MotivoRechazoVersionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoRechazoVersionDAO extends JpaRepository<MotivoRechazoVersionEntidad, Long> {
    MotivoRechazoVersionEntidad findByVersion(Long version);
}