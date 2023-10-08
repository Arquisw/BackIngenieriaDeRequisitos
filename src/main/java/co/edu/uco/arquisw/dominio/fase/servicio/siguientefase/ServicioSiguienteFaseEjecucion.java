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

public class ServicioSiguienteFaseEjecucion extends ServicioSiguienteFase {
    public ServicioSiguienteFaseEjecucion(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos) {
        super(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos);
    }

    @Override
    public Long construirSiguienteFase(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion) {
        return this.construirContenidoSiguienteFase(proyectoId, requisitosUltimaVersion, TextoConstante.FASE_EJECUCION_NOMBRE, TextoConstante.FASE_EJECUCION_DESCRIPCION, TextoConstante.ETAPA_NEGOCIACION_NOMBRE, TextoConstante.ETAPA_NEGOCIACION_DESCRIPCION);
    }

    @Override
    public Long construirSiguienteEtapa(Etapa etapaAnterior, Long etapaID, List<RequisitoDTO> requisitosUltimaVersion) {
        return NumeroConstante.CERO;
    }

    @Override
    public Long construirFaseCierre(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion, List<SeleccionDTO> seleccionesDelProyecto) {
        return NumeroConstante.CERO;
    }
}