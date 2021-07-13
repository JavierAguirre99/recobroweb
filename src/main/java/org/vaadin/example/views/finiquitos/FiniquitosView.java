package org.vaadin.example.views.finiquitos;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import org.vaadin.example.entidades.Finiquitos;
import org.vaadin.example.entidades.Usuario;
import org.vaadin.example.service.UsuarioService;
import org.vaadin.example.views.usuario.UsuarioForm;

@PageTitle("Finiquitos | Recobro Web")
public class FiniquitosView extends Div {

    Grid<Usuario> finiquitosGrid = new Grid<>(Usuario.class);

    public Grid<Finiquitos> gridFiniquitos = new Grid<>(Finiquitos.class);

    TextField filterText = new TextField();

    public FiniquitosView(){
        setSizeFull();
        addClassName("empleado-view");

        finiquitosGrid.setSizeFull();
        finiquitosGrid.addClassName("empleado-grid");
        finiquitosGrid.setColumns("usuario", "clave");
        finiquitosGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        finiquitosGrid.asSingleSelect().addValueChangeListener(event ->
                enviarEmpleado(event.getValue()));

        Div content2 = new Div(finiquitosGrid);
        content2.addClassName("content");
        content2.setSizeFull();

        add(getToolbar(), content2);

        llenarUsuario();

    }

    private HorizontalLayout getToolbar() {
        Usuario usuario = new Usuario();
        Button addEmpleadoButton = new Button("Nuevo usuario");
        addEmpleadoButton.addClickListener(click -> new UsuarioForm(usuario).open());

        HorizontalLayout toolbar = new HorizontalLayout(addEmpleadoButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void llenarUsuario(){
        UsuarioService usuarioService = new UsuarioService();
        finiquitosGrid.setItems(usuarioService.llenarListaUsuario());
    }

    public void enviarEmpleado(Usuario empleado) {

        if (empleado == null) {

        } else {

            new UsuarioForm(empleado).open();

        }
    }


}
