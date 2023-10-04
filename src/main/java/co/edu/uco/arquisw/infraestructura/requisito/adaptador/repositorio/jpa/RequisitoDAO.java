package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequisitoDAO extends JpaRepository<RequisitoEntidad, Long> {
    Page<RequisitoEntidad> findByVersion(Long versionId, Pageable pageable);
    long countByVersion(Long versionId);
}