package co.edu.uco.arquisw.infraestructura.requisito.controlador;

import co.edu.uco.arquisw.aplicacion.requisito.comando.MotivoRechazoVersionComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitoComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.RequisitosFinalesComando;
import co.edu.uco.arquisw.aplicacion.requisito.comando.manejador.*;
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
    private final GuardarVersionInicialManejador guardarVersionInicialManejador;
    private final RechazarVersionPorIDManejador rechazarVersionPorIDManejador;
    private final GuardarRequisitosFinalesPorEtapaIDManejador guardarRequisitosFinalesPorEtapaIDManejador;

    public RequisitoComandoControlador(ActualizarRequisitoManejador actualizarRequisitoManejador, EliminarRequisitoManejador eliminarRequisitoManejador, GenerarVersionFinalManejador generarVersionFinalManejador, GuardarRequisitoManejador guardarRequisitoManejador, GuardarVersionInicialManejador guardarVersionInicialManejador, RechazarVersionPorIDManejador rechazarVersionPorIDManejador, GuardarRequisitosFinalesPorEtapaIDManejador guardarRequisitosFinalesPorEtapaIDManejador) {
        this.actualizarRequisitoManejador = actualizarRequisitoManejador;
        this.eliminarRequisitoManejador = eliminarRequisitoManejador;
        this.generarVersionFinalManejador = generarVersionFinalManejador;
        this.guardarRequisitoManejador = guardarRequisitoManejador;
        this.guardarVersionInicialManejador = guardarVersionInicialManejador;
        this.rechazarVersionPorIDManejador = rechazarVersionPorIDManejador;
        this.guardarRequisitosFinalesPorEtapaIDManejador = guardarRequisitosFinalesPorEtapaIDManejador;
    }


    @PreAuthorize("hasAuthority('EQUIPO_DESARROLLO_ESCRITURA')")
    @PostMapping("/{id}")
    @Operation(summary = "Guardar Requisito", description = "Este es usado para guardar el requisito de una versión de una etapa por medio del ID de la versión")
    public ComandoRespuesta<Long> guardar(@RequestBody RequisitoComando requisitoComando, @PathVariable Long id) {
        return this.guardarRequisitoManejador.ejecutar(requisitoComando, id);
    }

    @PreAuthorize("hasAuthority('EQUIPO_DESARROLLO_ACTUALIZACION')")
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Requisito", description = "Este es usado para actualizar el requisito por medio de su ID")
    public ComandoRespuesta<Long> actualizar(@RequestBody RequisitoComando requisitoComando, @PathVariable Long id) {
        return this.actualizarRequisitoManejador.ejecutar(requisitoComando, id);
    }

    @PreAuthorize("hasAuthority('EQUIPO_DESARROLLO_ELIMINACION')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Requisito", description = "Este es usado para eliminar el requisito por medio de su ID")
    public ComandoRespuesta<Long> eliminar(@PathVariable Long id) {
        return this.eliminarRequisitoManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('INGENIERIA_ESCRITURA')")
    @PostMapping("/versiones/{id}")
    @Operation(summary = "Generar Version Inicial", description = "Este es usado para generar la version inicial por medio del id de la etapa")
    public ComandoRespuesta<Long> generarVersionInicial(@PathVariable Long id) {
        return this.guardarVersionInicialManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('INGENIERIA_ACTUALIZACION')")
    @PutMapping("/versiones/{id}")
    @Operation(summary = "Generar Version Final", description = "Este es usado para generar la version final de los requisitos por medio del ID de la versión")
    public ComandoRespuesta<Long> generarVersionFinal(@PathVariable Long id) {
        return this.generarVersionFinalManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('LIDER_DE_EQUIPO_ACTUALIZACION')")
    @PutMapping("/versiones/rechazar/{id}")
    @Operation(summary = "Rechazar Version Final", description = "Este es usado para rechazar la version final de los requisitos por medio del ID de la version")
    public ComandoRespuesta<Long> rechazarVersionPorId(@RequestBody MotivoRechazoVersionComando comando, @PathVariable Long id) {
        return this.rechazarVersionPorIDManejador.ejecutar(comando, id);
    }

    @PreAuthorize("hasAuthority('LIDER_DE_EQUIPO_ESCRITURA')")
    @PostMapping("/finales/{id}")
    @Operation(summary = "Guardar Requisitos Finales por ID", description = "Este es usado para guardar los requisitos finales por medio del ID de la etapa definitiva")
    public ComandoRespuesta<Long> guardarRequisitosFinales(@RequestBody RequisitosFinalesComando comando, @PathVariable Long id) {
        return this.guardarRequisitosFinalesPorEtapaIDManejador.ejecutar(comando, id);
    }
}