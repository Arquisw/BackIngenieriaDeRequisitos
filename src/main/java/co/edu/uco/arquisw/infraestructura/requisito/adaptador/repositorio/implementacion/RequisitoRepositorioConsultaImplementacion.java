package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.VersionEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.RequisitoMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.VersionMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.RequisitoDAO;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.RequisitoFinalDAO;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.VersionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class RequisitoRepositorioConsultaImplementacion implements RequisitoRepositorioConsulta {
    @Autowired
    RequisitoDAO requisitoDAO;
    @Autowired
    VersionDAO versionDAO;
    @Autowired
    RequisitoMapeador requisitoMapeador;
    @Autowired
    VersionMapeador versionMapeador;
    @Autowired
    RequisitoFinalDAO requisitoFinalDAO;

    @Override
    public RequisitoDTO consultarRequisitoPorID(Long id) {
        var entidad = this.requisitoDAO.findById(id).orElse(null);

        if (ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.requisitoMapeador.consturirDTO(entidad);
    }

    @Override
    public List<RequisitoDTO> consultarRequisitosPorVersionID(Long versionId) {
        var entidades = this.requisitoDAO.findAll().stream().filter(requisito -> Objects.equals(requisito.getVersion(), versionId)).toList();

        if (entidades.isEmpty()) {
            return new ArrayList<>();
        }

        var entidadesOrdenadas = entidades.stream().sorted(Comparator.comparing(RequisitoEntidad::getId)).toList();

        return this.requisitoMapeador.construirDTOs(entidadesOrdenadas);
    }

    @Override
    public Page<RequisitoDTO> consultarRequisitosPorVersionIDPaginado(Long versionId, int pagina, int tamano) {
        PageRequest pageable = PageRequest.of(pagina, tamano);
        var entidades = this.requisitoDAO.findByVersion(versionId, pageable);
        if (entidades.getContent().isEmpty()) {
            return Page.empty();
        }
        var entidadesOrdenadas = entidades.getContent().stream().sorted(Comparator.comparing(RequisitoEntidad::getId)).toList();
        entidades = new PageImpl<>(entidadesOrdenadas, entidades.getPageable(), entidades.getTotalElements());
        return this.requisitoMapeador.construirPageDto(entidades);
    }

    @Override
    public VersionDTO consultarVersionPorID(Long versionID) {
        var entidad = this.versionDAO.findById(versionID).orElse(null);

        if (ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.versionMapeador.construirDTO(entidad);
    }

    @Override
    public List<VersionDTO> consultarVersionesPorEtapaID(Long etapaID) {
        var entidades = this.versionDAO.findAll().stream().filter(version -> Objects.equals(version.getEtapa(), etapaID)).toList();

        var entidadesOrdenadas = entidades.stream().sorted((versionUno, versionDos) -> {
            var idUno = versionUno.getId();
            var idDos = versionDos.getId();
            return idDos.compareTo(idUno);
        }).toList();

        return this.versionMapeador.construirDTOs(entidadesOrdenadas);
    }

    @Override
    public VersionDTO consultarUltimaVersionPorEtapaID(Long etapaId) {
        var entidades = this.versionDAO.findAll().stream().filter(version -> Objects.equals(version.getEtapa(), etapaId)).toList();

        var versionFinal = entidades.stream().filter(VersionEntidad::isEsFinal).findFirst().orElse(null);

        if (ValidarObjeto.esNulo(versionFinal)) {
            return null;
        }

        return this.versionMapeador.construirDTO(versionFinal);
    }

    @Override
    public Boolean terminoProcesoDeIngenieriaDeRequisitosPorProyectoID(Long proyectoId) {
        var entidades = requisitoFinalDAO.findAll().stream().filter(requisito -> requisito.getProyectoId().equals(proyectoId)).toList();

        return !entidades.isEmpty();
    }
}