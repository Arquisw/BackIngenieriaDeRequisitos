package co.edu.uco.arquisw.aplicacion.fase.comando.manejador;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioGuardarFase;
import org.springframework.stereotype.Component;

@Component
public class GuardarFaseManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {
    private final ServicioGuardarFase servicioGuardarFase;

    public GuardarFaseManejador(ServicioGuardarFase servicioGuardarFase) {
        this.servicioGuardarFase = servicioGuardarFase;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioGuardarFase.ejecutar(comando));
    }
}