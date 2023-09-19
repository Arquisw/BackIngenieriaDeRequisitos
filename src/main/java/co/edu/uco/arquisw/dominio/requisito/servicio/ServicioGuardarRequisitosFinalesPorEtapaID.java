package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.RequisitosFinales;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioGuardarRequisitosFinalesPorEtapaID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioGuardarRequisitosFinalesPorEtapaID(RequisitoRepositorioComando requisitoRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public Long ejecutar(RequisitosFinales requisitosFinales, Long etapaId) {
        validarSiExisteEtapaConId(etapaId);

        return this.requisitoRepositorioComando.guardarRequisitosFinales(requisitosFinales, etapaId);
    }

    private void validarSiExisteEtapaConId(Long etapaId) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaId);
        }
    }
}
