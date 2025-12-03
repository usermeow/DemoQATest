package com.demoqa.model;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String gender; // Male, Female, Other
    private String mobile; // 10 digits
    // Данные для календаря
    private String birthDay;
    private String birthMonth;
    private String birthYear;

    private String subject; // Например, "Maths"
    private String hobby;   // Sports, Reading, Music
    private File picture;
    private String address;
    private String state;
    private String city;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFullDateOfBirth() {
        // Формат, который возвращает модалка: 15 January,1990
        return birthDay + " " + birthMonth + "," + birthYear;
    }
}