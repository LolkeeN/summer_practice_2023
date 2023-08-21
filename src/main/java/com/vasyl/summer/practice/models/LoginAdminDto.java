package com.vasyl.summer.practice.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginAdminDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String exchangeName;

    @NotNull
    private String partnerId;

}
