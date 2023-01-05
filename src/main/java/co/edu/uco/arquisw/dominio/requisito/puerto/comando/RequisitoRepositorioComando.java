package co.edu.uco.arquisw.dominio.requisito.puerto.comando;

import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;

public interface RequisitoRepositorioComando {
    Long guardar(Requisito requisito, Long etapaID, Long versionID);
    Long actualizar(Requisito requisito, Long etapaID, Long versionID);
    void eliminar(Long id);
    Long guardarVersion(Version version, Long etapaID);
    Long actualizarVersion(Version version, Long versionID);
}