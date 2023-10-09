package co.edu.uco.arquisw.dominio.transversal.utilitario;

public class Mensajes {
    public static final String NOMBRE_FASE_VACIO = "El nombre no puede estar vacío.";
    public static final String PATRON_NOMBRE_FASE_NO_ES_VALIDO = "El nombre solo puede contener letras y números.";
    public static final String DESCRIPCION_FASE_VACIO = "La descripción de la fase no puede estar vacía.";
    public static final String PATRON_DESCRIPCION_FASE_NO_ES_VALIDO = "La descripción solo puede contener letras y números.";
    public static final String NOMBRE_ETAPA_VACIO = "El nombre de la etapa no puede estar vacío.";
    public static final String PATRON_NOMBRE_ETAPA_NO_ES_VALIDO = "El nombre de la etapa solo puede contener letras y números.";
    public static final String DESCRIPCION_ETAPA_VACIO = "La descripción de la etapa no puede estar vacía.";
    public static final String PATRON_DESCRIPCION_ETAPA_NO_ES_VALIDO = "La descripción de la etapa solo puede contener letras y números.";
    public static final String NOMBRE_TIPO_REQUISITO_VACIO = "El nombre del tipo de requisito no puede estar vacío.";
    public static final String PATRON_NOMBRE_TIPO_REQUISITO_NO_ES_VALIDO = "El nombre del tipo de requisito solo puede contener letras y números.";
    public static final String NOMBRE_REQUISITO_VACIO = "El nombre del requisito no puede estar vacío.";
    public static final String PATRON_NOMBRE_REQUISITO_NO_ES_VALIDO = "El nombre del requisito solo puede contener letras y números.";
    public static final String DESCRIPCION_REQUISITO_VACIO = "La descripción del requisito no puede estar vacía.";
    public static final String PATRON_DESCRIPCION_REQUISITO_NO_ES_VALIDO = "La descripción del requisito solo puede contener letras y números.";
    public static final String OCURRIO_UN_ERROR_POR_FAVOR_CONTACTAR_AL_ADMINISTRADOR = "Ocurrió un error, por favor contactar al administrador.";
    public static final String NOMBRE_ESTADO_PROYECTO_NO_PUEDE_ESTAR_VACIO = "El nombre del estado del proyecto no puede estar vacío.";
    public static final String PATRON_NOMBRE_ESTADO_PROYECTO_NO_ES_VALIDO = "El nombre del estado del proyecto solo puede contener letras y espacios.";
    public static final String ETAPA_APROBADA_POR_EL_LIDER_DE_EQUIPO = "Etapa aprobada por el líder de equipo.";
    public static final String PRIMERA_VERSION_DE_LA_ETAPA_INICIADA = "Primera versión de la etapa actual iniciada.";
    public static final String VERSION_DE_LA_ETAPA_RECHAZADA = "Versión de la etapa actual rechazada.";
    public static final String VERSION_FINAL_GENERADA_Y_LISTA_PARA_REVISION = "Versión final generada y lista para revisión.";
    public static final String MOTIVO_RECHAZO_VERSION_NO_PUEDE_ESTAR_VACIO = "El motivo de rechazo de la version no puede estar vacío.";
    public static final String PATRON_MOTIVO_RECHAZO_VERSION_NO_ES_VALIDO = "El motivo de rechazo de la versión debe ser alfanumérico.";
    public static final String NO_SE_HA_GENERADO_UNA_VERSION_FINAL_PARA_LA_ETAPA_ACTUAL = "No se ha generado una versión final para la etapa actual, por lo tanto, no puede aprobar la etapa.";
    public static final String NO_HAY_REQUISITOS_PARA_LA_VERSION_ACTUAL = "No hay requisitos en la versión actual, por lo tanto, no puede generar la versión final.";
    public static final String LA_VERSION_NO_HA_SUFRIDO_NINGUN_CAMBIO_CON_RESPECTO_A_LA_ETAPA_ANTERIOR = "La versión no ha sufrido ningún cambio con respecto a la versión definitiva de la etapa anterior, por lo tanto, no puede generar la versión final.";
    public static final String NO_HAY_REQUISITOS_PARA_LA_VERSION_ACTUAL_NO_PUEDE_RECHAZAR = "No hay requisitos en la versión actual, por lo tanto, no puede rechazar.";
    public static final String LA_VERSION_NO_HA_SUFRIDO_NINGUN_CAMBIO_CON_RESPECTO_A_LA_ETAPA_ANTERIOR_NO_PUEDE_RECHAZAR = "La versión no ha sufrido ningún cambio con respecto a la versión definitiva de la etapa anterior, por lo tanto, no se puede rechazar.";
    public static final String NOMBRE_ROL_VACIO = "El nombre no puede estar vacío.";
    public static final String PATRON_NOMBRE_ROL_INVALIDO = "El nombre solo puede contener letras y números.";
    public static final String TOKEN_INVALIDO = "El token recibido es inválido.";
    public static final String NO_SE_ENCONTRO_NINGUN_GUION_BAJO = "No se encontró ningún guión bajo.";
    private static final String LA_ETAPA = "La etapa ";
    private static final String DE_LA_FASE = " de la fase ";
    private static final String EN_EL_PROYECTO = " en el proyecto ";
    private static final String HA_SIDO_APROBADO_POR_EL_ROL_LIDER_DE_EQUIPO = " ha sido aprobada por el líder del equipo.";
    private static final String HA_INICIADO_LA_PRIMERA_VERSION_DE_LOS_REQUISITOS = " ha iniciado la primera versión de los requisitos.";
    private static final String LA_VERSION = "La versión #";
    private static final String DE_LA_ETAPA = " de la etapa ";
    private static final String HA_SIDO_ESTABLECIDA_COMO_VERSION_FINAL_POR_LO_TANTO_ESTA_LISTA_PARA_REVISION = " ha sido establecida como versión final por el rol ingeniería, por lo tanto, está lista para su aprobación en la etapa o en el rechazo de la versión si así lo consideras.";
    private static final String HA_SIDO_RECHAZADA_POR_EL_LIDER_DE_EQUIPO_CUYO_MOTIVO_ES = " ha sido rechazada por el líder de equipo. El motivo del rechazo es por ";
    private static final String ETAPA_CON_ID = "La etapa con el ID ";
    private static final String ETAPA_YA_ESTA_COMPLETADA = " ya está completada.";
    private static final String NO_EXISTE_ETAPA_CON_EL_ID = "No existe etapa con el ID ";
    private static final String NO_EXISTE_PROYECTO_CON_EL_ID = "No existe proyecto con el ID ";
    private static final String NO_EXISTE_REQUISITO_CON_EL_ID = "No existe un requisito con el ID ";
    private static final String NO_EXISTE_VERSION_CON_EL_ID = "No existe una versión con el ID ";
    private static final String EXISTE_USUARIO_CON_CORREO = "Ya existe un usuario con el correo ";

