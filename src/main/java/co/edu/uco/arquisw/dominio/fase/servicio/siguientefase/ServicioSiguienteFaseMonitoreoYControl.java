package co.edu.uco.arquisw.dominio.fase.servicio.siguientefase;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConstruirNuevaFase;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisitos;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;

import java.util.List;

public class ServicioSiguienteFaseMonitoreoYControl extends ServicioSiguienteFase {
    public ServicioSiguienteFaseMonitoreoYControl(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos) {
        super(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos);
    }

    @Override
    public Long construirSiguienteFase(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion) {
        return this.construirContenidoSiguienteFase(proyectoId, requisitosUltimaVersion, TextoConstante.FASE_MONITOREO_Y_CONTROL_NOMBRE, TextoConstante.FASE_MONITOREO_Y_CONTROL_DESCRIPCION, TextoConstante.ETAPA_VERIFICACION_NOMBRE, TextoConstante.ETAPA_VERIFICACION_DESCRIPCION);
    }

    @Override
    public Long construirSiguienteEtapa(Etapa etapaAnterior, Long etapaID, List<RequisitoDTO> requisitosUltimaVersion) {
        var fase = this.getServicioConstruirNuevaFase().construirFaseActualizada(etapaAnterior);
        var faseID = this.getFaseRepositorioConsulta().consultarFasePorEtapaID(etapaID).getId();

        var faseId = this.getFaseRepositorioComando().actualizar(fase, faseID);

        var nuevaFase = this.getFaseRepositorioConsulta().consultarFasePorID(faseId);

        var id = nuevaFase.getEtapas().get(1).getId();

        this.getServicioGuardarRequisitos().ejecutar(requisitosUltimaVersion, id);

        return id;
    }

    @Override
    public Long construirFaseCierre(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion, List<SeleccionDTO> seleccionesDelProyecto) {
        return NumeroConstante.CERO;
    }
}