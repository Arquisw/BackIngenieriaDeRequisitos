package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioConsultarVersionPorID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioConsultarVersionPorID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public VersionDTO ejecutar(Long versionID) {
        validarSiExisteVersionConID(versionID);

        return this.requisitoRepositorioConsulta.consultarVersionPorID(versionID);
    }

    private void validarSiExisteVersionConID(Long versionID) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionID))) {
            throw new NullPointerException(Mensajes.obtenerNoExiteVersionConId(versionID));
        }
    }
}