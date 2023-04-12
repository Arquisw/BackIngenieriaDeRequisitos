package co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.EtapaEntidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EtapaMapeador {
    public EtapaEntidad construirEntidad(Etapa etapa) {
        return new EtapaEntidad(0L, etapa.getNombre(), etapa.getDescripcion(), etapa.isCompletada());
    }

    public List<EtapaEntidad> construirEntidades(List<Etapa> etapas) {
        return etapas.stream().map(new EtapaMapeador()::construirEntidad).toList();
    }

    public EtapaDTO construirDTO(EtapaEntidad etapa) {
        return new EtapaDTO(etapa.getId(), etapa.getNombre(), etapa.getDescripcion(), etapa.isCompletada());
    }

    public List<EtapaDTO> construirDTOs(List<EtapaEntidad> etapas) {
        return etapas.stream().map(new EtapaMapeador()::construirDTO).toList();
    }

    public void actualizarEntidad(EtapaEntidad entidad, Etapa etapa) {
        entidad.setNombre(entidad.getNombre());
        entidad.setDescripcion(etapa.getDescripcion());
        entidad.setCompletada(etapa.isCompletada());
    }
}