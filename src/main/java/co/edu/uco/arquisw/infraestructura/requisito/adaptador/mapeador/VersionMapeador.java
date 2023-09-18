package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.transversal.formateador.FechaFormateador;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.VersionEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.MotivoRechazoVersionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VersionMapeador {
    @Autowired
    MotivoRechazoVersionDAO motivoRechazoVersionDAO;

    public VersionEntidad consturirEntidad(Version version, Long etapaID) {
        return new VersionEntidad(0L, version.isEsFinal(), version.isEstaRechazada(), FechaFormateador.obtenerFechaTexto(version.getFecha()), etapaID);
    }

    public VersionDTO construirDTO(VersionEntidad version) {
        return new VersionDTO(version.getId(), version.isEsFinal(), version.isEstaRechazado(), obtenerMotivoRechazo(version), version.getFecha(), version.getEtapa());
    }

    private String obtenerMotivoRechazo(VersionEntidad version) {
        if (version.isEstaRechazado()) {
            return this.motivoRechazoVersionDAO.findByVersion(version.getId()).getMotivo();
        }

        return TextoConstante.VACIO;
    }

    public List<VersionDTO> construirDTOs(List<VersionEntidad> versiones) {
        return versiones.stream().map(new VersionMapeador()::construirDTO).toList();
    }

    public void actualizarEntidad(VersionEntidad entidad, Version version) {
        entidad.setEsFinal(version.isEsFinal());
        entidad.setFecha(FechaFormateador.obtenerFechaTexto(version.getFecha()));
    }

    public void actualizarEntidad(VersionEntidad entidad, boolean estado, boolean rechazado) {
        entidad.setEsFinal(estado);
        entidad.setEstaRechazado(rechazado);
    }

}