package com.avi.batch.writer;

import com.avi.batch.model.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBWriter implements ItemWriter<User> {

    private final String DEST = "E://batch//destination.txt";

    JdbcBatchItemWriter<User> writer =  null;

    public  DBWriter() throws IOException {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:9393/student");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");

        ItemPreparedStatementSetter<User> preparedStatementSetter =  new ItemPreparedStatementSetter<User>(){
            @Override
            public void setValues(User item, PreparedStatement ps) throws SQLException {
                ps.setInt(1, item.getId());
                ps.setString(2, item.getName());
            }
        };

        writer = new JdbcBatchItemWriterBuilder<User>()
                .dataSource(dataSource)
                .sql("INSERT INTO batch (sno, name) VALUES (?, ?)")
                .itemPreparedStatementSetter(preparedStatementSetter)
                .build();
    }
    @Override
    public void write(List<? extends User> items) throws Exception {
     items.forEach(user -> System.out.println(user.getId() +" : "+ user.getName()));
     writer.write(items);
    }
}
