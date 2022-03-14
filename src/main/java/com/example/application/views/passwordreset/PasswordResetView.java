package com.example.application.views.passwordreset;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

@PageTitle("Password Reset")
@Route(value = "password-reset")
@Slf4j
public class PasswordResetView extends VerticalLayout {

    public PasswordResetView() {

        TextField textFieldEmail = new TextField("Email *");

        Button buttonReset = new Button("Reset");
        buttonReset.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button buttonBack2Login = new Button("Back to Login", VaadinIcon.BACKSPACE_A.create());
        buttonBack2Login.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("login");
        });

        add(
                new H1("Password Reset"),
                new H5("Enter your email address for account verification."),
                textFieldEmail,
                buttonReset,
                buttonBack2Login,
                new H5("Please check your email inbox for the randomly generated password.")

                );

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }

}
