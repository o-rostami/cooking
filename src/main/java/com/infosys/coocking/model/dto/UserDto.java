package com.infosys.coocking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String userName;
    private String password;
    private String email;
}
