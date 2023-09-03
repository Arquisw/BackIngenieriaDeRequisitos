package co.edu.uco.arquisw.infraestructura.fase.controlador;

import co.edu.uco.arquisw.aplicacion.fase.consulta.ConsultarEtapaPorIDManejador;
import co.edu.uco.arquisw.aplicacion.fase.consulta.ConsultarFasesPorProyectoIDManejador;
import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fases")
@Tag(name = "Consulta de la Fase Controlador")
public class FaseConsultaControlador {
    private final ConsultarEtapaPorIDManejador consultarEtapaPorIDManejador;
    private final ConsultarFasesPorProyectoIDManejador consultarFasesPorProyectoIDManejador;

    public FaseConsultaControlador(ConsultarEtapaPorIDManejador consultarEtapaPorIDManejador, ConsultarFasesPorProyectoIDManejador consultarFasesPorProyectoIDManejador) {
        this.consultarEtapaPorIDManejador = consultarEtapaPorIDManejador;
        this.consultarFasesPorProyectoIDManejador = consultarFasesPorProyectoIDManejador;
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA')")
    @GetMapping("/etapa/{id}")
    @Operation(summary = "Consultar por ID", description = "Este es usado para consultar una etapa por medio de su ID")
    public EtapaDTO consultarPorEtapaId(@PathVariable Long id) {
        return this.consultarEtapaPorIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA')")
    @GetMapping("/proyecto/{id}")
    @Operation(summary = "Consultar Fases por Proyecto ID", description = "Este es usado para consultar todas las fases existentes de un proyecto por medio del ID del proyecto")
    public List<FaseDTO> consultarFasesPorProyectoId(@PathVariable Long id) {
        return this.consultarFasesPorProyectoIDManejador.ejecutar(id);
    }
}