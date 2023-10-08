package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.factoria.ServicioSiguienteFaseFactoria;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ServicioActualizarFase {
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final ServicioSiguienteFaseFactoria servicioSiguienteFaseFactoria;

    public Long ejecutar(Etapa etapaAnterior, Long etapaID, List<SeleccionDTO> seleccionesDelProyecto) {
        var proyectoID = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID).getProyectoID();
        var ultimaVersionEtapa = this.requisitoRepositorioConsulta.consultarUltimaVersionPorEtapaID(etapaID);
        var requisitosUltimaVersion = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(ultimaVersionEtapa.getId());

        return this.servicioSiguienteFaseFactoria.obtenerEtapaIdDeSiguienteFase(
                etapaAnterior,
                etapaID,
                proyectoID,
                requisitosUltimaVersion,
                seleccionesDelProyecto
        );
    }
}