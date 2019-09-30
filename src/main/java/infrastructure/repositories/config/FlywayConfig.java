package infrastructure.repositories.config;

import org.flywaydb.core.Flyway;

public class FlywayConfig {

    public void migrate() {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", null).load();
        flyway.migrate();
    }
}


