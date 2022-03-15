package com.example.application.views.passwordreset;

import com.example.application.utils.MyNotificationService;
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
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Password Reset")
@Route(value = "password-reset")
@Slf4j
public class PasswordResetView extends VerticalLayout {

    @Autowired
    private MyNotificationService myNotificationService;

    public PasswordResetView() {

        TextField textFieldEmail = new TextField("Email");
        textFieldEmail.setRequired(true);

        Button buttonReset = new Button("Reset");
        buttonReset.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonReset.addClickListener(buttonClickEvent -> {

            if (textFieldEmail.isEmpty()) {

                textFieldEmail.setInvalid(true);

                myNotificationService.SendErrorNotification("Your registered Email address is required")
                        .addDetachListener(detachEvent -> {

                            textFieldEmail.setInvalid(false);

                        });
            }

            if (!textFieldEmail.isEmpty()) {

                buttonReset.setEnabled(false);
                myNotificationService.SendSuccessNotification("Please check your email for password reset instructions");

            }
        });

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
