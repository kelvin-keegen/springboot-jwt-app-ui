package com.example.application.views.register;

import com.example.application.utils.MyNotificationService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Register")
@Route(value = "register")
@Slf4j
public class RegisterView extends VerticalLayout {

    @Autowired
    private MyNotificationService myNotificationService;

    public RegisterView() {

        TextField textFieldFirstName = new TextField("First name");
        textFieldFirstName.setRequired(true);

        TextField textFieldLastName = new TextField("Last name");
        textFieldLastName.setRequired(true);

        TextField textFieldUserName = new TextField("Username");
        textFieldUserName.setRequired(true);

        TextField textFieldEmail = new TextField("Email");
        textFieldEmail.setRequired(true);

        PasswordField passwordField = new PasswordField("Create a Password");
        passwordField.setRequired(true);

        Button buttonSend = new Button("SignUp");
        buttonSend.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSend.addClickListener(buttonClickEvent -> {

            if (textFieldFirstName.isEmpty() || textFieldLastName.isEmpty() ||
            textFieldUserName.isEmpty() || textFieldEmail.isEmpty() || passwordField.isEmpty()) {

                if (textFieldFirstName.isEmpty())
                textFieldFirstName.setInvalid(true);
                if (textFieldLastName.isEmpty())
                textFieldLastName.setInvalid(true);
                if (textFieldUserName.isEmpty())
                textFieldUserName.setInvalid(true);
                if (textFieldEmail.isEmpty())
                textFieldEmail.setInvalid(true);
                if (passwordField.isEmpty())
                passwordField.setInvalid(true);

                myNotificationService.SendErrorNotification("Please fill all required fields")
                        .addDetachListener(detachEvent -> {

                            textFieldFirstName.setInvalid(false);
                            textFieldLastName.setInvalid(false);
                            textFieldUserName.setInvalid(false);
                            textFieldEmail.setInvalid(false);
                            passwordField.setInvalid(false);

                        });

            } else {

                myNotificationService.SendSuccessNotification("Thank you! Please proceed to login :)");

                UI.getCurrent().navigate("login");

            }

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
