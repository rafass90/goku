package com.goku.ecommerce.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserUpdateDTO {

    @NotBlank(message = "username is required")
    private String username;

    private String password;

    private String name;
}
