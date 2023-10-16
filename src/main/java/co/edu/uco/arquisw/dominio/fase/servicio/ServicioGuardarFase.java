package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

import java.util.List;

public class ServicioGuardarFase {
    private final FaseRepositorioComando faseRepositorioComando;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;

    public ServicioGuardarFase(FaseRepositorioComando faseRepositorioComando, ProyectoRepositorioConsulta proyectoRepositorioConsulta) {
        this.faseRepositorioComando = faseRepositorioComando;
        this.proyectoRepositorioConsulta = proyectoRepositorioConsulta;
    }

    public Long ejecutar(Long proyectoID) {
        validarSiExisteProyectoConID(proyectoID);

        var fase = construirPrimeraFase();

        return this.faseRepositorioComando.guardar(fase, proyectoID);
    }

    private void validarSiExisteProyectoConID(Long proyectoID) {
        if (ValidarObjeto.esNulo(this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoID))) {
            throw new NullPointerException(Mensajes.obtenerNoExiteProyectoConId(proyectoID));
        }
    }

    public Fase construirPrimeraFase() {
        return Fase.crear(TextoConstante.FASE_INICIO_NOMBRE, TextoConstante.FASE_INICIO_DESCRIPCION, construirPrimerasEtapas());
    }

    public List<Etapa> construirPrimerasEtapas() {
        var primeraEtapa = Etapa.crear(TextoConstante.ETAPA_DECLARACION_NOMBRE, TextoConstante.ETAPA_DECLARACION_DESCRIPCION, LogicoConstante.ESTADO_ETAPA_POR_DEFECTO);

        return List.of(primeraEtapa);
    }
}