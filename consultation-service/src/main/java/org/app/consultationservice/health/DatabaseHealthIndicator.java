package org.app.consultationservice.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseHealthIndicator {

    private final DataSource dataSource;

    public boolean isDatabaseHealthy() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(1);
        } catch (SQLException e) {
            log.error("Database health check failed", e);
            return false;
        }
    }

    public String getDatabaseStatus() {
        if (isDatabaseHealthy()) {
            return "UP - PostgreSQL database is available";
        } else {
            return "DOWN - PostgreSQL database is unavailable";
        }
    }
}
