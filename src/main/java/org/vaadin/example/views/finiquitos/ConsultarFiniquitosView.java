package org.vaadin.example.views.finiquitos;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.vaadin.example.entidades.Finiquitos;

public class ConsultarFiniquitosView extends Div {

    public Grid<Finiquitos> gridFiniquitos = new Grid<>(Finiquitos.class);

    TextField filterText = new TextField();

    public ConsultarFiniquitosView(){
        addClassName("finiquito-view");
        setSizeFull();
        crearGridFiniquitos();

        Div content2 = new Div(gridFiniquitos);
        content2.addClassName("content");
        content2.setSizeFull();

        add(getToolbar(), content2);
        llenarGridFiniquitos();

    }

    private void crearGridFiniquitos() {

        gridFiniquitos.addClassName("finiquito-grid");
        gridFiniquitos.setSizeFull();
        gridFiniquitos.setColumns("correlativo", "identificacion", "nombre", "departamento", "municipio", "cuenta", "tipo", "observaciones");

        gridFiniquitos.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private HorizontalLayout getToolbar() {
        filterText.setTitle("Buscador :");
        filterText.setPlaceholder("Buscar por nombre, identificacion, cuenta...");
        filterText.setWidth("30em");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> llenarGridFiniquitos());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void llenarGridFiniquitos() {
        //gridFiniquitos.setItems();
    }
}
