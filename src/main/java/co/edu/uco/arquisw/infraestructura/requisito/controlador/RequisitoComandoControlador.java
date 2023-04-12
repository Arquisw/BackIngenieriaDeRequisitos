package co.edu.uco.arquisw.infraestructura.requisito.controlador;

import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitoComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.manejador.ActualizarRequisitoManejador;
import co.edu.uco.arquisw.aplicacion.requisito.comando.manejador.EliminarRequisitoManejador;
import co.edu.uco.arquisw.aplicacion.requisito.comando.manejador.GenerarVersionFinalManejador;
import co.edu.uco.arquisw.aplicacion.requisito.comando.manejador.GuardarRequisitoManejador;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requisitos")
@Tag(name = "Comando del Requisito Controlador")
public class RequisitoComandoControlador {
    private final ActualizarRequisitoManejador actualizarRequisitoManejador;
    private final EliminarRequisitoManejador eliminarRequisitoManejador;
    private final GenerarVersionFinalManejador generarVersionFinalManejador;
    private final GuardarRequisitoManejador guardarRequisitoManejador;

    public RequisitoComandoControlador(ActualizarRequisitoManejador actualizarRequisitoManejador, EliminarRequisitoManejador eliminarRequisitoManejador, GenerarVersionFinalManejador generarVersionFinalManejador, GuardarRequisitoManejador guardarRequisitoManejador) {
        this.actualizarRequisitoManejador = actualizarRequisitoManejador;
        this.eliminarRequisitoManejador = eliminarRequisitoManejador;
        this.generarVersionFinalManejador = generarVersionFinalManejador;
        this.guardarRequisitoManejador = guardarRequisitoManejador;
    }

    @PreAuthorize("hasRole('ROLE_TEAM_MEMBER')")
    @PostMapping("/{id}")
    @Operation(summary = "Guardar Requisito", description = "Este es usado para guardar el requisito de una etapa por medio del ID de la etapa")
    public ComandoRespuesta<Long> guardar(@RequestBody RequisitoComando requisitoComando, @PathVariable Long id) {
        return this.guardarRequisitoManejador.ejecutar(requisitoComando, id);
    }

    @PreAuthorize("hasRole('ROLE_TEAM_MEMBER')")
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Requisito", description = "Este es usado para actualizar el requisito por medio de su ID")
    public ComandoRespuesta<Long> actualizar(@RequestBody RequisitoComando requisitoComando, @PathVariable Long id) {
        return this.actualizarRequisitoManejador.ejecutar(requisitoComando, id);
    }

    @PreAuthorize("hasRole('ROLE_TEAM_MEMBER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Requisito", description = "Este es usado para eliminar el requisito por medio de su ID")
    public ComandoRespuesta<Long> eliminar(@PathVariable Long id) {
        return this.eliminarRequisitoManejador.ejecutar(id);
    }

    @PreAuthorize("hasRole('ROLE_TEAM_LEADER')")
    @PutMapping("/versiones/{id}")
    @Operation(summary = "Generar Version Final", description = "Este es usado para generar la version final de los requisitos por medio del ID de la Etapa")
    public ComandoRespuesta<Long> generarVersionFinal(@PathVariable Long id) {
        return this.generarVersionFinalManejador.ejecutar(id);
    }
}