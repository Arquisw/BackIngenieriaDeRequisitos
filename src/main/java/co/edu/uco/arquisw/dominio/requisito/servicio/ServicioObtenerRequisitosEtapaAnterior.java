package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AutorizacionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServicioObtenerRequisitosEtapaAnterior {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final ServicioValidarSiRequisitosSonIguales servicioValidarSiRequisitosSonIguales;

    public void ejecutar(EtapaDTO etapa, Long versionId, Long proyectoId, String mensajeExcepcionUno, String mensajeExcepcionDos) {
        var fases = this.faseRepositorioConsulta.consultarFasesPorProyectoID(proyectoId);
        var requisitosVersionActual = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(versionId);

        switch (etapa.getNombre()) {
            case TextoConstante.ETAPA_ANALISIS_NOMBRE ->
                    this.servicioValidarSiRequisitosSonIguales.ejecutar(fases, TextoConstante.ETAPA_DECLARACION_NOMBRE, requisitosVersionActual, mensajeExcepcionDos);
            case TextoConstante.ETAPA_NEGOCIACION_NOMBRE ->
                    this.servicioValidarSiRequisitosSonIguales.ejecutar(fases, TextoConstante.ETAPA_ANALISIS_NOMBRE, requisitosVersionActual, mensajeExcepcionDos);
            case TextoConstante.ETAPA_VERIFICACION_NOMBRE ->
                    this.servicioValidarSiRequisitosSonIguales.ejecutar(fases, TextoConstante.ETAPA_NEGOCIACION_NOMBRE, requisitosVersionActual, mensajeExcepcionDos);
            case TextoConstante.ETAPA_VALIDACION_NOMBRE ->
                    this.servicioValidarSiRequisitosSonIguales.ejecutar(fases, TextoConstante.ETAPA_VERIFICACION_NOMBRE, requisitosVersionActual, mensajeExcepcionDos);
            default -> {
                if (requisitosVersionActual.isEmpty()) {
                    throw new AutorizacionExcepcion(mensajeExcepcionUno);
                }
            }
        }
    }
}