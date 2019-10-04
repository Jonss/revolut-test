package infrastructure.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;

public class AppDataSource {

    // TODO get env vars from application.conf
    public Jdbi create(DataSource dataSource) {
        return Jdbi.create(dataSource);
    }

    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;

        config.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;" );
        config.setUsername("sa");
        config.setPassword("sa");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );

        return ds;
    }

}
