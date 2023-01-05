package co.edu.uco.arquisw.dominio.requisito.puerto.consulta;

import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import java.util.List;

public interface RequisitoRepositorioConsulta {
    RequisitoDTO consultarRequisitoPorID(Long id);
    List<RequisitoDTO> consultarRequisitosPorEtapaID(Long etapaID);
    VersionDTO consultarVersionPorID(Long versionID);
    List<VersionDTO> consultarVersionesPorEtapaID(Long etapaID);
}