package co.edu.uco.arquisw.dominio.requisito.puerto.consulta;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;

import java.util.List;

public interface RequisitoRepositorioConsulta {
    RequisitoDTO consultarRequisitoPorID(Long id);

    List<RequisitoDTO> consultarRequisitosPorVersionID(Long versionId);

    VersionDTO consultarVersionPorID(Long versionId);

    List<VersionDTO> consultarVersionesPorEtapaID(Long etapaId);
}