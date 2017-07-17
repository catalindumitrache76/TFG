package com.mycompany.myapp.config;

import java.sql.*;


public class HiveJdbcClient {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "catalin", "");
        Statement stmt = con.createStatement();
        String tableName = "pruebaDeTabla";
        System.out.println("> Conexión establecida con Hive");
        stmt.execute("drop table " + tableName);
        boolean executed = stmt.execute("create table " + tableName + " (key int, value string)");
        if (executed) {
            System.out.println("> Creada la tabla pruebaDeTabla en HIVE");
        }
        // show tables
        String sql = "show tables";
        System.out.println("> Ejecutando: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
        // describe table
        sql = "describe " + tableName;
        System.out.println("> Ejecutando: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }

        // load data into table
        // NOTE: filepath has to be local to the hive server
        // NOTE: /tmp/a.txt is a ctrl-A separated file with two fields per line
        String filepath = System.getProperty("user.dir") + "/Hive_Tables_Scripts/create_jhi_user_authority.sql";
        sql = "load data local inpath '" + filepath + "' into table " + tableName;
        System.out.println("> Ejecutando: " + sql);
        executed = stmt.execute(sql);
        System.out.println ("> Cargando el fichero "+ filepath + " en HIVE SERVER");


        // select * query
        sql = "select * from " + tableName;
        System.out.println("> Ejecutando: : " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
        }

        // regular hive query
        sql = "select count(*) from " + tableName;
        System.out.println("> Ejecutando: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }
}
