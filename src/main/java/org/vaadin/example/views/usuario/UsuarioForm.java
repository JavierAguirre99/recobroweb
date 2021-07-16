package org.vaadin.example.views.usuario;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.example.entidades.Usuario;
import org.vaadin.example.service.UsuarioService;

import java.math.BigDecimal;

public class UsuarioForm extends Dialog {

    UI mainUI;
    TextField usuarioTxt;
    PasswordField claveTxt;
    TextField nombreTxt;
    ComboBox<Usuario.Perfil> perfilCbx;
    EmailField emailTxt;
    TextField telefonoTxt;
    TextField codigoEspecialTxt;
    BigDecimalField metaDiariaTxt;

    Usuario usuario;
    int vandera = 0; // Nuevo

    UsuarioService usuarioService;

    public UsuarioForm(Usuario usuario, UsuarioService usuarioService){

        this.mainUI = UI.getCurrent();
        this.usuario = usuario;
        this.usuarioService = usuarioService;

        setWidth("70%");
        setHeight("60%");
        crearFormLayout();

        if(usuario.getUsuario() != null){
            llenarDatos();
            vandera =1; // EDITAR
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


        usuarioTxt = new TextField("Usuario");
        Icon icon = new Icon(VaadinIcon.USER);
        usuarioTxt.getElement().appendChild(icon.getElement());

        claveTxt = new PasswordField("Clave");

        nombreTxt = new TextField("Nombre");

        perfilCbx = new ComboBox<>("Perfil");
        perfilCbx.setItems(Usuario.Perfil.values());

        emailTxt = new EmailField("Email");

        telefonoTxt = new TextField("Telefono");

        codigoEspecialTxt = new TextField("Codigo especial");

        metaDiariaTxt = new BigDecimalField("Meta diaria");

        Button guardarBtn = new Button("Guardar");
        guardarBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        guardarBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                usuario.setUsuario(usuarioTxt.getValue());
                usuario.setClave(claveTxt.getValue());
                usuario.setPerfil(perfilCbx.getValue());
                usuario.setEmail(emailTxt.getValue());
                usuario.setNombre(nombreTxt.getValue());
                usuario.setTelefono(telefonoTxt.getValue());
                usuario.setCodigoEspecial(codigoEspecialTxt.getValue());
                usuario.setMetaDiaria(Double.valueOf(String.valueOf(metaDiariaTxt.getValue())));

                usuarioService.guardarUsuario(vandera, usuario);

                if (vandera == 1){
                    Notification.show("Registro modificado con exito!");
                }else{
                    Notification.show("Registro agregado con exito!");
                }


                UI.getCurrent().getPage().reload();

            }
        });

        Button eliminarBtn = new Button("Eliminar");
        //eliminarBtn.addClickListener(e -> close());
        eliminarBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button salirBtn = new Button("Salir");
        salirBtn.addClickListener(event -> close());
        salirBtn.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        columnLayout.add(usuarioTxt, claveTxt,  perfilCbx, emailTxt, nombreTxt);
        columnLayout.setColspan(nombreTxt, 2);
        columnLayout.add(telefonoTxt, codigoEspecialTxt, metaDiariaTxt);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(salirBtn, eliminarBtn, guardarBtn);

        buttonLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, salirBtn);
        buttonLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, eliminarBtn);
        buttonLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, guardarBtn);

        add(titleLayout);

        add(columnLayout);

        add(buttonLayout);
    }

    public void llenarDatos(){
        usuarioTxt.setValue(usuario.getUsuario());
        claveTxt.setValue(usuario.getClave());
        nombreTxt.setValue(usuario.getNombre());
        perfilCbx.setValue(usuario.getPerfil());
        telefonoTxt.setValue(usuario.getTelefono());
        emailTxt.setValue(usuario.getEmail());
        codigoEspecialTxt.setValue(usuario.getCodigoEspecial());
        metaDiariaTxt.setValue(BigDecimal.valueOf(usuario.getMetaDiaria().doubleValue()));
    }
}
