package org.vaadin.example;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;

public class MyDatabaseProvider {
    private Connection currentConnection = null;
    public String DtePath = "";

    public MyDatabaseProvider() {

    }

    /**
     * Retorna el objeto Connection, que es una nueva conexion a base de datos.
     * @return currentConnection Connection.
     **/
    public Connection getNewConnection() {
        try {

            currentConnection = null;


                Class.forName("com.mysql.jdbc.Driver").newInstance(); //produccion

            System.out.println("DRIVER CARGADO CON EXITO");

            String URL ="jdbc:mysql://localhost:3306/recobroweb";
            String USER = "root";
            String PASSWORD = "root";
            currentConnection = DriverManager.getConnection(URL,USER,PASSWORD);

/***
 DataSource ds = getDBDataSource();
 if (ds == null) {
 utileria.escribirLog("N/A", "", "\n\nConnectionBD->getConnection()...error al obtener el datasource.....\n\n");
 return null;
 }
 currentConnection = ds.getConnection();
 if (currentConnection == null){
 utileria.escribirLog("N/A", "", "\n\nConnectionBD->getConnection()...error al obtener el datasource.....\n\n");
 }
 ***/

        } catch (Exception ex) {
            System.out.println("Error al intentar conectarse");
            ex.printStackTrace();
        }
        return currentConnection;
    }

    /**
     * Lee la variable de ambiente DBDATASOURCE contenida en el archivo web.xml,
     * para determinar que manejador de base de datos se usarara.
     * Luego lee el contexto su-resources.xml para extraer el recurso MYSQLDS o MSSQLDS, segun sea para MYSQL o para MS SQL SERVER
     * @return DataSource ds
     **/
    private DataSource getDBDataSource() {
        DataSource ds = null;

        try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();


        }
        catch(Exception cnfE) {
        }

        return ds;
    }

    /**
     * Lee la variable de ambiente DBDATASOURCE contenida en el archivo web.xml,
     * para determinar que manejador de base de datos se usarara.
     * @return String DBDATASOURCE
     */
    public String getUsedDBDataSource() {
        String usedDataSource = null;

        try {

                usedDataSource = "MYSQL";

        }
        catch(Exception cnfE) {
        }

        return usedDataSource;
    }

    /**
     * Retorna la coneccion actual de base de datos.
     * @return the currentConnection
     **/
    public Connection getCurrentConnection() {
        return currentConnection;
    }
}