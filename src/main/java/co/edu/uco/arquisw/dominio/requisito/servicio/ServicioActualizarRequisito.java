package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioActualizarRequisito {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioActualizarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public Long ejecutar(Requisito requisito, Long id) {
        validarSiExisteRequisitoConID(id);

        return this.requisitoRepositorioComando.actualizar(requisito, id);
    }

    private void validarSiExisteRequisitoConID(Long requisitoID) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarRequisitoPorID(requisitoID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_REQUISITO_CON_EL_ID + requisitoID);
        }
    }
}