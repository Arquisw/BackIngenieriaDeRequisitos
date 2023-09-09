package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitoComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica.RequisitoFabrica;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoVariableDeRutaRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisito;
import org.springframework.stereotype.Component;

@Component
public class GuardarRequisitoManejador implements ManejadorComandoVariableDeRutaRespuesta<RequisitoComando, Long, ComandoRespuesta<Long>> {
    private final ServicioGuardarRequisito servicioGuardarRequisito;
    private final RequisitoFabrica requisitoFabrica;

    public GuardarRequisitoManejador(ServicioGuardarRequisito servicioGuardarRequisito, RequisitoFabrica requisitoFabrica) {
        this.servicioGuardarRequisito = servicioGuardarRequisito;
        this.requisitoFabrica = requisitoFabrica;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(RequisitoComando comando, Long id) {
        return new ComandoRespuesta<>(this.servicioGuardarRequisito.ejecutar(this.requisitoFabrica.construir(comando), id));
    }
}