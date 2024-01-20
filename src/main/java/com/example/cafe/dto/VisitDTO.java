package com.example.cafe.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VisitDTO {
    private Date datetime;
    private Long clientId;
}
