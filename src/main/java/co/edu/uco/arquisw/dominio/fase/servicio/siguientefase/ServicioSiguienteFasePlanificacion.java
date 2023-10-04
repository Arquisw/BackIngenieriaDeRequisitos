package co.edu.uco.arquisw.dominio.fase.servicio.siguientefase;

import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConstruirNuevaFase;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisitos;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;

import java.util.List;

public class ServicioSiguienteFasePlanificacion extends ServicioSiguienteFase {
    public ServicioSiguienteFasePlanificacion(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos) {
        super(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos);
    }

    @Override
    public Long construirSiguienteFase(Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion) {
        return this.construirContenidoSiguienteFase(proyectoId, requisitosUltimaVersion, TextoConstante.FASE_PLANIFICACION_NOMBRE, TextoConstante.FASE_PLANIFICACION_DESCRIPCION, TextoConstante.ETAPA_ANALISIS_NOMBRE, TextoConstante.ETAPA_ANALISIS_DESCRIPCION);
    }
}
