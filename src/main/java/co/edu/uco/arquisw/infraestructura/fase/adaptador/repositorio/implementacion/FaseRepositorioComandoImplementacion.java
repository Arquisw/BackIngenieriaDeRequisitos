package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.EtapaMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.FaseMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.EtapaDAO;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.FaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FaseRepositorioComandoImplementacion implements FaseRepositorioComando {
    @Autowired
    FaseDAO faseDAO;
    @Autowired
    EtapaDAO etapaDAO;
    @Autowired
    FaseMapeador faseMapeador;
    @Autowired
    EtapaMapeador etapaMapeador;

    @Override
    public Long guardar(Fase fase, Long proyectoID) {
        var entidad = this.faseMapeador.construirEntidad(fase, proyectoID);

        return this.faseDAO.save(entidad).getId();
    }

    @Override
    public Long actualizar(Fase fase, Long faseID) {
        var entidad = this.faseDAO.findById(faseID).orElse(null);

        assert entidad != null;
        this.faseMapeador.actualizarEntidad(entidad, fase);

        return this.faseDAO.save(entidad).getId();
    }

    @Override
    public void actualizarEtapa(Etapa etapa, Long etapaID) {
        var entidad = this.etapaDAO.findById(etapaID).orElse(null);

        assert entidad != null;
        this.etapaMapeador.actualizarEntidad(entidad, etapa);

        this.etapaDAO.save(entidad);
    }
}