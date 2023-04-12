package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.EtapaMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.FaseMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.ProyectoMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.EtapaDAO;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.FaseDAO;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.ProyectoDAO;
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
    ProyectoDAO proyectoDAO;
    @Autowired
    FaseMapeador faseMapeador;
    @Autowired
    EtapaMapeador etapaMapeador;
    @Autowired
    ProyectoMapeador proyectoMapeador;

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
        var etapaEntidad = this.etapaDAO.findById(etapaID).orElse(null);
        var entidad = this.faseDAO.findByEtapa(etapaEntidad);

        if(ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.faseMapeador.construirDTO(entidad);
    }

    @Override
    public ProyectoDTO consultarProyectoPorID(Long proyectoID) {
        var entidad = this.proyectoDAO.findById(proyectoID).orElse(null);

        if(ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.proyectoMapeador.construirDTO(entidad);
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