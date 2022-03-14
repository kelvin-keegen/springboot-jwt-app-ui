package com.example.application.views.passwordchange;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Password Change")
@Route(value = "password-change")
public class PasswordChangeView extends VerticalLayout {

    public PasswordChangeView() {

        TextField textFieldEmail = new TextField("Email *");
        TextField textFieldOldPWD = new TextField("Old Password *");
        TextField textFieldNewPWD = new TextField("New Password *");
        TextField textFieldVeryPWD = new TextField("Confirm Password *");

        Button buttonChange = new Button("Please change my Password");
        buttonChange.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(
                new H1("Password Change"),
                new H5("Please make sure to remember your new password upon change."),
                textFieldEmail,
                textFieldOldPWD,
                textFieldNewPWD,
                textFieldVeryPWD,
                buttonChange,
                new H5("A confirmation will be displayed upon successful password change")
        );

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

}
