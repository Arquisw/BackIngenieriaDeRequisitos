package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

import java.util.List;

public class ServicioConsultarFasesPorProyectoID {
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioConsultarFasesPorProyectoID(FaseRepositorioConsulta faseRepositorioConsulta) {
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public List<FaseDTO> ejecutar(Long proyectoID) {
        validarSiExisteProyectoConID(proyectoID);

        return this.faseRepositorioConsulta.consultarFasesPorProyectoID(proyectoID);
    }

    private void validarSiExisteProyectoConID(Long proyectoID) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarFasesPorProyectoID(proyectoID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_PROYECTO_CON_EL_ID + proyectoID);
        }
    }
}