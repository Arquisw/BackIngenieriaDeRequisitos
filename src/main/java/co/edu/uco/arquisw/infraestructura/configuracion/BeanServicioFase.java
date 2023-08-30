package co.edu.uco.arquisw.infraestructura.configuracion;

import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioAprobarEtapa;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarEtapaPorID;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasesPorProyectoID;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioGuardarFase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioFase {
    @Bean
    public ServicioAprobarEtapa servicioAprobarEtapa(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioAprobarEtapa(faseRepositorioComando, faseRepositorioConsulta);
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
    public ServicioGuardarFase servicioGuardarFase(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioGuardarFase(faseRepositorioComando, faseRepositorioConsulta);
    }
}