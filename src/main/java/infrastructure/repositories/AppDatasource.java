package infrastructure.repositories;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;

public class AppDatasource {

    public DataSource create() {
        return JdbcConnectionPool.create("jdbc:h2:mem:test", "username", "password");
    }
}
