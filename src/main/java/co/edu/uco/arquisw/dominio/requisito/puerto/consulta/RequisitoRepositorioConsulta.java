package co.edu.uco.arquisw.dominio.requisito.puerto.consulta;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RequisitoRepositorioConsulta {
    RequisitoDTO consultarRequisitoPorID(Long id);

    List<RequisitoDTO> consultarRequisitosPorVersionID(Long versionId);

    Page<RequisitoDTO> consultarRequisitosPorVersionIDPaginado(Long versionId, int pagina, int tamano);

    VersionDTO consultarVersionPorID(Long versionId);

    List<VersionDTO> consultarVersionesPorEtapaID(Long etapaId);

    VersionDTO consultarUltimaVersionPorEtapaID(Long etapaId);

    Boolean terminoProcesoDeIngenieriaDeRequisitosPorProyectoID(Long proyectoId);
}