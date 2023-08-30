package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioEliminarRequisitoPorID;
import org.springframework.stereotype.Component;

@Component
public class EliminarRequisitoManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {
    private final ServicioEliminarRequisitoPorID servicioEliminarRequisitoPorId;

    public EliminarRequisitoManejador(ServicioEliminarRequisitoPorID servicioEliminarRequisitoPorId) {
        this.servicioEliminarRequisitoPorId = servicioEliminarRequisitoPorId;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioEliminarRequisitoPorId.ejecutar(comando));
    }
}