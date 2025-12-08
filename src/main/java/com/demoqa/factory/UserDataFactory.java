package com.demoqa.factory;

import com.demoqa.config.ConfigReader;
import com.demoqa.models.UserData;

public class UserDataFactory {

    // Создаем пользователя из YAML
    public static UserData createDefaultUser() {
        return ConfigReader.getUserFromYaml("defaultUser");
    }

    // Создаем кастомного пользователя через Builder
    public static UserData createCustomUser(String name, String email) {
        return UserData.builder()
                .fullName(name)
                .email(email)
                .currentAddress("Random Address")
                .permanentAddress("Another Address")
                .build();
    }
}