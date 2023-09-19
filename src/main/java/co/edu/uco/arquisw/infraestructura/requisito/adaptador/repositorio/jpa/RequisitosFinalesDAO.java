package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitosFinalesEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitosFinalesDAO extends JpaRepository<RequisitosFinalesEntidad, Long> {
    RequisitosFinalesEntidad findByEtapa(Long etapa);
}