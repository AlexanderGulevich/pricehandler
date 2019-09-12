/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.dataSource;

import basisFx.appCore.utils.PropertiesUtils;
import basisFx.appCore.utils.Registry;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

import java.sql.DriverManager;
import java.sql.Statement;

public class DbServetHsql extends Db{

    protected Statement statement = null;
    private String db_name= PropertiesUtils.getProperty("db_name");
    private String db_path=PropertiesUtils.getProperty("db_path");
    private String db_folder=PropertiesUtils.getProperty("db_folder");
    private DbSchema dbSchema;

    public DbServetHsql(DbSchema dbSchema)  {

        this.dbSchema=dbSchema;

        if (db_path == null) {
            Registry.windowFabric.infoWindow("В локальных свойствах не задан путь БД");
        }else {
            init();
            dbSchema.create();
        }
    }

    private void init() {
        HsqlProperties props = new HsqlProperties();
        props.setProperty("server.database.0", "file:" + db_path +db_folder+"/"+ db_name);
        props.setProperty("server.dbname.0", db_name);
        props.setProperty("server.remote_open", true);
        props.setProperty("hsqldb.tx", " MVCC");


        sonicServer = new Server();

        try {
            sonicServer.setProperties(props);
        } catch (Exception e) {

            Registry.windowFabric.infoWindow("sonicServer.setProperties(props)   не сработал!!\n\n".toUpperCase()+ e.getMessage() );
            System.err.println(e.getMessage());
        }

        try {
            sonicServer.start();
        } catch (Exception e) {
            Registry.windowFabric.infoWindow("sonicServer.start()   не сработал!!\n\n".toUpperCase()+ e.getMessage() );
            System.err.println(e.getMessage());
        }

//           sonicServer.shutdown();

        createConnection();
    }

    private void createConnection() {
        try {

            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
            Db.connection= DriverManager.getConnection(
//                    "jdbc:hsqldb:hsql://localhost:9001/", "SA", "");
                    "jdbc:hsqldb:hsql://localhost/"+db_name, "SA", "");
//                    "jdbc:hsqldb:http://localhost/"+db_name, "SA", "");
            statement=this.connection.createStatement();
            statement.setQueryTimeout(30);

        } catch (Exception e) {
            Registry.windowFabric.infoWindow("Не получилось подключиться к базе данных!!\n\n".toUpperCase()+ e.getMessage() );
            System.err.println(e.getMessage());

        }
    }

}
