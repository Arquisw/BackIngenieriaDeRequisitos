package co.edu.uco.arquisw.infraestructura.requisito.controlador;

import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarRequisitosFinalesPorEtapaIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarRequisitosPorVersionIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarVersionPorIDManejador;
import co.edu.uco.arquisw.aplicacion.requisito.consulta.ConsultarVersionesPorEtapaIDManejador;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitosFinalesDTO;
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
    private final ConsultarVersionesPorEtapaIDManejador consultarVersionesPorEtapaIDManejador;
    private final ConsultarVersionPorIDManejador consultarVersionPorIDManejador;
    private final ConsultarRequisitosPorVersionIDManejador consultarRequisitosPorVersionIDManejador;
    private final ConsultarRequisitosFinalesPorEtapaIDManejador consultarRequisitosFinalesPorEtapaIDManejador;


    public RequisitoConsultaControlador(ConsultarVersionesPorEtapaIDManejador consultarVersionesPorEtapaIDManejador, ConsultarVersionPorIDManejador consultarVersionPorIDManejador, ConsultarRequisitosPorVersionIDManejador consultarRequisitosPorVersionIDManejador, ConsultarRequisitosFinalesPorEtapaIDManejador consultarRequisitosFinalesPorEtapaIDManejador) {
        this.consultarVersionesPorEtapaIDManejador = consultarVersionesPorEtapaIDManejador;
        this.consultarVersionPorIDManejador = consultarVersionPorIDManejador;
        this.consultarRequisitosPorVersionIDManejador = consultarRequisitosPorVersionIDManejador;
        this.consultarRequisitosFinalesPorEtapaIDManejador = consultarRequisitosFinalesPorEtapaIDManejador;
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA')")
    @GetMapping("/versiones/etapa/{id}")
    @Operation(summary = "Consultar Versiones por Etapa ID", description = "Este es usado para consultar las versiones de una etapa por medio de su ID")
    public List<VersionDTO> consultarVersionesPorEtapaId(@PathVariable Long id) {
        return this.consultarVersionesPorEtapaIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA')")
    @GetMapping("/version/{id}")
    @Operation(summary = "Consultar Requisitos por Version ID", description = "Este es usado para consultar los requisitos de una version por medio de su ID")
    public List<RequisitoDTO> consultarRequisitosPorVersionId(@PathVariable Long id) {
        return this.consultarRequisitosPorVersionIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA')")
    @GetMapping("/versiones/{id}")
    @Operation(summary = "Consultar Version por ID", description = "Este es usado para consultar una version por medio de su ID")
    public VersionDTO consultarVersionPorId(@PathVariable Long id) {
        return this.consultarVersionPorIDManejador.ejecutar(id);
    }

    @PreAuthorize("hasAuthority('SELECCIONADO_LECTURA')")
    @GetMapping("/finales/{id}")
    @Operation(summary = "Consultar Requisitos Finales por ID", description = "Este es usado para consultar los requisitos finales por medio del ID de la etapa definitiva.")
    public RequisitosFinalesDTO consultarRequisitosFinalesPorEtapaID(@PathVariable Long id) {
        return this.consultarRequisitosFinalesPorEtapaIDManejador.ejecutar(id);
    }
}