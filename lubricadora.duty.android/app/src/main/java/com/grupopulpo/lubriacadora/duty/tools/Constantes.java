package com.grupopulpo.lubriacadora.duty.tools;

/**
 * Clase que contiene los códigos-url usados en "Mi Nutricionista" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {
    public static final int CODIGO_DETALLE = 100;
    public static final int CODIGO_ACTUALIZACION = 101;
    public static final String INSERT_TOKEN_USUARIO = "http://servicios.misnutricionistas.com.ec/GuardarTokenUsuario.php?";
    public static String ruta_online = "http://localhost:8000/";
   // public static String INSERTA_CHAT = "http://servicios.misnutricionistas.com.ec/InsertarPeticion.php?";//Producción
    public static String INSERTA_CHAT = "http://servicios.misnutricionistas.com.ec/InsertarPeticion.php?";//Local
    public static String OBTENER_MIS_CHATSUSERS = "http://servicios.misnutricionistas.com.ec/ObtenerContactosNutricionistas.php?";
    public static String OBTENER_CONVERSACIONES = "http://servicios.misnutricionistas.com.ec/ObtenerConversaPorUsuario.php?";
   // public static String ruta_online = "52.27.202.153/";
   // public static String ruta_online_img = "52.27.202.153/";
    public static String ruta_online_img = "http://admin.misnutricionistas.com.ec/nutricionistas_img/";
    public static String PUBLICIDAD_ONLINE_PORTADAS = "http://servicios.misnutricionistas.com.ec/img_contenido/portadas/";
    public static String PUBLICIDAD_ONLINE_EVENTOS = "";
    public static String PUBLICIDAD_ONLINE_INGREDIENTES = "";
    public static String PUBLICIDAD_ONLINE_COMPLETAS = "http://servicios.misnutricionistas.com.ec/img_contenido/cuerpos/";
    /**
     * URLs del Web Service
     * http://innovasystem.com.ec/MostrarDietasPorId.php?dieta_id=1
     */
    public static final String OBTENER_VEHICULOS = ruta_online + "/vehicle-cliente/";


    //RUTA DE IMAGENES APP//
    public static final String GET_NUTRICIONISTA_IMG = ruta_online_img + "usuarios/";
    public static final String GET_PACIENTE_IMG = ruta_online_img + "pacientes/";
    public static final String GET_TIPOEJERCIICO_IMG = ruta_online_img + "tipo_ejercicios/";
    public static final String GET_TIPO_RETO_IMG = ruta_online_img + "reto/";
    public static final String GET_RETO_IMG = ruta_online_img + "reto/";
    public static final String GET_EJERCIICO_IMG = ruta_online_img + "ejercicios/";
    /// Clave para el valor extra que representa al identificador de una dieta ///
    public static final String EXTRA_ID = "IDEXTRA";
}
