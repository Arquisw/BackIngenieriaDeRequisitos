package co.edu.uco.arquisw.aplicacion.requisito.comando.fabrica;

import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitosFinalesComando;
import co.edu.uco.arquisw.dominio.requisito.modelo.RequisitosFinales;
import org.springframework.stereotype.Component;

@Component
public class RequisitosFinalesFabrica {
    public RequisitosFinales construir(RequisitosFinalesComando requisitosFinales) {
        return RequisitosFinales.crear(requisitosFinales.getRutaArchivo());
    }
}