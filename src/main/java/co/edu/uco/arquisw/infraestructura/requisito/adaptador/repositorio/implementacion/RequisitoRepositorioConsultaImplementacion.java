package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.formateador.FechaFormateador;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.RequisitoMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.VersionMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.RequisitoDAO;
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

        return this.versionMapeador.consturirDTO(entidad);
    }

    @Override
    public List<VersionDTO> consultarVersionesPorEtapaID(Long etapaID) {
        var entidades = this.versionDAO.findAll().stream().filter(version -> Objects.equals(version.getEtapa(), etapaID)).toList();

        var entidadesOrdenadas = entidades.stream().sorted((versionUno, versionDos) -> {
            var fechaUno = FechaFormateador.obtenerFecha(versionUno.getFecha());
            var fechaDos = FechaFormateador.obtenerFecha(versionDos.getFecha());

            return fechaDos.compareTo(fechaUno);
        }).toList();

        return this.versionMapeador.construirDTOs(entidadesOrdenadas);
    }
}