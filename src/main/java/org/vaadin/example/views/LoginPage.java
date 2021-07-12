package org.vaadin.example.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import org.vaadin.example.views.MainView;
import org.vaadin.example.views.UsuarioView;

@Route("")
public class LoginPage extends Composite<LoginOverlay> {
    LoginOverlay loginOverlay = getContent();

    public LoginPage() {
        loginOverlay.setI18n(createPortugueseI18n());
        loginOverlay.setOpened(true);
        loginOverlay.addLoginListener(loginEvent -> {
            validarUsuarios(loginEvent.getUsername(), loginEvent.getPassword());
        });

        loginOverlay.addForgotPasswordListener(forgotPasswordEvent -> {
            Notification.show("Opcion aun no disponible.");
        });

    }

    private void validarUsuarios(String usuario, String clave){
        RouteConfiguration configuration =
                RouteConfiguration.forSessionScope();


        if ("admin".equals(usuario)) {
            configuration.setRoute("", UsuarioView.class,
                    MainView.class);
        }

        UI.getCurrent().getPage().reload();
    }

    private LoginI18n createPortugueseI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Recobro WEB");
        i18n.getHeader().setDescription("Desarrollado por simpletecno");
        i18n.getForm().setUsername("Usuario");
        i18n.getForm().setTitle("Iniciar sesion");
        i18n.getForm().setSubmit("Entrar");
        i18n.getForm().setPassword("Clave");
        i18n.getForm().setForgotPassword("Olvide mi contraseña.");
        i18n.getErrorMessage().setTitle("Usuario/Clave incorrectos");
        i18n.getErrorMessage()
                .setMessage("Confirme su usuario y contraseña nuevamente.");
        //i18n.setAdditionalInformation(
        //        "Caso necessite apresentar alguma informação extra para o usuário"
        //                + " (como credenciais padrão), este é o lugar.");
        return i18n;
    }
}