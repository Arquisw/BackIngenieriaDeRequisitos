package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import java.util.List;

public class ServicioObtenerVersionFinal {
    public VersionDTO ejecutar(List<VersionDTO> versiones) {
        return versiones.get(versiones.size() - 1 );
    }
}