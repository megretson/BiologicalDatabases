package org.openapitools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
// JPA drop and create table, good for testing
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
class OpenApiGeneratorApplicationTests {

        // static, all tests share this postgres container
        @Container
        @ServiceConnection
        static MySQLContainer<?> postgres = new MySQLContainer<>(
                "mysql:8.0-debian"
        );

    @Test
    void contextLoads() {
    }

}