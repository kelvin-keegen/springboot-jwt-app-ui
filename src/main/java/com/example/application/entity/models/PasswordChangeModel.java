package com.example.application.entity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by kelvin keegan on 30/03/2022
 */

@Getter
@Setter
@AllArgsConstructor
public class PasswordChangeModel {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

}
