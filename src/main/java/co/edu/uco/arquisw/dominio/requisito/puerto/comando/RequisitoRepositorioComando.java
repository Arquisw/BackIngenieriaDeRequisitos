package co.edu.uco.arquisw.dominio.requisito.puerto.comando;

import co.edu.uco.arquisw.dominio.requisito.modelo.MotivoRechazoVersion;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;

public interface RequisitoRepositorioComando {
    Long guardar(Requisito requisito, Long versionId);

    Long actualizar(Requisito requisito, Long id);

    void eliminar(Long id);

    Long guardarVersion(Version version, Long etapaID);

    Long actualizarVersion(Version version, Long versionID);

    void actualizarVersion(boolean estado, boolean rechazado, Long versionID);

    void guardarMotivoRechazoVersion(MotivoRechazoVersion motivoRechazoVersion, Long versionId);
}