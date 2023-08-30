package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoEntidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequisitoMapeador {
    private final TipoRequisitoMapeador tipoRequisitoMapeador;

    public RequisitoMapeador(TipoRequisitoMapeador tipoRequisitoMapeador) {
        this.tipoRequisitoMapeador = tipoRequisitoMapeador;
    }

    public RequisitoEntidad consturirEntidad(Requisito requisito, Long versionId) {
        return new RequisitoEntidad(0L, requisito.getNombre(), requisito.getDescripcion(), this.tipoRequisitoMapeador.construirEntidad(requisito.getTipoRequisito()), versionId);
    }

    public RequisitoDTO consturirDTO(RequisitoEntidad requisito) {
        return new RequisitoDTO(requisito.getId(), requisito.getNombre(), requisito.getDescripcion(), this.tipoRequisitoMapeador.construirDTO(requisito.getTipoRequisito()), requisito.getVersion());
    }

    public List<RequisitoDTO> construirDTOs(List<RequisitoEntidad> requisitos) {
        return requisitos.stream().map(new RequisitoMapeador(tipoRequisitoMapeador)::consturirDTO).toList();
    }

    public void actualizarRequisito(RequisitoEntidad entidad, Requisito requisito) {
        entidad.setNombre(requisito.getNombre());
        entidad.setDescripcion(requisito.getDescripcion());
        entidad.getTipoRequisito().getTipoRequisito().setNombre(requisito.getTipoRequisito().getNombre());
    }
}