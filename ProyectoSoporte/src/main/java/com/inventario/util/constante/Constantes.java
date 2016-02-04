package com.inventario.util.constante;

public class Constantes {

    /*CONSTANTES*/


    /*TEXTOS*/


    /*ERRORES*/

    public static final String ERR_LOGIN_INVALIDO = "Usuario y/o contraseña invalida";


    /*QUERY*/

    public static final String HQL_OBTENER_EQUIPOS = "SELECT e FROM EquipoEntity e JOIN e.estado es JOIN e.modelo mo JOIN mo.marca ma WHERE es.nombre != 'Dañado'";
    public static final String HQL_OBTENER_ACCESORIOS = "SELECT a FROM AccesorioEntity a JOIN a.estado es JOIN a.modelo mo JOIN mo.marca ma JOIN a.categoria ca WHERE es.nombre != 'Dañado'";

}
