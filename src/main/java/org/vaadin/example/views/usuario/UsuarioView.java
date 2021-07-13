package org.vaadin.example.views.usuario;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import org.vaadin.example.entidades.Usuario;
import org.vaadin.example.service.UsuarioService;
import org.vaadin.example.views.usuario.UsuarioForm;

@PageTitle("Usuario | Recobro Web")
public class UsuarioView extends Div {

    Grid<Usuario> usuarioGrid = new Grid<>(Usuario.class);

    public UsuarioView(){
        setSizeFull();
        addClassName("empleado-view");

        usuarioGrid.setSizeFull();
        usuarioGrid.addClassName("empleado-grid");
        usuarioGrid.setColumns("usuario", "clave");
        usuarioGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        usuarioGrid.asSingleSelect().addValueChangeListener(event ->
                enviarEmpleado(event.getValue()));

        Div content2 = new Div(usuarioGrid);
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
        usuarioGrid.setItems(usuarioService.llenarListaUsuario());
    }

    public void enviarEmpleado(Usuario empleado) {

        if (empleado == null) {

        } else {

            new UsuarioForm(empleado).open();

        }
    }

}
