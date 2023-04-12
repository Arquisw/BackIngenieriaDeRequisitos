package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import java.util.List;

public class ServicioConsultarVersionesPorEtapaID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioConsultarVersionesPorEtapaID(RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public List<VersionDTO> ejecutar(Long etapaID) {
        validarSiExisteEtapaConID(etapaID);

        return this.requisitoRepositorioConsulta.consultarVersionesPorEtapaID(etapaID);
    }

    private void validarSiExisteEtapaConID(Long etapaID) {
        if(ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_PROYECTO_CON_EL_ID + etapaID);
        }
    }
}