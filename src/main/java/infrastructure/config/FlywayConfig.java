package infrastructure.config;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlywayConfig {

    public void migrate(DataSource dataSource) {
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }
}


