package com.inventario.util.constante;

public class Constantes {

    /*CONSTANTES*/


    /*TEXTOS*/


    /*ERRORES*/

    public static final String ERR_LOGIN_INVALIDO = "Usuario y/o contraseña invalida";


    /*QUERY*/

    public static final String HQL_OBTENER_EQUIPOS = "SELECT e FROM EquipoEntity e JOIN e.estado es JOIN e.modelo mo JOIN mo.marca ma WHERE es.nombre != 'Dañado'";
    public static final String HQL_OBTENER_ACCESORIOS = "SELECT a FROM AccesorioEntity a JOIN a.estado es JOIN a.modelo mo JOIN mo.marca ma JOIN a.categoria ca WHERE es.nombre != 'Dañado'";
    public static final String HQL_OBTENER_ESTADOS = "SELECT e FROM EstadoEntity e";
    public static final String HQL_OBTENER_MARCAS = "SELECT m FROM MarcaEntity m";
    public static final String HQL_OBTENER_MODELOS = "SELECT mo FROM ModeloEntity mo JOIN mo.marca ma WHERE ma.id = :marcaId";
    public static final String HQL_OBTENER_CATEGORIAS = "SELECT ca FROM CategoriaEntity ca WHERE ca.tipoCategoria = :tipoCategoria";

}
