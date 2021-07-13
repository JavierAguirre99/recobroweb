package org.vaadin.example.service;

import com.vaadin.flow.component.notification.Notification;
import org.vaadin.example.MyDatabaseProvider;
import org.vaadin.example.entidades.Finiquitos;
import org.vaadin.example.entidades.Usuario;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FiniquitosService {

    Statement stQuery;
    ResultSet rsRecords;

    List<Finiquitos> listFiniquitos = new ArrayList<>();

    MyDatabaseProvider databaseProvider;

    public FiniquitosService(){


    }
    public List llenarListaFiniquitos() {

        String queryString = " SELECT * from Finiquitos";

        Finiquitos finiquito;

        try {
            if (connectToDB() == true) {

                stQuery = databaseProvider.getCurrentConnection().createStatement();
                rsRecords = stQuery.executeQuery(queryString);

                if (rsRecords.next()) { //  encontrado
                    finiquito = new Finiquitos();

                    finiquito.setCorrelativo(rsRecords.getInt("Correlativo"));
                    finiquito.setIdentificacion(rsRecords.getString("Identificacion"));
                    finiquito.setNombre(rsRecords.getString("Nombre"));
                    finiquito.setDepartamento(rsRecords.getString("Departamento"));
                    finiquito.setMunicipio(rsRecords.getString("Municipio"));
                    finiquito.setCuenta(rsRecords.getString("Cuenta"));

                    if (rsRecords.getString("Tipo").equals(Finiquitos.Tipo.FINIQUITO)){
                        finiquito.setTipo(Finiquitos.Tipo.FINIQUITO);
                    }else if(rsRecords.getString("Tipo").equals(Finiquitos.Tipo.CARTA)){
                        finiquito.setTipo(Finiquitos.Tipo.CARTA);
                    }

                    finiquito.setObservaciones(rsRecords.getString("Observaciones"));

                    listFiniquitos.add(finiquito);

                }
            }

        } catch (Exception ex1) {
            System.out.println("Error al llenar Usuarios: " + ex1.getMessage());
            ex1.printStackTrace();
        }

        return listFiniquitos;
    }

    public boolean connectToDB() {
        try {

            if (databaseProvider == null) {
                databaseProvider = new MyDatabaseProvider();
                databaseProvider.getNewConnection();
            }

            if (databaseProvider.getCurrentConnection() == null) {

                databaseProvider = null;

                Notification.show("PROBLEMA AL CONECTARSE A BASE DE DATOS, POR FAVOR CONTACTE AL DESARROLLADOR");

                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Notification.show("PROBLEMA AL CONECTARSE A BASE DE DATOS, POR FAVOR CONTACTE AL DESARROLLADOR.");

            return false;
        }
        return true;
    }
}
