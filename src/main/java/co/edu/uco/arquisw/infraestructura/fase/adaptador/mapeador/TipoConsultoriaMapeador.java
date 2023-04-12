package co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.fase.dto.TipoConsultoriaDTO;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.TipoConsultoriaProyectoEntidad;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TipoConsultoriaMapeador {
    public TipoConsultoriaDTO construirDTO(TipoConsultoriaProyectoEntidad tipoConsultoria) {
        return new TipoConsultoriaDTO(tipoConsultoria.getTipoConsultoria().getNombre());
    }

    public List<TipoConsultoriaDTO> construirDTOs(List<TipoConsultoriaProyectoEntidad> roles) {
        return roles.stream().map(new TipoConsultoriaMapeador()::construirDTO).toList();
    }
}