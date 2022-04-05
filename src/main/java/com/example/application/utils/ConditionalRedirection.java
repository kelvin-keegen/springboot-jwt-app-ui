package com.example.application.utils;

import com.vaadin.flow.component.UI;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by kelvin keegan on 05/04/2022
 */

@Slf4j
public class ConditionalRedirection {

    public String SessionVariableExtractor(String var) {

        try {

            return UI.getCurrent().getSession().getAttribute(var).toString();

        } catch (Exception exception) {

            log.warn("exception caught : session variable = [{}] is [{}] -> redirection executed successfully!",var,exception.getMessage());
            UI.getCurrent().getPage().setLocation("login");

        }

        return "";
    }

}
