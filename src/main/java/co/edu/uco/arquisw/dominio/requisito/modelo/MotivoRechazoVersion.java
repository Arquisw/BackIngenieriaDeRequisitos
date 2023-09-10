package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

@Getter
public class MotivoRechazoVersion {
    private String motivo;

    private MotivoRechazoVersion(String motivo) {
        setMotivo(motivo);
    }

    public static MotivoRechazoVersion crear(String motivo) {
        return new MotivoRechazoVersion(motivo);
    }

    private void setMotivo(String motivo) {
        ValidarTexto.validarObligatorio(motivo, Mensajes.MOTIVO_RECHAZO_VERSION_NO_PUEDE_ESTAR_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(motivo, Mensajes.PATRON_MOTIVO_RECHAZO_VERSION_NO_ES_VALIDO);

        this.motivo = motivo;
    }
}