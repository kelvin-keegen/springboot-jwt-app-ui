package com.example.application.views.home;

import com.example.application.utils.RetrievalService;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.SessionDestroyEvent;
import com.vaadin.flow.server.SessionDestroyListener;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kelvin keegan on 30/03/2022
 */

@Route(value = "credentials",layout = HomeView.class)
@Slf4j
public class CredentialsView extends VerticalLayout {

    public CredentialsView() {

        add(CredentialsTabComponents());
    }

    private VerticalLayout CredentialsTabComponents() {

        VerticalLayout verticalLayout = new VerticalLayout();

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");

        String templateToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c[This is a template]";
        String accessToken = "";
        String refreshToken = "";

        accessToken = UI.getCurrent().getSession().getAttribute("accessToken").toString();
        refreshToken = UI.getCurrent().getSession().getAttribute("refreshToken").toString();

        TextArea accessTxt = new TextArea("Access Token");
        TextArea refreshTxt = new TextArea("Refresh Token");
        accessTxt.setWidthFull();
        refreshTxt.setWidthFull();
        accessTxt.setValue(accessToken);
        refreshTxt.setValue(refreshToken);

        Button buttonCopy = new Button("Copy", VaadinIcon.COPY.create());
        buttonCopy.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonCopy.addClickListener(buttonClickEvent -> {

            // Copy to clipboard
            String textToCopy = accessTxt.getValue();
            UI.getCurrent().getPage().executeJs("window.copyToClipboard($0)",textToCopy);

        });

        Button buttonCopy2 = new Button("Copy",VaadinIcon.COPY.create());
        buttonCopy2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonCopy2.addClickListener(buttonClickEvent -> {

            // Copy to clipboard
            String textToCopy = refreshTxt.getValue();
            UI.getCurrent().getPage().executeJs("window.copyToClipboard($0)",textToCopy);

        });

        Button buttonLogout = new Button("Logout from account");
        buttonLogout.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("login");

            // Invalidate session attributes
            UI.getCurrent().getSession().setAttribute("accessToken",null);
            UI.getCurrent().getSession().setAttribute("refreshToken",null);
            UI.getCurrent().getSession().setAttribute("email",null);

        });

        verticalLayout.add(

                new H2("Your credentials are right here! :)"),
                new H5("Please be sure to keep these credentials private and confidential"),
                accessTxt,
                buttonCopy,
                refreshTxt,
                buttonCopy2,
                buttonLogout

        );

        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        return verticalLayout;
    }
}
