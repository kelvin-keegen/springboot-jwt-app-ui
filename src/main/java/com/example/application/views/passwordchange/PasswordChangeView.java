package com.example.application.views.passwordchange;

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
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Password Change")
@Route(value = "password-change")
public class PasswordChangeView extends VerticalLayout {

    @Autowired
    private MyNotificationService myNotificationService;

    public PasswordChangeView() {

        TextField textFieldEmail = new TextField("Email");
        textFieldEmail.setRequired(true);
        TextField textFieldOldPWD = new TextField("Old Password");
        textFieldOldPWD.setRequired(true);
        TextField textFieldNewPWD = new TextField("New Password");
        textFieldNewPWD.setRequired(true);
        TextField textFieldVeryPWD = new TextField("Confirm Password");
        textFieldVeryPWD.setRequired(true);

        Button buttonChange = new Button("Please change my Password");
        buttonChange.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button buttonTryLogin = new Button("Test new password", VaadinIcon.LOCK.create());
        buttonTryLogin.setEnabled(false);

        buttonChange.addClickListener(buttonClickEvent -> {

            if (textFieldEmail.isEmpty() || textFieldOldPWD.isEmpty() ||
                    textFieldNewPWD.isEmpty() || textFieldVeryPWD.isEmpty()) {

                if (textFieldEmail.isEmpty())
                    textFieldEmail.setInvalid(true);
                if (textFieldOldPWD.isEmpty())
                    textFieldOldPWD.setInvalid(true);
                if (textFieldNewPWD.isEmpty())
                    textFieldNewPWD.setInvalid(true);
                if (textFieldVeryPWD.isEmpty())
                    textFieldVeryPWD.setInvalid(true);

                myNotificationService.SendErrorNotification("Please fill all required fields")
                        .addDetachListener(detachEvent -> {

                            textFieldEmail.setInvalid(false);
                            textFieldOldPWD.setInvalid(false);
                            textFieldNewPWD.setInvalid(false);
                            textFieldVeryPWD.setInvalid(false);
                        });

            } else {

                myNotificationService.SendSuccessNotification("Password changed successfully!");
                buttonChange.setEnabled(false);
                buttonChange.setText("Password changed successfully");
                buttonTryLogin.setEnabled(true);

            }

        });


        buttonTryLogin.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("login");
        });

        add(
                new H1("Password Change"),
                new H5("Please make sure to remember your new password upon change."),
                textFieldEmail,
                textFieldOldPWD,
                textFieldNewPWD,
                textFieldVeryPWD,
                buttonChange,
                buttonTryLogin,
                new H5("A confirmation will be displayed upon successful password change")
        );

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

}
