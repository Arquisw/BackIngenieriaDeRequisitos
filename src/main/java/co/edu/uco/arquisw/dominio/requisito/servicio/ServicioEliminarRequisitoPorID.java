package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioEliminarRequisitoPorID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioEliminarRequisitoPorID(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public Long ejecutar(Long id) {
        validarSiExisteRequisitoConID(id);

        this.requisitoRepositorioComando.eliminar(id);

        return id;
    }

    private void validarSiExisteRequisitoConID(Long id) {
        if(ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarRequisitoPorID(id))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_REQUISITO_CON_EL_ID + id);
        }
    }
}