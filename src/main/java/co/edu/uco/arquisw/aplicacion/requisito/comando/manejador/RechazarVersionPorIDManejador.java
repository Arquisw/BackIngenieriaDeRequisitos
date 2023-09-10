package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.requisito.comando.MotivoRechazoVersionComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica.MotivoRechazoVersionFabrica;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoVariableDeRutaRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioRechazarVersionPorID;
import org.springframework.stereotype.Component;

@Component
public class RechazarVersionPorIDManejador implements ManejadorComandoVariableDeRutaRespuesta<MotivoRechazoVersionComando, Long, ComandoRespuesta<Long>> {
    private final ServicioRechazarVersionPorID servicioRechazarVersionPorID;
    private final MotivoRechazoVersionFabrica motivoRechazoVersionFabrica;

    public RechazarVersionPorIDManejador(ServicioRechazarVersionPorID servicioRechazarVersionPorID, MotivoRechazoVersionFabrica motivoRechazoVersionFabrica) {
        this.servicioRechazarVersionPorID = servicioRechazarVersionPorID;
        this.motivoRechazoVersionFabrica = motivoRechazoVersionFabrica;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(MotivoRechazoVersionComando comando, Long id) {
        return new ComandoRespuesta<>(this.servicioRechazarVersionPorID.ejecutar(this.motivoRechazoVersionFabrica.construir(comando), id));
    }
}