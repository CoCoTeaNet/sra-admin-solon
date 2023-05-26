package net.cocotea.admin.bootstrap.config;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    //typed=true，表示默认数据源。@Db 可不带名字注入
    @Bean(name = "db1", typed = true)
    public DataSource db1(@Inject("${sra.db1}") HikariDataSource ds) {
        return ds;
    }

}
