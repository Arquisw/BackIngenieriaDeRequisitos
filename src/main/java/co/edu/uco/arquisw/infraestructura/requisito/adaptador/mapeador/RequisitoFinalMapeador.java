package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoFinalEntidad;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequisitoFinalMapeador {
    public RequisitoFinalEntidad consturirEntidad(RequisitoDTO requisito, Long proyectoId) {
        return new RequisitoFinalEntidad(NumeroConstante.CERO, proyectoId, requisito.getNombre(), requisito.getDescripcion(), requisito.getTipoRequisito().getNombre(), LogicoConstante.NO_CALIFICADO);
    }

    public List<RequisitoFinalEntidad> consturirEntidades(List<RequisitoDTO> requisitosUltimaVersion, Long proyectoID) {
        var entidades = new ArrayList<RequisitoFinalEntidad>();

        requisitosUltimaVersion.forEach(requisitoDTO -> entidades.add(this.consturirEntidad(requisitoDTO, proyectoID)));

        return entidades;
    }
}
