package co.edu.uco.arquisw.infraestructura.configuracion;

import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.ProyectoRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioAprobarEtapa;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarEtapaPorID;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasesPorProyectoID;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioGuardarFase;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioComando;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioFase {
    @Bean
    public ServicioAprobarEtapa servicioAprobarEtapa(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, ProyectoRepositorioComando proyectoRepositorioComando, PersonaRepositorioConsulta personaRepositorioConsulta, PersonaRepositorioComando personaRepositorioComando, ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, RequisitoRepositorioConsulta requisitoRepositorioConsulta, RequisitoRepositorioComando requisitoRepositorioComando) {
        return new ServicioAprobarEtapa(faseRepositorioComando, faseRepositorioConsulta, seleccionRepositorioConsulta, proyectoRepositorioConsulta, proyectoRepositorioComando, personaRepositorioConsulta, personaRepositorioComando, servicioEnviarCorreoElectronico, requisitoRepositorioConsulta, requisitoRepositorioComando);
    }

    @Bean
    public ServicioConsultarEtapaPorID servicioConsultarFasePorID(FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioConsultarEtapaPorID(faseRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarFasesPorProyectoID servicioConsultarFasesPorProyectoID(FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioConsultarFasesPorProyectoID(faseRepositorioConsulta);
    }

    @Bean
    public ServicioGuardarFase servicioGuardarFase(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta) {
        return new ServicioGuardarFase(faseRepositorioComando, faseRepositorioConsulta, proyectoRepositorioConsulta);
    }
}