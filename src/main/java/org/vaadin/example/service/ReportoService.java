package org.vaadin.example.service;

import com.vaadin.flow.component.notification.Notification;

import org.vaadin.example.MyDatabaseProvider;
import org.vaadin.example.entidades.Reporto;
import org.vaadin.example.entidades.Usuario;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReportoService {

    Statement stQuery, stQuery1, stQuery2;
    ResultSet rsRecords, rsRecords1, rsRecords2;

    List<Reporto> listReporto = new ArrayList<>();

    MyDatabaseProvider databaseProvider;

    public ReportoService() {

    }

    public List llenarReporto() {

        String queryString = " SELECT * from Reporto";

        Reporto reporto;

        try {
            if (connectToDB() == true) {

                stQuery = databaseProvider.getCurrentConnection().createStatement();
                rsRecords = stQuery.executeQuery(queryString);

                if (rsRecords.next()) { //  encontrado
                    reporto = new Reporto();

                    reporto.setIdUsuario(rsRecords.getInt("IdUsuario"));
                    reporto.setFecha_hora(rsRecords.getDate("FechaYHora"));
                    reporto.setMonto_acumulado(rsRecords.getDouble("MontoAcumulado"));
                    reporto.setMeta_diaria(rsRecords.getDouble("MetaDiaria"));
                    reporto.setMonto_probable(rsRecords.getDouble("MontoProbable"));
                    reporto.setMonto_seguro(rsRecords.getDouble("MontoSeguro"));
                    listReporto.add(reporto);

                }
            }

        } catch (Exception ex1) {
            System.out.println("Error al llenar Usuarios: " + ex1.getMessage());
            ex1.printStackTrace();
        }

        return listReporto;
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