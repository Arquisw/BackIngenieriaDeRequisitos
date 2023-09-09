package co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica;

import co.edu.uco.arquisw.aplicacion.requisito.comando.TipoRequisitoComando;
import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;
import org.springframework.stereotype.Component;

@Component
public class TipoRequisitoFabrica {
    public TipoRequisito construir(TipoRequisitoComando tipoRequisito) {
        return TipoRequisito.crear(tipoRequisito.getNombre());
    }
}