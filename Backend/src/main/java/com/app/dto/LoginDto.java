package com.app.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String email;
    private String password;
}
