package com.example.pm1e12921.Configuracion;

public class Transaciones {

    //nombre de la base de datos
    public static final  String DBName = "PM1E12921";
    //creacion de la tabla en la base de datos
    public static final  String Tablepersonas = "personas";
    //creacion de los campus de la base de datos
    public static final  String id = "id";
    public static final  String pais = "pais";
    public static final  String nombre = "nombre";
    public static final  String telefono = "telefono";
    public static final  String nota = "nota";


    //ddl crate
    public static final  String CreateTablePersonas = "CREATE TABLE " + Tablepersonas + "(" +
            " id  INTEGER PRIMARY KEY AUTOINCREMENT ,  pais  TEXT, nombre  TEXT,  telefono  INTEGER, nota TEXT) ";

    //ddl drop
    public static final  String DropTablePersonas = "DROP TABLE IF EXISTS " + Tablepersonas ;

    //dml
    public static final  String SelectAllPersonas = "SELECT *FROM " + Tablepersonas ;

}
