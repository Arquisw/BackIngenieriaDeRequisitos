package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import java.util.List;

public class ServicioConsultarRequisitosPorEtapaID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioConsultarRequisitosPorEtapaID(RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public List<RequisitoDTO> ejecutar(Long etapaID) {
        validarSiExisteEtapaConID(etapaID);

        return this.requisitoRepositorioConsulta.consultarRequisitosPorEtapaID(etapaID);
    }

    private void validarSiExisteEtapaConID(Long etapaID) {
        if(ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaID);
        }
    }
}