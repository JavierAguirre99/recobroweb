package org.vaadin.example.views;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.FileUtils;
import org.vaadin.alejandro.PdfBrowserViewer;
import org.vaadin.example.MyDatabaseProvider;
import org.vaadin.example.views.finiquitos.ConsultarFiniquitosView;
import org.vaadin.example.views.finiquitos.FiniquitosView;
import org.vaadin.example.views.usuario.UsuarioView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The main view contains a button and a click listener.
 */
@PWA(name = "Project Base for Vaadin", shortName = "Recobro Web", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends AppLayout {

    public MyDatabaseProvider databaseProvider;
    Statement stQuery = null;
    Statement stQuery2 = null;
    ResultSet rsRecords = null;
    ResultSet rsRecords2 = null;

    public MainView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Recobro Web");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");


        addToNavbar(header);

        if(validateUser("JAVIER", "Zepeda")){

        }

    }

    private void createDrawer() {

        Accordion menu = new Accordion();  //// Acordion principal

        Accordion seguridadAccordion = new Accordion(); ////Acordion "Seguridad"
        VerticalLayout seguridadLayout = new VerticalLayout(); //Contenedor del acordion "Seguridad"

        seguridadLayout.add(new RouterLink("Usuario", UsuarioView.class)); ///LINKS Dentro del contenedor seguridad
        seguridadAccordion.add("Usuario" , seguridadLayout);

        menu.add("Seguridad", seguridadAccordion); /// se agrega el acordion al principal

        Accordion gestionAcordion = new Accordion(); /// Acordion "Gestion"

        VerticalLayout gestionLayout = new VerticalLayout(); ///// Contenedor de acordion "gestion"

        gestionLayout.add(new RouterLink("Usuario", UsuarioView.class)); // Primer linck agregado a gestiones

        gestionAcordion.add("Gestion" ,gestionLayout);


        Accordion finiquitosAcordion = new Accordion(); // Nuevo acordion finiquitos

        gestionLayout.add(finiquitosAcordion); /// agregamos al contenedor el nuevo acordion

        VerticalLayout finiquitosLayout = new VerticalLayout(); // creamos un contenedor del finiquitos acordion

        finiquitosLayout.add(new RouterLink("ConsultarFiniquitos", ConsultarFiniquitosView.class)); // agregamos los linkc
        finiquitosLayout.add(new RouterLink("IngresarFiniquitos", FiniquitosView.class));

        finiquitosAcordion.add("Finiquitos" ,finiquitosLayout); /// agregamos

        menu.add("Gestion", gestionAcordion);

        addToDrawer(new VerticalLayout(menu));
    }

    public boolean validateUser(String userName, String passWord) {

        try {

            if (!connectToDB()) {
                return false;
            }

            String queryString;

            queryString = "Select * from usuario ";

            stQuery = databaseProvider.getCurrentConnection().createStatement();
            rsRecords = stQuery.executeQuery(queryString);
            if (rsRecords.next()) { //  encontrado

                System.out.println(rsRecords.getString("Usuario"));
            }
        } catch (Exception ex1) {
            System.out.println("Error : " + ex1.getMessage());
            ex1.printStackTrace();
        }

        return true;

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


    public  void agregarPDF(){
        OutputStream file = null;
        Dialog contenedor;
        try {
            contenedor = new Dialog();
            contenedor.setSizeFull();

            Button closeBtn = new Button("Cerrar");
            file = new FileOutputStream(new File("C:\\Users\\jzepeda\\Documents\\Contacts.pdf"));

            // Create a new Document object
            Document document = new Document();

            // You need PdfWriter to generate PDF document
            PdfWriter.getInstance(document, file);

            // Opening document for writing PDF
            document.open();

            // Writing content
            document.add(new Paragraph("Hello World, Creating PDF document in Java is easy"));
            document.add(new Paragraph("You are customer # 2345433"));
            document.add(new Paragraph(new Date(new java.util.Date().getTime()).toString()));

            // Add meta data information to PDF file
            document.addCreationDate();
            document.addAuthor("Javarevisited");
            document.addTitle("How to create PDF document in Java");
            document.addCreator("Thanks to iText, writing into PDF is easy");


            // close the document
            document.close();

            StreamResource streamResource = new StreamResource(
                    "Contacts.pdf", () -> {
                try {
                    return FileUtils.openInputStream(
                            new File("C:\\Users\\jzepeda\\Documents\\Contacts.pdf"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            });

            PdfBrowserViewer viewer = new PdfBrowserViewer(streamResource);
            viewer.setHeight("100%");

            contenedor.add(closeBtn);
            contenedor.add(viewer);

            contenedor.open();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            // closing FileOutputStream
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException io) {

            }

        }

    }
}
