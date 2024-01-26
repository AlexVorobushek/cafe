package com.example.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private String email;
    private Date birthDate;

    private boolean isAgeLegal(){
        Date today = new Date();
        Calendar deadLine = Calendar.getInstance();
        deadLine.setTime(today);

        deadLine.add(Calendar.YEAR, -14);
        return deadLine.getTime().after(this.birthDate);
    }

    private boolean isEmailValid(){
        return Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/ =?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\ [\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0 -9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5 [0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}( ?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[ a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                .matcher(this.email)
                .matches();
    }

    private boolean isNameValid(){
        return (this.name.length() > 4);
    }

    public void checkIsValid() throws IllegalArgumentException{
        if (!this.isAgeLegal())
            throw new IllegalArgumentException("Sorry, we don't serve customers under the age of 14.");
        if (!this.isEmailValid())
            throw new IllegalArgumentException("Email address not valid.");
        if (!this.isNameValid())
            throw new IllegalArgumentException("Enter current name.");
    }
}
