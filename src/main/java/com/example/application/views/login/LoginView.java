package com.example.application.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@PageTitle("Login")
@Route(value = "login")
@Slf4j
public class LoginView extends VerticalLayout {

    public LoginView() {

        TextField textFieldUserName = new TextField("Email *");
        PasswordField passwordField = new PasswordField("Password *");

        Button buttonSend = new Button("Login", VaadinIcon.UNLOCK.create());
        buttonSend.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSend.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("home");

        });

        Button buttonReset = new Button("Forgot your password? Click here to reset your password");
        buttonReset.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonReset.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("password-reset");

        });

        Button buttonRegister = new Button("Don't have an account? Let's go get an account right now");
        buttonRegister.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        buttonRegister.addThemeVariants(ButtonVariant.LUMO_SMALL);
        buttonRegister.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("register");

        });

        add(
                new H1("Hi there! Welcome back. :)"),
                new H5("Please input your credentials to proceed"),
                textFieldUserName,
                passwordField,
                buttonSend,
                buttonReset,
                buttonRegister,
                new Paragraph("Please, contact support@company.com if you're experiencing issues logging into your account")
        );

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

}
