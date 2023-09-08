package co.edu.uco.arquisw.dominio.fase.puerto.consulta;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import java.util.List;

public interface FaseRepositorioConsulta {
    FaseDTO consultarFasePorID(Long faseID);
    FaseDTO consultarFasePorEtapaID(Long etapaID);
    ProyectoDTO consultarProyectoPorID(Long proyectoID);
    EtapaDTO consultarEtapaPorID(Long etapaID);
    List<FaseDTO> consultarFasesPorProyectoID(Long proyectoID);
}