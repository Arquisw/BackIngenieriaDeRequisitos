package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import java.util.List;

public class ServicioGuardarRequisito {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final ServicioObtenerVersionFinal servicioObtenerVersionFinal;

    public ServicioGuardarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, ServicioObtenerVersionFinal servicioObtenerVersionFinal) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
        this.servicioObtenerVersionFinal = servicioObtenerVersionFinal;
    }

    public Long ejecutar(Requisito requisito, Long etapaID) {
        validarSiExisteEtapaConID(etapaID);

        var versionID = generarVersion(etapaID);

        return this.requisitoRepositorioComando.guardar(requisito, etapaID, versionID);
    }

    private void validarSiExisteEtapaConID(Long etapaID) {
        if(ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaID);
        }
    }

    private Long generarVersion(Long etapaID) {
        Long versionID;
        var versiones = this.requisitoRepositorioConsulta.consultarVersionesPorEtapaID(etapaID);

        if(versiones.isEmpty() || ultimaVersionEsFinal(versiones)) {
            var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO);

            versionID = this.requisitoRepositorioComando.guardarVersion(nuevaVersion, etapaID);
        } else {
            var ultimaVersion = this.servicioObtenerVersionFinal.ejecutar(versiones);
            var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO);

            versionID = this.requisitoRepositorioComando.actualizarVersion(nuevaVersion, ultimaVersion.getId());
        }

        return versionID;
    }

    private boolean ultimaVersionEsFinal(List<VersionDTO> versiones) {
        var ultimaVersion = this.servicioObtenerVersionFinal.ejecutar(versiones);

        return ultimaVersion.isEsFinal();
    }
}