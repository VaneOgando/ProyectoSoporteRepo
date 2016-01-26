package com.inventario.util.constante;

public class Constantes {

    /*CONSTANTES*/


    /*TEXTOS*/


    /*ERRORES*/

    public static final String ERR_LOGIN_INVALIDO = "Usuario y/o contraseña invalida";


    /*QUERY*/

    public static final String HQL_OBTENER_EQUIPOS = "SELECT e FROM EquipoEntity e JOIN e.estado es JOIN e.modelo mo JOIN mo.marca ma";

}
