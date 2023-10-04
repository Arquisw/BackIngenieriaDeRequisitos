package co.edu.uco.arquisw.aplicacion.transversal.manejador;

import org.springframework.transaction.annotation.Transactional;

public interface ManejadorComandoPaginacion <C, L, D, R>{
    @Transactional
    R ejecutar(C comando, L pagina, D tamano);
}
