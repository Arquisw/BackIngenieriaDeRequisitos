package co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica;

import co.edu.uco.arquisw.aplicacion.requisito.comando.MotivoRechazoVersionComando;
import co.edu.uco.arquisw.dominio.requisito.modelo.MotivoRechazoVersion;
import org.springframework.stereotype.Component;

@Component
public class MotivoRechazoVersionFabrica {
    public MotivoRechazoVersion construir(MotivoRechazoVersionComando motivoRechazoVersionComando) {
        return MotivoRechazoVersion.crear(motivoRechazoVersionComando.getMotivoRechazo());
    }
}