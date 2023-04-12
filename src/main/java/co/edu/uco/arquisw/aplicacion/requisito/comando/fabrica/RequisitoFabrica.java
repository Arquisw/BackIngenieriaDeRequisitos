package co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica;

import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitoComando;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import org.springframework.stereotype.Component;

@Component
public class RequisitoFabrica {
    private final TipoRequisitoFabrica tipoRequisitoFabrica;

    public RequisitoFabrica(TipoRequisitoFabrica tipoRequisitoFabrica) {
        this.tipoRequisitoFabrica = tipoRequisitoFabrica;
    }

    public Requisito construir(RequisitoComando requisito) {
        return Requisito.crear(requisito.getNombre(), requisito.getDescripcion(), this.tipoRequisitoFabrica.construir(requisito.getTipoRequisito()));
    }
}