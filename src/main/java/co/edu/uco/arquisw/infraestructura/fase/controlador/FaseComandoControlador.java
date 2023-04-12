package co.edu.uco.arquisw.infraestructura.fase.controlador;

import co.edu.uco.arquisw.aplicacion.fase.comando.manejador.AprobarEtapaManejador;
import co.edu.uco.arquisw.aplicacion.fase.comando.manejador.GuardarFaseManejador;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fases")
@Tag(name = "Comando de la Fase Controlador")
public class FaseComandoControlador {
    private final AprobarEtapaManejador aprobarEtapaManejador;
    private final GuardarFaseManejador guardarFaseManejador;

    public FaseComandoControlador(AprobarEtapaManejador aprobarEtapaManejador, GuardarFaseManejador guardarFaseManejador) {
        this.aprobarEtapaManejador = aprobarEtapaManejador;
        this.guardarFaseManejador = guardarFaseManejador;
    }

    @PreAuthorize("hasRole('ROLE_DIRECTOR_PROYECTO')")
    @PostMapping("/{id}")
    @Operation(summary = "Guardar Fase", description = "Este es usado para guardar la fase de un proyecto por medio de su ID, dando así inicio al descarrollo del proyecto.")
    public ComandoRespuesta<Long> guardar(@PathVariable Long id) {
        return this.guardarFaseManejador.ejecutar(id);
    }

    @PreAuthorize("hasRole('ROLE_TEAM_LEADER')")
    @PutMapping("/aprobacion/{id}")
    @Operation(summary = "Actualizar Usuario", description = "Este es usado para actualizar los datos de una persona por medio de su ID")
    public ComandoRespuesta<Long> aprobarEtapa(@PathVariable Long id) {
        return this.aprobarEtapaManejador.ejecutar(id);
    }
}