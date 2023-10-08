package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioConsultarEtapaPorID {
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioConsultarEtapaPorID(FaseRepositorioConsulta faseRepositorioConsulta) {
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public EtapaDTO ejecutar(Long etapaId) {
        validarSiExisteEtapaConID(etapaId);

        return this.faseRepositorioConsulta.consultarEtapaPorID(etapaId);
    }

    private void validarSiExisteEtapaConID(Long etapaId) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaId))) {
            throw new NullPointerException(Mensajes.obtenerNoExiteEtapaConId(etapaId));
        }
    }
}