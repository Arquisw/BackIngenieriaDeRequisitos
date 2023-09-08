package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.EtapaMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.FaseMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.EtapaDAO;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.FaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;

@Repository
public class FaseRepositorioConsultaImplementacion implements FaseRepositorioConsulta {
    @Autowired
    FaseDAO faseDAO;
    @Autowired
    EtapaDAO etapaDAO;
    @Autowired
    FaseMapeador faseMapeador;
    @Autowired
    EtapaMapeador etapaMapeador;

    @Override
    public FaseDTO consultarFasePorID(Long faseID) {
        var entidad = this.faseDAO.findById(faseID).orElse(null);

        if(ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.faseMapeador.construirDTO(entidad);
    }

    @Override
    public FaseDTO consultarFasePorEtapaID(Long etapaID) {
        var faseEntidad = this.faseDAO.findAll().stream().filter(fase -> {
            var etapa = fase.getEtapas().stream().filter(unaEtapa -> unaEtapa.getId() == etapaID).findFirst().orElse(null);

            return !ValidarObjeto.esNulo(etapa);
        }).findFirst().orElse(null);

        if(ValidarObjeto.esNulo(faseEntidad)) {
            return null;
        }

        return this.faseMapeador.construirDTO(faseEntidad);
    }

    @Override
    public EtapaDTO consultarEtapaPorID(Long etapaID) {
        var entidad = this.etapaDAO.findById(etapaID).orElse(null);

        if(ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.etapaMapeador.construirDTO(entidad);
    }

    @Override
    public List<FaseDTO> consultarFasesPorProyectoID(Long proyectoID) {
        var entidades = this.faseDAO.findAll().stream().filter(fase -> Objects.equals(fase.getProyecto(), proyectoID)).toList();

        return this.faseMapeador.construirDTOs(entidades);
    }
}