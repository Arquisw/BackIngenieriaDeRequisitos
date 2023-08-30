package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;

public class ServicioGuardarVersionInicial {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioGuardarVersionInicial(RequisitoRepositorioComando requisitoRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public Long ejecutar(Long etapaId) {
        validarSiExisteEtapaConID(etapaId);

        var version = Version.crear(LogicoConstante.ESTADO_VERSION_FINAL);

        return this.requisitoRepositorioComando.guardarVersion(version, etapaId);
    }

    private void validarSiExisteEtapaConID(Long etapaId) {
        if(ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaId);
        }
    }
}
