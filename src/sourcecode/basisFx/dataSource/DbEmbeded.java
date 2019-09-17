package basisFx.dataSource;

import basisFx.DbSchemaPrice;

import java.sql.*;


public class DbEmbeded extends Db{
    
   protected Statement statement = null;

    public DbEmbeded() throws ClassNotFoundException, SQLException {
          

        try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                Db.connection = DriverManager.getConnection(
                        "jdbc:hsqldb:file:database/db", "SA", "");    
                statement=this.connection.createStatement();
                statement.setQueryTimeout(30);
                
                System.out.println("Database connected!");
        
        } catch (Exception e) {
                
                System.out.println("Сбой при подключении к БД!");
                System.err.println( e.getMessage());
               
            
        }
        
        init();

}
    
    private void init(){
        
        new DbSchemaPrice();
    
    }


    @Override
    public Connection newConnection() {
        return null;
    }
}

