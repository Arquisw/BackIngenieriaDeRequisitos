package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioActualizarRequisito {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioActualizarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public Long ejecutar(Requisito requisito, Long requisitoID) {
        var versionID = validarSiExisteRequisitoConID(requisitoID);

        versionID = actualizarVersion(versionID, requisitoID);

        return this.requisitoRepositorioComando.actualizar(requisito, requisitoID, versionID);
    }

    private Long validarSiExisteRequisitoConID(Long requisitoID) {
        var requisito = this.requisitoRepositorioConsulta.consultarRequisitoPorID(requisitoID);

        if(ValidarObjeto.esNulo(requisito)) {
            throw new NullPointerException(Mensajes.NO_EXISTE_REQUISITO_CON_EL_ID + requisitoID);
        }

        return requisito.getVersionID();
    }

    private Long actualizarVersion(Long versionID, Long requisitoID) {
        var requisito = this.requisitoRepositorioConsulta.consultarRequisitoPorID(requisitoID);
        var version = this.requisitoRepositorioConsulta.consultarVersionPorID(versionID);
        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO);

        if(version.isEsFinal()) {
            versionID = this.requisitoRepositorioComando.guardarVersion(nuevaVersion, requisito.getEtapaID());
        } else {
            versionID = this.requisitoRepositorioComando.actualizarVersion(nuevaVersion, version.getId());
        }

        return versionID;
    }
}