package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitosFinalesDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioConsultarRequisitosFinalesPorEtapaID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioConsultarRequisitosFinalesPorEtapaID(RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public RequisitosFinalesDTO ejecutar(Long etapaId) {
        validarSiExisteEtapaConId(etapaId);

        return this.requisitoRepositorioConsulta.consultarRequisitosFinalesPorEtapaID(etapaId);
    }

    private void validarSiExisteEtapaConId(Long etapaId) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarFasePorID(etapaId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaId);
        }
    }
}
