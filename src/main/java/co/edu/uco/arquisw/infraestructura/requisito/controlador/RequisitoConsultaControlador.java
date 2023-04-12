package co.edu.uco.arquisw.infraestructura.requisito.controlador;

import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarRequisitoPorIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarRequisitosPorEtapaIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarVersionPorIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarVersionesPorEtapaIDManejador;
import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/requisitos")
@Tag(name = "Consulta del Requisito Controlador")
public class RequisitoConsultaControlador {
    private final ConsultarRequisitoPorIDManejador consultarRequisitoPorIDManejador;
    private final ConsultarRequisitosPorEtapaIDManejador consultarRequisitosPorEtapaIDManejador;
    private final ConsultarVersionesPorEtapaIDManejador consultarVersionesPorEtapaIDManejador;
    private final ConsultarVersionPorIDManejador consultarVersionPorIDManejador;

    public RequisitoConsultaControlador(ConsultarRequisitoPorIDManejador consultarRequisitoPorIDManejador, ConsultarRequisitosPorEtapaIDManejador consultarRequisitosPorEtapaIDManejador, ConsultarVersionesPorEtapaIDManejador consultarVersionesPorEtapaIDManejador, ConsultarVersionPorIDManejador consultarVersionPorIDManejador) {
        this.consultarRequisitoPorIDManejador = consultarRequisitoPorIDManejador;
        this.consultarRequisitosPorEtapaIDManejador = consultarRequisitosPorEtapaIDManejador;
        this.consultarVersionesPorEtapaIDManejador = consultarVersionesPorEtapaIDManejador;
        this.consultarVersionPorIDManejador = consultarVersionPorIDManejador;
    }

    @PreAuthorize("hasRole('ROLE_SELECCIONADO', 'ROLE_ADMINISTRADOR')")
    @GetMapping("/{id}")
    @Operation(summary = "Consultar por ID", description = "Este es usado para consultar un requisito por medio de su ID")
    public ComandoRespuesta<RequisitoDTO> consultarPorId(@PathVariable Long id) {
        return this.consultarRequisitoPorIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasRole('ROLE_SELECCIONADO', 'ROLE_ADMINISTRADOR')")
    @GetMapping("/etapa/{id}")
    @Operation(summary = "Consultar Requisitos por Etapa ID", description = "Este es usado para consultar un requisito por medio del ID de una Etapa")
    public ComandoRespuesta<List<RequisitoDTO>> consultarRequisitosPorEtapaId(@PathVariable Long id) {
        return this.consultarRequisitosPorEtapaIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasRole('ROLE_SELECCIONADO', 'ROLE_ADMINISTRADOR')")
    @GetMapping("/versiones/etapa/{id}")
    @Operation(summary = "Consultar Versiones por Etapa ID", description = "Este es usado para consultar las versiones de una etapa por medio de su ID")
    public ComandoRespuesta<List<VersionDTO>> consultarVersionesPorEtapaId(@PathVariable Long id) {
        return this.consultarVersionesPorEtapaIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasRole('ROLE_SELECCIONADO', 'ROLE_ADMINISTRADOR')")
    @GetMapping("/versiones/{id}")
    @Operation(summary = "Consultar Version por ID", description = "Este es usado para consultar una version por medio de su ID")
    public ComandoRespuesta<VersionDTO> consultarVersionPorId(@PathVariable Long id) {
        return this.consultarVersionPorIDManejador.ejecutar(id);
    }
}