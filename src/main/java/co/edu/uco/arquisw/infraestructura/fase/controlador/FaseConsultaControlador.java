package co.edu.uco.arquisw.infraestructura.fase.controlador;

import co.edu.uco.arquisw.aplicacion.fase.consulta.ConsultarFasePorIDManejador;
import co.edu.uco.arquisw.aplicacion.fase.consulta.ConsultarFasesPorProyectoIDManejador;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
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
    private final ConsultarFasePorIDManejador consultarFasePorIDManejador;
    private final ConsultarFasesPorProyectoIDManejador consultarFasesPorProyectoIDManejador;

    public FaseConsultaControlador(ConsultarFasePorIDManejador consultarFasePorIDManejador, ConsultarFasesPorProyectoIDManejador consultarFasesPorProyectoIDManejador) {
        this.consultarFasePorIDManejador = consultarFasePorIDManejador;
        this.consultarFasesPorProyectoIDManejador = consultarFasesPorProyectoIDManejador;
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA') || hasAuthority('ADMINISTRADOR_LECTURA')")
    @GetMapping("/{id}")
    @Operation(summary = "Consultar por ID", description = "Este es usado para consultar una fase por medio de su ID")
    public ComandoRespuesta<FaseDTO> consultarPorId(@PathVariable Long id) {
        return this.consultarFasePorIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA') || hasAuthority('ADMINISTRADOR_LECTURA')")
    @GetMapping("/proyecto/{id}")
    @Operation(summary = "Consultar Fases por Proyecto ID", description = "Este es usado para consultar todas las fases existentes de un proyecto por medio del ID del proyecto")
    public ComandoRespuesta<List<FaseDTO>> consultarFasesPorProyectoId(@PathVariable Long id) {
        return this.consultarFasesPorProyectoIDManejador.ejecutar(id);
    }
}