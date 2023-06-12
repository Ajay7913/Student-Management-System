package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminDto {
    private String name;
    private int id;
    private String role;

    public AdminDto(String name, int id, String role) {
        super();
        this.name = name;
        this.role = role;
        this.id= id;
    }
    public AdminDto( int id, String role) {
        super();
       
        this.role = role;
        this.id= id;
    }
}
