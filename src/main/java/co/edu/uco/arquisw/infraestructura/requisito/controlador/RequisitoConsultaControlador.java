package co.edu.uco.arquisw.infraestructura.requisito.controlador;

import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarRequisitosPorVersionIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarVersionPorIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarVersionesPorEtapaIDManejador;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisitos")
@Tag(name = "Consulta del Requisito Controlador")
public class RequisitoConsultaControlador {
    private final ConsultarVersionesPorEtapaIDManejador consultarVersionesPorEtapaIDManejador;
    private final ConsultarVersionPorIDManejador consultarVersionPorIDManejador;
    private final ConsultarRequisitosPorVersionIDManejador consultarRequisitosPorVersionIDManejador;

    public RequisitoConsultaControlador(ConsultarVersionesPorEtapaIDManejador consultarVersionesPorEtapaIDManejador, ConsultarVersionPorIDManejador consultarVersionPorIDManejador, ConsultarRequisitosPorVersionIDManejador consultarRequisitosPorVersionIDManejador) {
        this.consultarVersionesPorEtapaIDManejador = consultarVersionesPorEtapaIDManejador;
        this.consultarVersionPorIDManejador = consultarVersionPorIDManejador;
        this.consultarRequisitosPorVersionIDManejador = consultarRequisitosPorVersionIDManejador;
    }

    @PreAuthorize("hasAuthority('USUARIO_LECTURA')")
    @GetMapping("/versiones/etapa/{id}")
    @Operation(summary = "Consultar Versiones por Etapa ID", description = "Este es usado para consultar las versiones de una etapa por medio de su ID")
    public List<VersionDTO> consultarVersionesPorEtapaId(@PathVariable Long id) {
        return this.consultarVersionesPorEtapaIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('USUARIO_LECTURA')")
    @GetMapping("/version/{id}")
    @Operation(summary = "Consultar Requisitos por Version ID", description = "Este es usado para consultar los requisitos de una version por medio de su ID")
    public Page<RequisitoDTO> consultarRequisitosPorVersionId(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamano,
            @PathVariable Long id) {
        return this.consultarRequisitosPorVersionIDManejador.ejecutar(id,pagina,tamano);
    }

    @PreAuthorize("hasAuthority('USUARIO_LECTURA')")
    @GetMapping("/versiones/{id}")
    @Operation(summary = "Consultar Version por ID", description = "Este es usado para consultar una version por medio de su ID")
    public VersionDTO consultarVersionPorId(@PathVariable Long id) {
        return this.consultarVersionPorIDManejador.ejecutar(id);
    }
}