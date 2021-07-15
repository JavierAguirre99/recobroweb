package org.vaadin.example.views.usuario;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.example.entidades.Usuario;
import org.vaadin.example.service.UsuarioService;
import org.vaadin.example.views.MainView;
import org.vaadin.example.views.usuario.UsuarioForm;

@Route( layout = MainView.class)
@PageTitle("Usuario | Recobro Web")
public class UsuarioView extends Div {

    Grid<Usuario> usuarioGrid = new Grid<>(Usuario.class);
    UsuarioService usuarioService = new UsuarioService();

    public UsuarioView(){
        setSizeFull();
        addClassName("empleado-view");

        usuarioGrid.setSizeFull();
        usuarioGrid.addClassName("empleado-grid");
        usuarioGrid.setColumns("usuario", "nombre", "perfil", "email", "telefono", "meta_diaria", "codigo_especial");

        usuarioGrid.getColumnByKey("meta_diaria").setHeader("Meta diaria").setTextAlign(ColumnTextAlign.END);
        usuarioGrid.getColumnByKey("codigo_especial").setHeader("Equipo");

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
        addEmpleadoButton.addClickListener(click -> new UsuarioForm(usuario, usuarioService).open());

        HorizontalLayout toolbar = new HorizontalLayout(addEmpleadoButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void llenarUsuario(){
        usuarioGrid.setItems(usuarioService.llenarListaUsuario());
    }

    public void enviarEmpleado(Usuario empleado) {

        if (empleado == null) {

        } else {

            new UsuarioForm(empleado, usuarioService).open();

        }
    }

}
