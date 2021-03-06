package com.example.application.views.passwordchange;

import com.example.application.entity.models.ApiResponseBody;
import com.example.application.entity.models.PasswordChangeModel;
import com.example.application.utils.ConditionalRedirection;
import com.example.application.utils.MyNotificationService;
import com.example.application.utils.RestClientService;
import com.example.application.views.home.HomeView;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

@PageTitle("Password Change")
@Route(value = "password-change",layout = HomeView.class)
public class PasswordChangeView extends VerticalLayout {

    @Autowired
    private MyNotificationService myNotificationService;

    @Autowired
    private RestClientService restClientService;

    @Value("${api-server.pwd-change.link}")
    private String serverLink;

    private final String emailAddress;

    public PasswordChangeView() {

        emailAddress = new ConditionalRedirection().SessionVariableExtractor("email");

        add(SetContentOnView());
    }

    private VerticalLayout SetContentOnView() {

        VerticalLayout verticalLayout = new VerticalLayout();

        PasswordField textFieldOldPWD = new PasswordField("Old Password");
        textFieldOldPWD.setRequired(true);
        PasswordField textFieldNewPWD = new PasswordField("New Password");
        textFieldNewPWD.setRequired(true);
        PasswordField textFieldVeryPWD = new PasswordField("Confirm Password");
        textFieldVeryPWD.setRequired(true);

        Button buttonChange = new Button("Please change my Password");
        buttonChange.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button buttonTryLogin = new Button("Test new password", VaadinIcon.LOCK.create());
        buttonTryLogin.setEnabled(false);

        buttonChange.addClickListener(buttonClickEvent -> {

            if (textFieldOldPWD.isEmpty() ||
                    textFieldNewPWD.isEmpty() || textFieldVeryPWD.isEmpty()) {

                if (textFieldOldPWD.isEmpty())
                    textFieldOldPWD.setInvalid(true);
                if (textFieldNewPWD.isEmpty())
                    textFieldNewPWD.setInvalid(true);
                if (textFieldVeryPWD.isEmpty())
                    textFieldVeryPWD.setInvalid(true);

                myNotificationService.SendErrorNotification("Please fill all required fields")
                        .addDetachListener(detachEvent -> {

                            textFieldOldPWD.setInvalid(false);
                            textFieldNewPWD.setInvalid(false);
                            textFieldVeryPWD.setInvalid(false);
                        });

            } else {

                PasswordChangeModel passwordChangeModel = new PasswordChangeModel(

                        textFieldOldPWD.getValue(),
                        textFieldNewPWD.getValue(),
                        textFieldVeryPWD.getValue()

                );

                ApiResponseBody response = ResponseOnPasswordChange(emailAddress,passwordChangeModel);

                if (response.getStatusCode() == 200){

                    myNotificationService.SendSuccessNotification("Password changed successfully!");
                    buttonChange.setEnabled(false);
                    buttonChange.setText("Password changed successfully");
                    buttonTryLogin.setEnabled(true);


                } else {

                    myNotificationService.SendErrorNotification(response.getMessage())
                            .addDetachListener(detachEvent -> {

                                buttonChange.setEnabled(true);
                                buttonTryLogin.setEnabled(false);

                            });
                }
            }

        });

        buttonTryLogin.addClickListener(buttonClickEvent -> {

            UI.getCurrent().getSession().close();
            UI.getCurrent().getPage().setLocation("login");

        });

        verticalLayout.add(

                new H1("Password Change"),
                new H5("Please make sure to remember your new password upon change."),
                textFieldOldPWD,
                textFieldNewPWD,
                textFieldVeryPWD,
                buttonChange,
                buttonTryLogin,
                new H5("A confirmation will be displayed upon successful password change")
        );

        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return verticalLayout;
    }

    private ApiResponseBody ResponseOnPasswordChange(String email,PasswordChangeModel passwordChangeModel) {

        String link = serverLink+"?email="+email;
        MediaType mediaType = MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE);
        String token = UI.getCurrent().getSession().getAttribute("accessToken").toString();

        return restClientService.Http_POST_ResponseBody(link,mediaType,passwordChangeModel,token);

    }

}
