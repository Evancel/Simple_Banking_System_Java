package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final String parameterName = "-fileName";
    private static Connection connection;

    public static Connection getConnection(String[] args){
        if(connection != null){
            return connection;
        }

        String url = getUrl(args);
        if(url == null){
            System.out.println("URL is invalid");
            return null;
        }

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

    private static String getUrl(String[] args){
        if(args.length != 2){
            return  null;
        }

        if(!args[0].equals(parameterName)){
            return  null;
        }

        String dbname = args[1];
        return "jdbc:sqlite:" + dbname;
    }

}
