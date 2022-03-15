package com.example.application.utils;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.springframework.stereotype.Service;

@Service
public class MyNotificationService {

    public Notification SendSuccessNotification(String message) {

        Notification successNotify = Notification.show(message);
        successNotify.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        successNotify.setPosition(Notification.Position.TOP_CENTER);

        return successNotify;
    }

    public Notification SendErrorNotification(String message) {

        Notification errorNotify = Notification.show(message);
        errorNotify.addThemeVariants(NotificationVariant.LUMO_ERROR);
        errorNotify.setPosition(Notification.Position.TOP_CENTER);

        return errorNotify;
    }
}
