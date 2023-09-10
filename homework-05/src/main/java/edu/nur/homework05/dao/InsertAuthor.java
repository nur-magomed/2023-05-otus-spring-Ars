package edu.nur.homework05.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertAuthor extends SqlUpdate {

    private static final  String SQL_INSERT_AUTHOR =
            "INSERT INTO t_author(first_name, last_name, birth_date, created_date, modified_date) " +
            "VALUES (:first_name, :last_name, :birth_date, :created_date, :modified_date)";

    public InsertAuthor(DataSource dataSource) {
        super(dataSource,  SQL_INSERT_AUTHOR);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.declareParameter(new SqlParameter("created_date", Types.DATE));
        super.declareParameter(new SqlParameter("modified_date", Types.DATE));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }

}
