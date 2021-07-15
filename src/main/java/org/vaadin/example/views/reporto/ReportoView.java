package org.vaadin.example.views.reporto;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.example.entidades.Reporto;
import org.vaadin.example.entidades.Usuario;
import org.vaadin.example.service.ReportoService;
import org.vaadin.example.service.UsuarioService;
import org.vaadin.example.views.MainView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Route( layout = MainView.class)
@PageTitle("Reporto de recuperación | Recobro WEB")
public class ReportoView extends VerticalLayout {

    Grid<Reporto> reportoGrid = new Grid<>(Reporto.class);

    HorizontalLayout componentesLayout = new HorizontalLayout();

    NumberField montoAcumuladoTxt, montoSeguroTxt, montoProbableTxt;

    TextField comentarioTxt;

    String idSesion, nombreSesion;

    H4 titulo;
    Label comentarioLbl;

    ReportoService reportoService;

    public static DecimalFormat numberFormat = new DecimalFormat("#,###,##0.00");

    public ReportoView(){

        //idSesion = authService.idSesion();
        //nombreSesion = authService.nombreSesion();

        addClassName("empleado-view");
        setSizeFull();

        HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.setWidthFull();
        titleLayout.setPadding(true);

        comentarioLbl = new Label();
        comentarioLbl.getStyle().set("margin-left", "auto");
        titulo = new H4("Reporto avance de meta de recuperación : " + nombreSesion);

        llenarLabelComentario();

        titleLayout.add(titulo, comentarioLbl);
        titleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.END, comentarioLbl);

        configurarGrid();
        crearComponentesReporto();
        llenarGridReporto();

        add(titleLayout, reportoGrid, componentesLayout);
        setHorizontalComponentAlignment(Alignment.CENTER, componentesLayout);

    }

    private void llenarLabelComentario() {
        //if (comentarioService.ultimoComenario().size() > 0) {
        //    comentarioLbl.setText("Comentario : " + comentarioService.ultimoComenario().get(0).getComentario());
       // }
    }

    private void configurarGrid() {

        reportoGrid.addClassName("empleado-grid");
        reportoGrid.setSizeFull();
        reportoGrid.removeColumnByKey("empleado");
        reportoGrid.setColumns("IdUsuario","monto_acumulado", "monto_seguro", "monto_probable", "comentario");
        reportoGrid.removeAllColumns();

        reportoGrid.addColumn(reporto -> {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String format = formatter.format(reporto.getFecha_hora());
            return reporto == null ? "-" : format;
        }).setHeader("Fecha");

        reportoGrid.addColumn(reporto -> {
            String hora = reporto.getFecha_hora().toString().substring(10, reporto.getFecha_hora().toString().length() - 2);
            return reporto == null ? "-" : hora;
        }).setHeader("Hora");

        reportoGrid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private void crearComponentesReporto() {

        montoAcumuladoTxt = new NumberField("Al momento :");

        montoSeguroTxt = new NumberField("Seguros :");

        montoProbableTxt = new NumberField("Probable :");

        comentarioTxt = new TextField("Comentario :");
        comentarioTxt.setWidth("20em");

        Button salirBtn = new Button("Salir");
        salirBtn.setWidth("6em");
        salirBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button reportarBtn = new Button("Reportar");
        reportarBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        reportarBtn.addClickListener(buttonClickEvent -> guardarReporte());

        componentesLayout.add(salirBtn, montoAcumuladoTxt, montoSeguroTxt, montoProbableTxt, comentarioTxt, reportarBtn);

        componentesLayout.setDefaultVerticalComponentAlignment(Alignment.END);
    }

    public void guardarReporte() {

        if (montoAcumuladoTxt.getValue() < 0.00){
            Notification.show("Por favor ingrese un monto acumulado.");
            montoAcumuladoTxt.focus();
            return;
        }
        if (montoProbableTxt.getValue() < 0.00){
            Notification.show("Por favor ingrese un monto probable.");
            montoProbableTxt.focus();
            return;
        }
        if (montoSeguroTxt.getValue() < 0.00){
            Notification.show("Por favor ingrese un monto seguro.");
            montoSeguroTxt.focus();
            return;
        }

        if (comentarioTxt.getValue().isEmpty()){
            Notification.show("Por favor ingrese un comentario.");
            comentarioTxt.focus();
            return;
        }

        List<Reporto> reportoList = reportoService.llenarReporto();

        Reporto reporto = new Reporto();

        Long nuevoSesion = Long.valueOf(idSesion);

        for (int i = 0; i < reportoList.size(); i++) {
       //     if (reportoList.get(i).get().equals(nuevoSesion)) {
        //        reporto.setIdUsuario(reportoList.get(i));
         //   }
        }

        Date fecha = Calendar.getInstance().getTime();
        reporto.setFecha_hora(fecha);
        reporto.setMonto_acumulado(montoAcumuladoTxt.getValue());
        reporto.setMonto_seguro(montoSeguroTxt.getValue());
        reporto.setMonto_probable(montoProbableTxt.getValue());
        reporto.setMeta_diaria(0.00);
        reporto.setComentario(comentarioTxt.getValue());

        try {

        //    reportoService.save(reporto);
            limpiarFormulario();
            llenarGridReporto();

        } catch (Exception e) {
            Notification.show("Error al momento de ingresar Reporto.");
            System.out.println("Error al momento de ingresar un reporto" + e);
        }
    }

    private void llenarGridReporto() {

        String idEnviar = idSesion;
        int empleadoint = Integer.parseInt(idEnviar);

        LocalDate now = LocalDate.now();

        System.out.println("now " + now);

        //reportoGrid.setItems(reportoService.llenarBitacora(empleadoint, String.valueOf(now)));
    }

    public void limpiarFormulario() {
        montoAcumuladoTxt.clear();
        montoProbableTxt.clear();
        montoSeguroTxt.clear();
        comentarioTxt.clear();
    }

}
