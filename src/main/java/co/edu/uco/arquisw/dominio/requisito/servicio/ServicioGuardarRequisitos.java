package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ServicioGuardarRequisitos {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public void ejecutar(List<RequisitoDTO> requisitosUltimaVersion, Long etapaId) {
        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, LogicoConstante.NO_ESTA_RECHAZADA);
        var versionId = this.requisitoRepositorioComando.guardarVersion(nuevaVersion, etapaId);

        requisitosUltimaVersion.forEach(requisitoDTO -> {
            var tipoRequisito = TipoRequisito.crear(requisitoDTO.getTipoRequisito().getNombre());
            var requisito = Requisito.crear(requisitoDTO.getNombre(), requisitoDTO.getDescripcion(), tipoRequisito);

            this.requisitoRepositorioComando.guardar(requisito, versionId);
        });
    }

    public void guardarRequisitosSegunEtapa(EtapaDTO etapa, Long versionId, Long proyectoId) {
        var fases = this.faseRepositorioConsulta.consultarFasesPorProyectoID(proyectoId);

        switch (etapa.getNombre()) {
            case TextoConstante.ETAPA_ANALISIS_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_DECLARACION_NOMBRE, versionId);
            case TextoConstante.ETAPA_NEGOCIACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_ANALISIS_NOMBRE, versionId);
            case TextoConstante.ETAPA_VERIFICACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_NEGOCIACION_NOMBRE, versionId);
            case TextoConstante.ETAPA_VALIDACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_VERIFICACION_NOMBRE, versionId);
        }
    }

    private void obtenerRequisitosEtapaAnterior(List<FaseDTO> fases, String nombreEtapaAnterior, Long versionId) {
        fases.forEach(fase -> fase.getEtapas().forEach(etapa -> {
            if (etapa.getNombre().equals(nombreEtapaAnterior)) {
                guardarRequisitosPorEtapaYVersion(etapa.getId(), versionId);
            }
        }));
    }

    private void guardarRequisitosPorEtapaYVersion(Long etapaId, Long versionId) {
        var ultimaVersionEtapa = this.requisitoRepositorioConsulta.consultarUltimaVersionPorEtapaID(etapaId);
        var requisitosUltimaVersion = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(ultimaVersionEtapa.getId());

        requisitosUltimaVersion.forEach(requisitoDTO -> {
            var tipoRequisito = TipoRequisito.crear(requisitoDTO.getTipoRequisito().getNombre());
            var requisito = Requisito.crear(requisitoDTO.getNombre(), requisitoDTO.getDescripcion(), tipoRequisito);

            this.requisitoRepositorioComando.guardar(requisito, versionId);
        });
    }
}