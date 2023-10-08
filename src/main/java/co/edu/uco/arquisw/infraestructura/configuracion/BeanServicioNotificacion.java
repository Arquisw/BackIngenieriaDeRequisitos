package co.edu.uco.arquisw.infraestructura.configuracion;

import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionAprobacionEtapa;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionVersionFinalGenerada;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionVersionInicialGuardada;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionVersionRechazada;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria.ServicioEnviarNotificacionFactoria;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioNotificacion {
    @Bean
    public ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria(ServicioEnviarNotificacionAprobacionEtapa servicioEnviarNotificacionAprobacionEtapa, ServicioEnviarNotificacionVersionFinalGenerada servicioEnviarNotificacionVersionFinalGenerada, ServicioEnviarNotificacionVersionInicialGuardada servicioEnviarNotificacionVersionInicialGuardada, ServicioEnviarNotificacionVersionRechazada servicioEnviarNotificacionVersionRechazada) {
        return new ServicioEnviarNotificacionFactoria(servicioEnviarNotificacionAprobacionEtapa, servicioEnviarNotificacionVersionFinalGenerada, servicioEnviarNotificacionVersionInicialGuardada, servicioEnviarNotificacionVersionRechazada);
    }

    @Bean
    public ServicioEnviarNotificacionAprobacionEtapa servicioEnviarNotificacionAprobacionEtapa(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        return new ServicioEnviarNotificacionAprobacionEtapa(servicioEnviarCorreoElectronico, personaRepositorioConsulta);
    }

    @Bean
    public ServicioEnviarNotificacionVersionFinalGenerada servicioEnviarNotificacionVersionFinalGenerada(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        return new ServicioEnviarNotificacionVersionFinalGenerada(servicioEnviarCorreoElectronico, personaRepositorioConsulta);
    }

    @Bean
    public ServicioEnviarNotificacionVersionInicialGuardada servicioEnviarNotificacionVersionInicialGuardada(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        return new ServicioEnviarNotificacionVersionInicialGuardada(servicioEnviarCorreoElectronico, personaRepositorioConsulta);
    }

    @Bean
    public ServicioEnviarNotificacionVersionRechazada servicioEnviarNotificacionVersionRechazada(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        return new ServicioEnviarNotificacionVersionRechazada(servicioEnviarCorreoElectronico, personaRepositorioConsulta);
    }
}