    private Mensajes() {
    }

    public static String obtenerCuerpoNotificacionAprobacionEtapa(String nombreEtapa, String nombreFase, String nombreProyecto) {
        return LA_ETAPA + nombreEtapa + DE_LA_FASE + nombreFase + EN_EL_PROYECTO + nombreProyecto + HA_SIDO_APROBADO_POR_EL_ROL_LIDER_DE_EQUIPO;
    }

    public static String obtenerCuerpoNotificacionVersionFinalGenerada(Long versionId, String nombreEtapa, String nombreFase, String nombreProyecto) {
        return LA_VERSION + versionId + DE_LA_ETAPA + nombreEtapa + DE_LA_FASE + nombreFase + EN_EL_PROYECTO + nombreProyecto + HA_SIDO_ESTABLECIDA_COMO_VERSION_FINAL_POR_LO_TANTO_ESTA_LISTA_PARA_REVISION;
    }

    public static String obtenerCuerpoNotificacionVersionInicialGuardada(String nombreEtapa, String nombreFase, String nombreProyecto) {
        return LA_ETAPA + nombreEtapa + DE_LA_FASE + nombreFase + EN_EL_PROYECTO + nombreProyecto + HA_INICIADO_LA_PRIMERA_VERSION_DE_LOS_REQUISITOS;
    }

    public static String obtenerCuerpoNotificacionVersionRechazada(Long versionId, String nombreEtapa, String nombreFase, String nombreProyecto, String motivoRechazo) {
        return LA_VERSION + versionId + DE_LA_ETAPA + nombreEtapa + DE_LA_FASE + nombreFase + EN_EL_PROYECTO + nombreProyecto + HA_SIDO_RECHAZADA_POR_EL_LIDER_DE_EQUIPO_CUYO_MOTIVO_ES + motivoRechazo;
    }

    public static String obtenerNoExiteEtapaConId(Long etapaId) {
        return NO_EXISTE_ETAPA_CON_EL_ID + etapaId;
    }

    public static String obtenerEtapaConIdYaEstaCompletada(Long etapaId) {
        return ETAPA_CON_ID + etapaId + ETAPA_YA_ESTA_COMPLETADA;
    }

    public static String obtenerNoExiteProyectoConId(Long proyectoID) {
        return NO_EXISTE_PROYECTO_CON_EL_ID + proyectoID;
    }

    public static String obtenerNoExiteRequisitoConId(Long requisitoID) {
        return NO_EXISTE_REQUISITO_CON_EL_ID + requisitoID;
    }

    public static String obtenerNoExiteVersionConId(Long versionID) {
        return NO_EXISTE_VERSION_CON_EL_ID + versionID;
    }

    public static String obtenerNoExiteUsuarioConCorreo(String correo) {
        return EXISTE_USUARIO_CON_CORREO + correo;
    }
}