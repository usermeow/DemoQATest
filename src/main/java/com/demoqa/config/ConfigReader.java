package com.demoqa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.demoqa.models.UserData;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Map;

public class ConfigReader {

    private static final String FILE_NAME = "test_data.yaml";

    @SneakyThrows
    public static UserData getUserFromYaml(String key) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            // Читаем YAML как Map, где ключ - название секции (например, defaultUser)
            Map<String, UserData> data = mapper.readValue(inputStream,
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, UserData.class));
            return data.get(key);
        }
    }
}