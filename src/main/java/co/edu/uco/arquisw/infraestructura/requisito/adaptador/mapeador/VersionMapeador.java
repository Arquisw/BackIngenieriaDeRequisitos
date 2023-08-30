package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.transversal.formateador.FechaFormateador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.VersionEntidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VersionMapeador {
    public VersionEntidad consturirEntidad(Version version, Long etapaID) {
        return new VersionEntidad(0L, version.isEsFinal(), FechaFormateador.obtenerFechaTexto(version.getFecha()), etapaID);
    }

    public VersionDTO consturirDTO(VersionEntidad version) {
        return new VersionDTO(version.getId(), version.isEsFinal(), version.getFecha(), version.getEtapa());
    }

    public List<VersionDTO> construirDTOs(List<VersionEntidad> versiones) {
        return versiones.stream().map(new VersionMapeador()::consturirDTO).toList();
    }

    public void actualizarEntidad(VersionEntidad entidad, Version version) {
        entidad.setEsFinal(version.isEsFinal());
        entidad.setFecha(FechaFormateador.obtenerFechaTexto(version.getFecha()));
    }

    public void actualizarEntidad(VersionEntidad entidad, boolean estado) {
        entidad.setEsFinal(estado);
    }

}