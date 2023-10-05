package co.edu.uco.arquisw.dominio.fase.servicio.siguientefase;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConstruirNuevaFase;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisitos;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class ServicioSiguienteFase {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final ServicioConstruirNuevaFase servicioConstruirNuevaFase;
    private final FaseRepositorioComando faseRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final ServicioGuardarRequisitos servicioGuardarRequisitos;

    protected ServicioSiguienteFase(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.servicioConstruirNuevaFase = servicioConstruirNuevaFase;
        this.faseRepositorioComando = faseRepositorioComando;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
        this.servicioGuardarRequisitos = servicioGuardarRequisitos;
    }

    public abstract Long construirSiguienteFase(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion);

    public abstract Long construirSiguienteEtapa(Etapa etapaAnterior, Long etapaID, List<RequisitoDTO> requisitosUltimaVersion);

    public abstract Long construirFaseCierre(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion, List<SeleccionDTO> seleccionesDelProyecto);

    public Long construirContenidoSiguienteFase(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion, String nombreSiguienteFase, String descripcionSiguienteFase, String nombreSiguienteEtapa, String descripcionSiguienteEtapa) {
        var fase = this.servicioConstruirNuevaFase.ejecutar(nombreSiguienteFase, descripcionSiguienteFase, nombreSiguienteEtapa, descripcionSiguienteEtapa);

        var faseId = this.faseRepositorioComando.guardar(fase, proyectoId);

        var nuevaFase = this.faseRepositorioConsulta.consultarFasePorID(faseId);

        var id = nuevaFase.getEtapas().get(0).getId();

        this.servicioGuardarRequisitos.ejecutar(requisitosUltimaVersion, id);

        return id;
    }
}
