package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitoComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica.RequisitoFabrica;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoActualizacionRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioActualizarRequisito;
import org.springframework.stereotype.Component;

@Component
public class ActualizarRequisitoManejador implements ManejadorComandoActualizacionRespuesta<RequisitoComando, Long, ComandoRespuesta<Long>> {
    private final ServicioActualizarRequisito servicioActualizarRequisito;
    private final RequisitoFabrica requisitoFabrica;

    public ActualizarRequisitoManejador(ServicioActualizarRequisito servicioActualizarRequisito, RequisitoFabrica requisitoFabrica) {
        this.servicioActualizarRequisito = servicioActualizarRequisito;
        this.requisitoFabrica = requisitoFabrica;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(RequisitoComando comando, Long id) {
        return new ComandoRespuesta<>(this.servicioActualizarRequisito.ejecutar(this.requisitoFabrica.construir(comando), id));
    }
}