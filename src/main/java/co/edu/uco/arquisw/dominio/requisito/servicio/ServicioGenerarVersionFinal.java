package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioGenerarVersionFinal {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final ServicioObtenerVersionFinal servicioObtenerVersionFinal;

    public ServicioGenerarVersionFinal(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, ServicioObtenerVersionFinal servicioObtenerVersionFinal) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
        this.servicioObtenerVersionFinal = servicioObtenerVersionFinal;
    }

    public Long ejecutar(Long etapaID) {
        validarSiExisteEtapaConID(etapaID);

        var ultimaVersionID = obtenerUltimaVersionNoFinalID(etapaID);
        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_FINAL);

        return this.requisitoRepositorioComando.actualizarVersion(nuevaVersion, ultimaVersionID);
    }

    private void validarSiExisteEtapaConID(Long etapaID) {
        if(ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaID);
        }
    }

    private Long obtenerUltimaVersionNoFinalID(Long etapaID) {
        var versiones = requisitoRepositorioConsulta.consultarVersionesPorEtapaID(etapaID);

        if(versiones.isEmpty() || this.servicioObtenerVersionFinal.ejecutar(versiones).isEsFinal()) {
            throw new AccionExcepcion(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID);
        }

        return this.servicioObtenerVersionFinal.ejecutar(versiones).getId();
    }
}