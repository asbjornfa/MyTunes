package DAL;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;


public class MyDatabaseConnector {


    private SQLServerDataSource dataSource;

    public MyDatabaseConnector()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.34");
        dataSource.setDatabaseName("MytunesYTMusic_MRS2023");
        dataSource.setUser("CSe2023a_e_10");
        dataSource.setPassword("CSe2023aE10#23");
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);

    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();

    }

    public static void main(String[] args) throws SQLException {

        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {

            System.out.println("Is it open? " + !connection.isClosed());
        }

        //Connection gets closed here
    }


}


