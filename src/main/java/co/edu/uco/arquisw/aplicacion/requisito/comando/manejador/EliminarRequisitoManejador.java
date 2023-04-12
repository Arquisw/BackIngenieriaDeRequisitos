package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioEliminarRequisito;
import org.springframework.stereotype.Component;

@Component
public class EliminarRequisitoManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {
    private final ServicioEliminarRequisito servicioEliminarRequisito;

    public EliminarRequisitoManejador(ServicioEliminarRequisito servicioEliminarRequisito) {
        this.servicioEliminarRequisito = servicioEliminarRequisito;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioEliminarRequisito.ejecutar(comando));
    }
}