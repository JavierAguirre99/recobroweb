package org.vaadin.example.views.finiquitos;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.example.entidades.Finiquitos;
import org.vaadin.example.entidades.Usuario;
import org.vaadin.example.service.FiniquitosService;
import org.vaadin.example.service.UsuarioService;
import org.vaadin.example.views.MainView;
import org.vaadin.example.views.usuario.UsuarioForm;

@Route( layout = MainView.class)
@PageTitle("Finiquitos | Recobro Web")
public class FiniquitosView extends Div {

    Grid<Finiquitos> finiquitosGrid = new Grid<>(Finiquitos.class);

    TextField filterText = new TextField();

    public FiniquitosView(){
        setSizeFull();
        addClassName("empleado-view");

        finiquitosGrid.setSizeFull();
        finiquitosGrid.addClassName("empleado-grid");
        finiquitosGrid.setColumns("correlativo", "identificacion","nombre", "municipio", "departamento", "cuenta", "tipo", "observaciones");
        finiquitosGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        finiquitosGrid.asSingleSelect().addValueChangeListener(event ->
                enviarFiniquito(event.getValue()));

        Div content2 = new Div(finiquitosGrid);
        content2.addClassName("content");
        content2.setSizeFull();

        add(getToolbar(), content2);

        llenarUsuario();

    }

    private HorizontalLayout getToolbar() {
        Finiquitos finiquitos = new Finiquitos();
        Button nuevoBtn = new Button("Nuevo Finiquito");
        nuevoBtn.addClickListener(click -> new FiniquitosForm(finiquitos).open());

        HorizontalLayout toolbar = new HorizontalLayout(nuevoBtn);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void llenarUsuario(){
        FiniquitosService finiquitosService = new FiniquitosService();
        finiquitosGrid.setItems(finiquitosService.llenarListaFiniquitos());
    }

    public void enviarFiniquito(Finiquitos finiquito) {

        if (finiquito == null) {

        } else {

            new FiniquitosForm(finiquito).open();

        }
    }


}
