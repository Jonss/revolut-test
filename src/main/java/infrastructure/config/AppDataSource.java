package infrastructure.config;

import org.h2.jdbcx.JdbcConnectionPool;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;

public class AppDataSource {

    // TODO get env vars from application.conf
    public Jdbi create() {
        DataSource dataSource = JdbcConnectionPool.create(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                "sa",
                "");
        return Jdbi.create(dataSource);
    }

}
