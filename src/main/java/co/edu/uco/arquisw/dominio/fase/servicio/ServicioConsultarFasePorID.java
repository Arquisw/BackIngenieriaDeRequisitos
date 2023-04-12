package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioConsultarFasePorID {
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioConsultarFasePorID(FaseRepositorioConsulta faseRepositorioConsulta) {
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public FaseDTO ejecutar(Long faseID) {
        validarSiExisteFaseConID(faseID);

        return this.faseRepositorioConsulta.consultarFasePorID(faseID);
    }

    private void validarSiExisteFaseConID(Long faseID) {
        if(ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarFasePorID(faseID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_FASE_CON_EL_ID + faseID);
        }
    }
}