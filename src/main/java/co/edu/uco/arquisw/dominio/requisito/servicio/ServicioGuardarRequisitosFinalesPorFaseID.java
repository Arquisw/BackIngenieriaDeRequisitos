package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.RequisitosFinales;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioGuardarRequisitosFinalesPorFaseID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioGuardarRequisitosFinalesPorFaseID(RequisitoRepositorioComando requisitoRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public Long ejecutar(RequisitosFinales requisitosFinales, Long faseId) {
        validarSiExisteFaseConId(faseId);

        return this.requisitoRepositorioComando.guardarRequisitosFinales(requisitosFinales, faseId);
    }

    private void validarSiExisteFaseConId(Long faseId) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarFasePorID(faseId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_FASE_CON_EL_ID + faseId);
        }
    }
}
