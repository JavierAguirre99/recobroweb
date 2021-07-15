package org.vaadin.example.service;

import com.vaadin.flow.component.notification.Notification;
import org.vaadin.example.MyDatabaseProvider;
import org.vaadin.example.entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    Statement stQuery;
    ResultSet rsRecords;

    List<Usuario> listUsuario = new ArrayList<>();

    MyDatabaseProvider databaseProvider;

    String queryString = "";

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

    public void guardarUsuario(int i, Usuario usuario) {
        try {

            if (i == 0) {

                queryString = "Insert Into usuario (Usuario, Clave, Nombre, Email, Telefono, Perfil, CodigoEspecial, Estatus, MetaDiaria)";
                queryString += " Values (";
                queryString += "'" + usuario.getUsuario()+ "'";
                queryString += ",Sha1('" + usuario.getClave() + "')";
                queryString += ",'" + usuario.getNombre() + "'";
                queryString += ",'" + usuario.getEmail() + "'";
                queryString += ",'" + usuario.getTelefono() + "'";
                queryString += ",'" + usuario.getPerfil() + "'";
                queryString += ",'" + usuario.getCodigo_especial()+ "'";
                queryString += ",'" + usuario.getEstatus() + "'";
                queryString += "," + usuario.getMeta_diaria();
                queryString += ")";

            } else {

                queryString = "Update usuario Set ";
                queryString += " Usuario = '" + usuario.getUsuario() + "'";
                queryString += " Nombre = '" + usuario.getNombre() + "'";

                if (!usuario.getClave().trim().isEmpty()) {
                    queryString += ",Clave = Sha1('" + usuario.getClave() + "')";

                }
                queryString += ",Email = '" + usuario.getEmail()+ "'";
                queryString += ",Telefono = '" + usuario.getTelefono() + "'";
                queryString += ",Perfil = '" + String.valueOf(usuario.getPerfil()) + "'";
                queryString += ",CodigoEspecial = '" + usuario.getCodigo_especial() + "'";
                queryString += ",Estatus ='" + String.valueOf(usuario.getEstatus() + "'");
                queryString += ",MetaDiaria = " + usuario.getMeta_diaria();
                queryString += " Where IdUsuario = " + String.valueOf(usuario.getIdUsuario());
            }

                stQuery = databaseProvider.getCurrentConnection().createStatement();
                stQuery.executeUpdate(queryString);


        } catch(Exception ex){
            System.out.println("Error al intentar guardar Usuario");
        }

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
