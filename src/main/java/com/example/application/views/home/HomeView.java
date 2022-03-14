package com.example.application.views.home;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

@PageTitle("Home")
@Route(value = "home")
@JsModule("./recipe/copytoclipboard/copytoclipboard.js")
@Slf4j
public class HomeView extends VerticalLayout {

    public HomeView() {

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");

        String templateToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c[This is a template]";

        String accessToken = templateToken;
        String refreshToken = templateToken;

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

        });

        add(

                new H2("Your credentials are right here! :)"),
                new H5("Please be sure to keep these credentials private and confidential"),
                accessTxt,
                buttonCopy,
                refreshTxt,
                buttonCopy2,
                buttonLogout

        );

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

}
