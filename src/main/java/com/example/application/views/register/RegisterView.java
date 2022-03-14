package com.example.application.views.register;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Register")
@Route(value = "register")
public class RegisterView extends VerticalLayout {

    public RegisterView() {

        TextField textFieldFirstName = new TextField("First name");
        TextField textFieldLastName = new TextField("Last name");
        TextField textFieldUserName = new TextField("Username");
        TextField textFieldEmail = new TextField("Email");
        PasswordField passwordField = new PasswordField("Create a Password");

        Button buttonSend = new Button("SignUp");
        buttonSend.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSend.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("login");

        });

        Button buttonLogin = new Button("Got an account? Let's go login right now!");
        buttonLogin.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        buttonLogin.addThemeVariants(ButtonVariant.LUMO_SMALL);
        buttonLogin.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("login");

        });



        add(
                new H1("Hello Stranger! want to SignUp?"),
                new H5("Let's get to know each other a bit better... :)"),
                textFieldFirstName,
                textFieldLastName,
                textFieldUserName,
                textFieldEmail,
                passwordField,
                buttonSend,
                buttonLogin,
                new H5("By Signing up you agree to the terms and conditions that are stipulated in the use of this SaaS.")
        );

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

}
