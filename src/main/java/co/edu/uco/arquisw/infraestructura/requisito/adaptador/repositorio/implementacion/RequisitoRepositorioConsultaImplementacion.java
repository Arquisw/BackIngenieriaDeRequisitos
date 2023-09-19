package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitosFinalesDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.formateador.FechaFormateador;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.VersionEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.RequisitoMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.RequisitosFinalesMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.VersionMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.RequisitoDAO;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.RequisitosFinalesDAO;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.VersionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    RequisitosFinalesMapeador requisitosFinalesMapeador;
    @Autowired
    RequisitosFinalesDAO requisitosFinalesDAO;

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

        var entidadesOrdenadas = entidades.stream().sorted(Comparator.comparing(RequisitoEntidad::getId)).toList();

        return this.requisitoMapeador.construirDTOs(entidadesOrdenadas);
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
    public RequisitosFinalesDTO consultarRequisitosFinalesPorEtapaID(Long etapaId) {
        var entidad = this.requisitosFinalesDAO.findByEtapa(etapaId);

        if (ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.requisitosFinalesMapeador.construirDTO(entidad);
    }
}