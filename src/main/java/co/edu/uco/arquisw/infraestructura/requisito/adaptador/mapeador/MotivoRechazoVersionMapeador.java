package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.modelo.MotivoRechazoVersion;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.MotivoRechazoVersionEntidad;
import org.springframework.stereotype.Component;

@Component
public class MotivoRechazoVersionMapeador {
    public MotivoRechazoVersionEntidad construirEntidad(MotivoRechazoVersion motivoRechazoVersion, Long versionId) {
        return new MotivoRechazoVersionEntidad(0L, motivoRechazoVersion.getMotivo(), versionId);
    }
}