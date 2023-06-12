package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FacultyDto {


    private String name;
    private int id;
    private String role;

    public FacultyDto(String name, int id, String role) {
        super();
        this.name = name;
        this.role = role;
        this.id= id;
    }
}
