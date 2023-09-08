package co.edu.uco.arquisw.dominio.fase.puerto.consulta;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;

import java.util.List;

public interface FaseRepositorioConsulta {
    FaseDTO consultarFasePorID(Long faseID);
    FaseDTO consultarFasePorEtapaID(Long etapaID);
    EtapaDTO consultarEtapaPorID(Long etapaID);
    List<FaseDTO> consultarFasesPorProyectoID(Long proyectoID);
}