package com.example.cafe.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDTO {
    private String name;
    private String email;
    private Date birthDate;

}
