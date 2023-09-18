package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitosFinalesComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica.RequisitosFinalesFabrica;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoVariableDeRutaRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisitosFinalesPorFaseID;
import org.springframework.stereotype.Component;

@Component
public class GuardarRequisitosFinalesPorFaseIDManejador implements ManejadorComandoVariableDeRutaRespuesta<RequisitosFinalesComando, Long, ComandoRespuesta<Long>> {
    private final ServicioGuardarRequisitosFinalesPorFaseID servicioGuardarRequisitosFinalesPorFaseID;
    private final RequisitosFinalesFabrica requisitosFinalesFabrica;

    public GuardarRequisitosFinalesPorFaseIDManejador(ServicioGuardarRequisitosFinalesPorFaseID servicioGuardarRequisitosFinalesPorFaseID, RequisitosFinalesFabrica requisitosFinalesFabrica) {
        this.servicioGuardarRequisitosFinalesPorFaseID = servicioGuardarRequisitosFinalesPorFaseID;
        this.requisitosFinalesFabrica = requisitosFinalesFabrica;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(RequisitosFinalesComando comando, Long id) {
        return new ComandoRespuesta<>(this.servicioGuardarRequisitosFinalesPorFaseID.ejecutar(this.requisitosFinalesFabrica.construir(comando), id));
    }
}