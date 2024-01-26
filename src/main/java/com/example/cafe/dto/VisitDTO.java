package com.example.cafe.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VisitDTO {
    private Date datetime;
    private Long clientId;

    private boolean isDateValid(){
        return new Date().after(this.datetime);
    }

    private boolean isClientIdValid(){
        return this.clientId > 0;
    }

    public  void checkIsDTOValid() throws IllegalArgumentException{
        if (!this.isClientIdValid())
            throw new IllegalArgumentException("Client id not valid.");
        if (!this.isDateValid())
            throw new IllegalArgumentException("Date has no arrived.");
    }
}
