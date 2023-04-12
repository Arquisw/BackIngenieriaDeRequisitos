package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGenerarVersionFinal;
import org.springframework.stereotype.Component;

@Component
public class GenerarVersionFinalManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {
    private final ServicioGenerarVersionFinal servicioGenerarVersionFinal;

    public GenerarVersionFinalManejador(ServicioGenerarVersionFinal servicioGenerarVersionFinal) {
        this.servicioGenerarVersionFinal = servicioGenerarVersionFinal;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioGenerarVersionFinal.ejecutar(comando));
    }
}