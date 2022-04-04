package com.example.application.entity.models;

import com.example.application.entity.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by kelvin keegan on 04/04/2022
 */

@Getter
@Setter
@AllArgsConstructor
public class RegistrationModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserRoles clientRoles;

}
