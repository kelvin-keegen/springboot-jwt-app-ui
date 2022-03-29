package com.example.application.views.login;

import com.example.application.entity.models.ApiResponseBody;
import com.example.application.utils.MyNotificationService;
import com.example.application.utils.RestClientService;
import com.example.application.utils.RetrievalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

@PageTitle("Login")
@Route(value = "login")
@Slf4j
public class LoginView extends VerticalLayout {

    @Autowired
    private MyNotificationService myNotificationService;
    @Autowired
    private RestClientService restClientService;
    @Autowired
    private RetrievalService retrievalService;

    @Value("${api-server.login.link}")
    private String serverLink;

    public String CurrentToken;

    public LoginView() {

        TextField textFieldUserName = new TextField("Email");
        textFieldUserName.setRequired(true);
        PasswordField passwordField = new PasswordField("Password");
        passwordField.setRequired(true);

        Button buttonSend = new Button("Login", VaadinIcon.UNLOCK.create());
        buttonSend.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSend.addClickListener(buttonClickEvent -> {

            if (textFieldUserName.isEmpty() || passwordField.isEmpty()) {

                String message = "";

                if (textFieldUserName.isEmpty()) {

                    message = "Email is required";
                    textFieldUserName.setInvalid(true);
                    textFieldUserName.setErrorMessage(message);
                }

                 if (passwordField.isEmpty()) {

                     message = "Password is required";
                     passwordField.setInvalid(true);
                     passwordField.setErrorMessage(message);
                 }

                myNotificationService.SendErrorNotification("Email and Password are both required")
                        .addDetachListener(detachEvent -> {

                            buttonSend.setEnabled(true);
                            textFieldUserName.setInvalid(false);
                            textFieldUserName.setErrorMessage("");
                            passwordField.setInvalid(false);
                            passwordField.setErrorMessage("");

                        });

            } else {

                buttonSend.setEnabled(false);

                // Sending Request to server

                MediaType mediaType = MediaType.parseMediaType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
                MultiValueMap<String,String> authObjectModel = new LinkedMultiValueMap<>();
                authObjectModel.add("username",textFieldUserName.getValue());
                authObjectModel.add("password", passwordField.getValue());

                ApiResponseBody responseBody = restClientService.Http_POST_ResponseBody(serverLink,mediaType,authObjectModel,"");

                if (responseBody.getMessage() == null) {

                    myNotificationService.SendErrorNotification("Internal server error")
                            .addDetachListener(detachEvent -> {

                                buttonSend.setEnabled(true);

                            });
                }

                if (responseBody.getMessage() != null && responseBody.getData() != null) {

                    // Unpack data
                    String accessTokenValue = retrievalService.GetObjValue(responseBody.getData(),"accessToken").toString();
                    String refreshTokenValue = retrievalService.GetObjValue(responseBody.getData(),"refreshToken").toString();

                    if (!accessTokenValue.isEmpty() && !refreshTokenValue.isEmpty()) {

                        UI.getCurrent().getSession().setAttribute("accessToken",accessTokenValue);   // per user.
                        UI.getCurrent().getSession().setAttribute("refreshToken",refreshTokenValue);

                        UI.getCurrent().navigate("home");

                    } else {

                        myNotificationService.SendErrorNotification("Something went wrong!")
                                .addDetachListener(detachEvent -> {

                                    buttonSend.setEnabled(true);

                                });

                    }

                } else {

                    myNotificationService.SendErrorNotification(responseBody.getMessage())
                            .addDetachListener(detachEvent -> {

                                buttonSend.setEnabled(true);

                            });
                }

            }

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
