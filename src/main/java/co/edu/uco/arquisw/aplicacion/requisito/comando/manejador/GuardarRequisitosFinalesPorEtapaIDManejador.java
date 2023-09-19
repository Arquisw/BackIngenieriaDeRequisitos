package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitosFinalesComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica.RequisitosFinalesFabrica;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoVariableDeRutaRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisitosFinalesPorEtapaID;
import org.springframework.stereotype.Component;

@Component
public class GuardarRequisitosFinalesPorEtapaIDManejador implements ManejadorComandoVariableDeRutaRespuesta<RequisitosFinalesComando, Long, ComandoRespuesta<Long>> {
    private final ServicioGuardarRequisitosFinalesPorEtapaID servicioGuardarRequisitosFinalesPorEtapaID;
    private final RequisitosFinalesFabrica requisitosFinalesFabrica;

    public GuardarRequisitosFinalesPorEtapaIDManejador(ServicioGuardarRequisitosFinalesPorEtapaID servicioGuardarRequisitosFinalesPorEtapaID, RequisitosFinalesFabrica requisitosFinalesFabrica) {
        this.servicioGuardarRequisitosFinalesPorEtapaID = servicioGuardarRequisitosFinalesPorEtapaID;
        this.requisitosFinalesFabrica = requisitosFinalesFabrica;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(RequisitosFinalesComando comando, Long id) {
        return new ComandoRespuesta<>(this.servicioGuardarRequisitosFinalesPorEtapaID.ejecutar(this.requisitosFinalesFabrica.construir(comando), id));
    }
}