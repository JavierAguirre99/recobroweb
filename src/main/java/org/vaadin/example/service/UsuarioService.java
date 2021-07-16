package org.vaadin.example.service;

import com.mysql.cj.QueryResult;
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

        Usuario usuario;

        queryString = " SELECT * from Usuario";

        try {
            if (connectToDB() == true) {

                stQuery = databaseProvider.getCurrentConnection().createStatement();
                rsRecords = stQuery.executeQuery(queryString);

                if (rsRecords.next()) { //  encontrado

                    do{

                        usuario = new Usuario();

                        usuario.setIdUsuario(rsRecords.getInt("IdUsuario"));
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
                        usuario.setCodigoEspecial(rsRecords.getString("CodigoEspecial"));
                        usuario.setMetaDiaria(rsRecords.getDouble("MetaDiaria"));

                        listUsuario.add(usuario);

                    }while (rsRecords.next());

                }
            }

        } catch (Exception ex1) {
            System.out.println("Error al llenar Usuarios: " + ex1.getMessage());
            ex1.printStackTrace();
        }

        return listUsuario;
    }

    public void guardarUsuario(int i, Usuario usuario) {

        System.out.println("VANDERA " + i);
        System.out.println("Id Usuario" + usuario.getIdUsuario());
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
                queryString += ",'" + usuario.getCodigoEspecial()+ "'";
                queryString += ",'" + usuario.getEstatus() + "'";
                queryString += "," + usuario.getMetaDiaria();
                queryString += ")";

                System.out.println("QUERY " + queryString);

            } else {

                queryString = "Update usuario Set ";
                queryString += " Usuario = '" + usuario.getUsuario() + "'";
                queryString += ", Nombre = '" + usuario.getNombre() + "'";

                if (!usuario.getClave().trim().isEmpty()) {
                    queryString += ",Clave = Sha1('" + usuario.getClave() + "')";

                }
                queryString += ",Email = '" + usuario.getEmail()+ "'";
                queryString += ",Telefono = '" + usuario.getTelefono() + "'";
                queryString += ",Perfil = '" + String.valueOf(usuario.getPerfil()) + "'";
                queryString += ",CodigoEspecial = '" + usuario.getCodigoEspecial() + "'";
                queryString += ",Estatus ='" + String.valueOf(usuario.getEstatus() + "'");
                queryString += ",MetaDiaria = " + usuario.getMetaDiaria();
                queryString += " Where IdUsuario = " + String.valueOf(usuario.getIdUsuario());

                System.out.println("QUERY " + queryString);
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
