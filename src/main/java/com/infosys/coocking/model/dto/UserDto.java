package com.infosys.coocking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String userName;
    private String password;
    @Email
    private String email;
}
