package co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.RequisitoMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador.VersionMapeador;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.RequisitoDAO;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.RequisitoTipoRequisitoDAO;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.repositorio.jpa.VersionDAO;
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

    @Override
    public Long guardar(Requisito requisito, Long versionId) {
        var entidad = this.requisitoMapeador.consturirEntidad(requisito, versionId);

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
}