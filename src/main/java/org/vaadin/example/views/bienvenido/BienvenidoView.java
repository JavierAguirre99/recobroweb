package org.vaadin.example.views.bienvenido;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.example.views.MainView;

@Route(layout = MainView.class)
@PageTitle("Bienvenido | Recobro Web")
public class BienvenidoView extends Div {


    public BienvenidoView(){
        setSizeFull();
        VerticalLayout verticalLayout = new VerticalLayout();

        H1 bienvenido = new H1("BIENVENIDO(A) : ");

        H2 nombre = new H2("BIENVENIDO(A) : ");

        H3 ultimaconeccion = new H3("Su última fecha de conexión fue:14/07/2021 2:34:00 PM");

        verticalLayout.add(bienvenido, nombre, ultimaconeccion);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, bienvenido);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, nombre);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, ultimaconeccion);

        add(verticalLayout);
    }

}
