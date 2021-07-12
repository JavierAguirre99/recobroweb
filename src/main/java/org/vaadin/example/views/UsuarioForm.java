package org.vaadin.example.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.example.entidades.Usuario;

public class UsuarioForm extends Dialog {

    TextField usuarioTxt;
    PasswordField claveTxt;
    TextField nombreTxt;
    ComboBox<Usuario.Perfil> perfilCbx;
    EmailField emailTxt;
    TextField telefonoTxt;
    TextField codigoEspecialTxt;
    BigDecimalField metaDiariaTxt;
    Usuario usuario;
    public UsuarioForm(Usuario usuario){

        this.usuario = usuario;

        setWidth("70%");
        setHeight("60%");
        crearFormLayout();

        if(usuario.getUsuario()!= null || !usuario.getUsuario().isEmpty()){
            llenarDatos();
        }

    }

    public void crearFormLayout(){

        H3 titulo = new H3("Ficha de Usuario");

        HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.add(titulo);
        titleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, titulo);

        FormLayout columnLayout = new FormLayout();
        columnLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));


        TextField usuarioTxt = new TextField("Usuario");
        Icon icon = new Icon(VaadinIcon.USER);
        usuarioTxt.getElement().appendChild(icon.getElement());

        PasswordField claveTxt = new PasswordField("Clave");

        TextField nombreTxt = new TextField("Nombre");

        ComboBox<Usuario.Perfil> perfilCbx = new ComboBox<>("Perfil");
        perfilCbx.setItems(Usuario.Perfil.values());

        EmailField emailTxt = new EmailField("Email");

        TextField telefonoTxt = new TextField("Telefono");

        TextField codigoEspecialTxt = new TextField("Codigo especial");

        BigDecimalField metaDiariaTxt = new BigDecimalField("Meta diaria");

        Button saveBtn = new Button("Guardar");

        Button deleteBtn = new Button("Eliminar");
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        columnLayout.add(usuarioTxt, claveTxt,  perfilCbx, emailTxt, nombreTxt);
        columnLayout.setColspan(nombreTxt, 2);
        columnLayout.add(telefonoTxt, codigoEspecialTxt, metaDiariaTxt);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(deleteBtn, saveBtn);

        buttonLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, saveBtn);

        add(titleLayout);

        add(columnLayout);

        add(buttonLayout);
    }

    public void llenarDatos(){
        usuarioTxt.setValue(usuario.getUsuario());
        claveTxt.setValue(usuario.getClave());
    }
}
