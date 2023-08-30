package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioRechazarVersiónPorID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioRechazarVersiónPorID(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public Long ejecutar(Long versionId) {
        validarSiExisteVersionConID(versionId);

        var versionDTO = this.requisitoRepositorioConsulta.consultarVersionPorID(versionId);

        this.requisitoRepositorioComando.actualizarVersion(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, versionId);

        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO);

        return this.requisitoRepositorioComando.guardarVersion(nuevaVersion, versionDTO.getEtapaID());
    }

    private void validarSiExisteVersionConID(Long versionId) {
        if(ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_VERSION_CON_EL_ID + versionId);
        }
    }
}
