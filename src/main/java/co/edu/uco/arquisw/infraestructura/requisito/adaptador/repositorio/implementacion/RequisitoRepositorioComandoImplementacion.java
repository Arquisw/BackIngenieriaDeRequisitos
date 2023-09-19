package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.requisito.modelo.MotivoRechazoVersion;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.RequisitosFinales;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.MotivoRechazoVersionMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.RequisitoMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.RequisitosFinalesMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.VersionMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequisitoRepositorioComandoImplementacion implements RequisitoRepositorioComando {
    @Autowired
    RequisitoDAO requisitoDAO;
    @Autowired
    RequisitoTipoRequisitoDAO requisitoTipoRequisitoDAO;
    @Autowired
    VersionDAO versionDAO;
    @Autowired
    RequisitoMapeador requisitoMapeador;
    @Autowired
    VersionMapeador versionMapeador;
    @Autowired
    MotivoRechazoVersionDAO motivoRechazoVersionDAO;
    @Autowired
    MotivoRechazoVersionMapeador motivoRechazoVersionMapeador;
    @Autowired
    RequisitosFinalesMapeador requisitosFinalesMapeador;
    @Autowired
    RequisitosFinalesDAO requisitosFinalesDAO;

    @Override
    public Long guardar(Requisito requisito, Long versionId) {
        var entidad = this.requisitoMapeador.consturirEntidad(requisito, versionId);

        entidad.getTipoRequisito().setId(this.requisitoTipoRequisitoDAO.save(entidad.getTipoRequisito()).getId());

        return this.requisitoDAO.save(entidad).getId();
    }

    @Override
    public Long actualizar(Requisito requisito, Long id) {
        var entidad = this.requisitoDAO.findById(id).orElse(null);

        assert entidad != null;
        this.requisitoMapeador.actualizarRequisito(entidad, requisito);

        return this.requisitoDAO.save(entidad).getId();
    }

    @Override
    public void eliminar(Long id) {
        var entidad = this.requisitoDAO.findById(id).orElse(null);

        assert entidad != null;
        this.requisitoDAO.deleteById(entidad.getId());
        this.requisitoTipoRequisitoDAO.deleteById(entidad.getTipoRequisito().getId());
    }

    @Override
    public Long guardarVersion(Version version, Long etapaID) {
        var entidad = this.versionMapeador.consturirEntidad(version, etapaID);

        return this.versionDAO.save(entidad).getId();
    }

    @Override
    public Long actualizarVersion(Version version, Long versionID) {
        var entidad = this.versionDAO.findById(versionID).orElse(null);

        assert entidad != null;
        this.versionMapeador.actualizarEntidad(entidad, version);

        return this.versionDAO.save(entidad).getId();
    }

    @Override
    public void actualizarVersion(boolean estado, boolean rechazado, Long versionID) {
        var entidad = this.versionDAO.findById(versionID).orElse(null);

        assert entidad != null;
        this.versionMapeador.actualizarEntidad(entidad, estado, rechazado);

        this.versionDAO.save(entidad);
    }

    @Override
    public void guardarMotivoRechazoVersion(MotivoRechazoVersion motivoRechazoVersion, Long versionId) {
        var entidad = this.motivoRechazoVersionMapeador.construirEntidad(motivoRechazoVersion, versionId);

        this.motivoRechazoVersionDAO.save(entidad);
    }

    @Override
    public Long guardarRequisitosFinales(RequisitosFinales requisitosFinales, Long etapaId) {
        var entidad = this.requisitosFinalesMapeador.construir(requisitosFinales, etapaId);

        return this.requisitosFinalesDAO.save(entidad).getId();
    }
}