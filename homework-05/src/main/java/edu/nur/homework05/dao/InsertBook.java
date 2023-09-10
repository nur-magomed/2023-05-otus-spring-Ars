package edu.nur.homework05.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertBook extends SqlUpdate {

    private static final  String SQL_INSERT_BOOK =
            "INSERT INTO t_book(title,  created_date, modified_date) " +
            "VALUES (:title, :created_date, :modified_date)";

    public InsertBook(DataSource dataSource) {
        super(dataSource, SQL_INSERT_BOOK);
        super.declareParameter(new SqlParameter("title", Types.VARCHAR));
        super.declareParameter(new SqlParameter("created_date", Types.DATE));
        super.declareParameter(new SqlParameter("modified_date", Types.DATE));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }

}
