package co.edu.uco.arquisw.infraestructura.configuracion;

import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioAprobarEtapa;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasePorID;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasesPorProyectoID;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioGuardarFase;
import org.springframework.context.annotation.Bean;

public class BeanServicioFase {
    @Bean
    public ServicioAprobarEtapa servicioAprobarEtapa(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioAprobarEtapa(faseRepositorioComando, faseRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarFasePorID servicioConsultarFasePorID(FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioConsultarFasePorID(faseRepositorioConsulta);
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