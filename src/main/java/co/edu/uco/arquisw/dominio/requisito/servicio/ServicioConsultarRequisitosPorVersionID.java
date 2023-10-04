package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import org.springframework.data.domain.Page;

import java.util.List;

public class ServicioConsultarRequisitosPorVersionID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioConsultarRequisitosPorVersionID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public Page<RequisitoDTO> ejecutar(Long versionID, int pagina, int tamano) {
        validarSiExisteVersionConID(versionID);

        return this.requisitoRepositorioConsulta.consultarRequisitosPorVersionIDPaginado(versionID,pagina,tamano);
    }

    private void validarSiExisteVersionConID(Long versionID) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_VERSION_CON_EL_ID + versionID);
        }
    }
}