package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitosFinalesDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioConsultarRequisitosFinalesPorFaseID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioConsultarRequisitosFinalesPorFaseID(RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public RequisitosFinalesDTO ejecutar(Long faseId) {
        validarSiExisteFaseConId(faseId);

        return this.requisitoRepositorioConsulta.consultarRequisitosFinalesPorFaseID(faseId);
    }

    private void validarSiExisteFaseConId(Long faseId) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarFasePorID(faseId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_FASE_CON_EL_ID + faseId);
        }
    }
}
