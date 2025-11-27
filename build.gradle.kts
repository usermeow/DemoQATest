plugins {
    java
    id("io.qameta.allure") version "2.11.2" // Плагин для генерации отчетов Allure
}

group = "com.demoqa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Версии библиотек вынесены в переменные для удобства
val selenideVersion = "7.4.2"
val testngVersion = "7.10.2"
val allureVersion = "2.27.0"
val jacksonVersion = "2.17.2" // Для парсинга YAML
val aspectjVersion = "1.9.22"

dependencies {
    // Selenide (обертка над Selenium)
    implementation("com.codeborne:selenide:$selenideVersion")

    // TestNG (Тест раннер)
    testImplementation("org.testng:testng:$testngVersion")

    // Allure (Отчетность)
    implementation("io.qameta.allure:allure-testng:$allureVersion")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
    testImplementation("org.aspectj:aspectjweaver:1.9.22")
    // Jackson Dataformat YAML (для чтения test_data.yaml)
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")

    // Lombok (для сокращения бойлерплейта в PageObject и Models - по желанию, но рекомендуется)
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    // Logging (SLF4J simple implementation для вывода логов в консоль)
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

allure {
    report {
        version.set(allureVersion) // Используем переменную allureVersion
    }
    adapter {
        aspectjWeaver.set(true) // Автоматическое подключение javaagent
        frameworks {
            testng {
                enabled.set(true) // Явное включение TestNG
            }
        }
    }
}
tasks.withType<Test> {
    useTestNG() // Используем TestNG
    // Запускаем тесты параллельно (если нужно, можно убрать)
    // maxParallelForks = 4

    // --- КРИТИЧЕСКИ ВАЖНЫЙ БЛОК ДЛЯ ИСПРАВЛЕНИЯ ОШИБКИ JVM ---
    // Указываем JVM, что нужно использовать AspectJ Weaver для Allure аннотаций (@Step)
    // Это исправляет ошибки "Error opening zip file" и "agent library failed"
    doFirst {
        val aspectjweaver = configurations.testRuntimeClasspath.get().find { it.name.contains("aspectjweaver") }

        // Проверяем, что AspectJ Weaver найден
        if (aspectjweaver != null) {
            jvmArgs("-javaagent:${aspectjweaver.absolutePath}")
        } else {
            logger.warn("AspectJ Weaver JAR not found in testRuntimeClasspath. Allure annotations might not work.")
        }
    }
}