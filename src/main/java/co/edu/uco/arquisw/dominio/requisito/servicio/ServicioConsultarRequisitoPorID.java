package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioConsultarRequisitoPorID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioConsultarRequisitoPorID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public RequisitoDTO ejecutar(Long id) {
        validarSiExisteRequisitoConID(id);

        return this.requisitoRepositorioConsulta.consultarRequisitoPorID(id);
    }

    private void validarSiExisteRequisitoConID(Long id) {
        if(ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarRequisitoPorID(id))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_REQUISITO_CON_EL_ID + id);
        }
    }
}