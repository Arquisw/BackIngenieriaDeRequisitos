package co.edu.uco.arquisw.dominio.fase.servicio.siguientefase;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioCerrarProcesoDeIngenieriaDeRequisitos;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConstruirNuevaFase;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisitos;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import lombok.Getter;

import java.util.List;

@Getter
public class ServicioSiguienteFaseCierre extends ServicioSiguienteFase {
    private final ServicioCerrarProcesoDeIngenieriaDeRequisitos servicioCerrarProcesoDeIngenieriaDeRequisitos;

    public ServicioSiguienteFaseCierre(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos, ServicioCerrarProcesoDeIngenieriaDeRequisitos servicioCerrarProcesoDeIngenieriaDeRequisitos) {
        super(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos);

        this.servicioCerrarProcesoDeIngenieriaDeRequisitos = servicioCerrarProcesoDeIngenieriaDeRequisitos;
    }

    @Override
    public Long construirSiguienteFase(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion) {
        return NumeroConstante.CERO;
    }

    @Override
    public Long construirSiguienteEtapa(Etapa etapaAnterior, Long etapaID, List<RequisitoDTO> requisitosUltimaVersion) {
        return NumeroConstante.CERO;
    }

    @Override
    public Long construirFaseCierre(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion, List<SeleccionDTO> seleccionesDelProyecto) {
        var fase = this.getServicioConstruirNuevaFase().construirFaseFinal();
        var faseId = this.getFaseRepositorioComando().guardar(fase, proyectoId);
        var nuevaFase = this.getFaseRepositorioConsulta().consultarFasePorID(faseId);

        var id = nuevaFase.getEtapas().get(0).getId();
        this.getServicioGuardarRequisitos().ejecutar(requisitosUltimaVersion, id);

        this.servicioCerrarProcesoDeIngenieriaDeRequisitos.ejecutar(requisitosUltimaVersion, proyectoId, seleccionesDelProyecto);

        return id;
    }
}