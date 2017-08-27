package com.grupopulpo.lubriacadora.duty.finals;

/**
 * Created by eliberio on 07/07/16.
 */
public final class ConfigEssentials {
        public static final String AUTHORIZATION = "Bearer";
        public static final String API_GRANT_TYPE="password";
        public static final String API_CLIENT_ID="ugclient";
        public static final String API_CLIENT_SECRET="gBOJNral2AV24kPh6RazcrpRI2zoN7eW";
        public static final String facebook_app_id = "116256795633137";
        public static final String fb_login_protocol_scheme = "fb116256795633137";

        public static final String API_URL="http://192.168.43.239:8000";
        public static final String API_URL_LOGIN=ConfigEssentials.API_URL.concat("/datosCliente?cedula=");
        public static final String API_URL_PHOTO="C:/xampp/htdocs/lubriacadora.web/public/images/tips/";

        public static final String API_VEHICULOS_CLIENTE=ConfigEssentials.API_URL.concat("//vehicle-cliente/");
        public static final String API_URL_TIPS=ConfigEssentials.API_URL.concat("/Tips");
        public static final String API_SERVICIOS=ConfigEssentials.API_URL.concat("/DetServicios-cliente/");



        public static final int SPLASH_TIEMPO=4000;

        public static final String MESSAGE_LOGOUT_API="The resource owner or authorization server denied the request.";}


