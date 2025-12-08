package com.demoqa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String fullName;
    private String email;
    private String currentAddress;
    private String permanentAddress;
}