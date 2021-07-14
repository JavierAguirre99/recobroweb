package org.vaadin.example.service;

import com.vaadin.flow.component.notification.Notification;
import org.vaadin.example.MyDatabaseProvider;
import org.vaadin.example.entidades.Usuario;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    Statement stQuery, stQuery1, stQuery2;
    ResultSet rsRecords, rsRecords1, rsRecords2;

    List<Usuario> listUsuario = new ArrayList<>();

    MyDatabaseProvider databaseProvider;

    public UsuarioService() {

    }

    public List llenarListaUsuario() {

        String queryString = " SELECT * from Usuario";

        Usuario usuario;

        try {
            if (connectToDB() == true) {

                stQuery = databaseProvider.getCurrentConnection().createStatement();
                rsRecords = stQuery.executeQuery(queryString);

                if (rsRecords.next()) { //  encontrado
                    usuario = new Usuario();

                    usuario.setUsuario(rsRecords.getString("Usuario"));
                    usuario.setClave(rsRecords.getString("Clave"));
                    usuario.setNombre(rsRecords.getString("Nombre"));
                    if (rsRecords.getString("Perfil").equals("ADMINISTRADOR")){
                        usuario.setPerfil(Usuario.Perfil.ADMINISTRADOR);
                    }else if(rsRecords.getString("Perfil").equals("GESTOR")){
                        usuario.setPerfil(Usuario.Perfil.ASESOR);
                    }else{
                        usuario.setPerfil(Usuario.Perfil.SUPERVISOR);
                    }
                    usuario.setTelefono(rsRecords.getString("Telefono"));
                    usuario.setEmail(rsRecords.getString("Email"));
                    usuario.setCodigo_especial(rsRecords.getString("CodigoEspecial"));
                    usuario.setMeta_diaria(rsRecords.getDouble("MetaDiaria"));
                    listUsuario.add(usuario);

                }
            }

        } catch (Exception ex1) {
            System.out.println("Error al llenar Usuarios: " + ex1.getMessage());
            ex1.printStackTrace();
        }

        return listUsuario;
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
