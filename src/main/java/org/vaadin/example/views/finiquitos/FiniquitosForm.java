package org.vaadin.example.views.finiquitos;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.example.entidades.Finiquitos;
import org.vaadin.example.entidades.Usuario;

import java.math.BigDecimal;

public class FiniquitosForm extends Dialog {

    TextField correlativoTxt;
    TextField identificacionTxt;
    TextField nombreTxt;
    TextField municipioTxt;
    TextField departamentoTxt;
    TextField cuentaTxt;
    ComboBox<Finiquitos.Tipo> tipoCbx;
    TextField observacionesTxt;

    Finiquitos finiquito;


    public FiniquitosForm(Finiquitos finiquito){
        this.finiquito = finiquito;
        setWidth("70%");
        setHeight("60%");
        crearFormLayout();

        if(finiquito.getCorrelativo().equals("")){

        }
    }
    public void crearFormLayout(){

        H3 titulo = new H3("Ficha de Finiquito");

        HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.add(titulo);
        titleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, titulo);

        FormLayout columnLayout = new FormLayout();
        columnLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));


        correlativoTxt = new TextField("Correlativo :");
        Icon icon = new Icon(VaadinIcon.USER);
        correlativoTxt.getElement().appendChild(icon.getElement());

        identificacionTxt = new TextField("Identificación :");

        nombreTxt = new TextField("Nombre :");

        departamentoTxt = new TextField("Departamento :");

        municipioTxt = new TextField("Municipio :");

        cuentaTxt = new TextField("Cuenta :");

        tipoCbx = new ComboBox<>("Tipo :");
        tipoCbx.setItems(Finiquitos.Tipo.values());

        observacionesTxt = new TextField("Observaciones :");

        Button saveBtn = new Button("Guardar");

        Button deleteBtn = new Button("Eliminar");
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        columnLayout.add(correlativoTxt, identificacionTxt);
        columnLayout.setColspan(nombreTxt, 2);
        columnLayout.add(departamentoTxt, municipioTxt, cuentaTxt);
        columnLayout.add(tipoCbx, observacionesTxt);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(deleteBtn, saveBtn);

        buttonLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, saveBtn);

        add(titleLayout);

        add(columnLayout);

        add(buttonLayout);
    }

    public void llenarDatos(){

        correlativoTxt.setValue(String.valueOf(finiquito.getCorrelativo()));
        identificacionTxt.setValue(finiquito.getIdentificacion());
        nombreTxt.setValue(finiquito.getNombre());
        departamentoTxt.setValue(finiquito.getDepartamento());
        municipioTxt.setValue(finiquito.getMunicipio());
        cuentaTxt.setValue(finiquito.getCuenta());
        observacionesTxt.setValue(finiquito.getObservaciones());
        tipoCbx.setValue(finiquito.getTipo());
    }

}
