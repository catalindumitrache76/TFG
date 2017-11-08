package com.unizar.phytoscheme.mysql;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * Created by catalin on 2/11/17.
 */
@Component
public class MySQL {

    public static final String fichero_configuracion = "Scripts/fichero_configuracion.properties";

    // cada 30 min
    @Scheduled(initialDelay=1000, fixedRate=1800000)
    public static void query () {
        System.out.println("Query a MYSQL: cada 30 min");
        //selectAllFitosanitarios();
        //dropFitosanitarios();
        //createFitosanitarios();
    }


    // Devuelve del fichero de configuración el valor de la propiedad indicada en key.
    private static String getProperty(String key){
        String value = "";
        try {
            InputStream input = new FileInputStream(fichero_configuracion);
            Properties properties = new Properties();

            // load a properties file
            properties.load(input);

            // get the property value and print it out
            value = properties.getProperty(key);

            // close the InputStream
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


    // Establece la conexión con la base de datos MySQL
    private static Connection connect () {
        // create our mysql database connection
        String myDriver = getProperty("database_connection_driver_15");
        String myUrl = getProperty("database_connection_url_16");
        Connection conn = null;
        try {
            Class.forName(myDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(myUrl, getProperty("database_user_17"), getProperty("database_password_18"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    // Lanza la query select * from fitosanitarios;
    private static void selectAllFitosanitarios() {
        Connection conn = connect();
        try {
            // create the java statement
            Statement st = conn.createStatement();

            String query = "SELECT * FROM fitosanitario";

            // Execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                String numregistro = rs.getString("numregistro");
                String nombrecomercial = rs.getString("nombrecomercial");
                String titular = rs.getString("titular");
                String formulado = rs.getString("formulado");

                // print the results
                System.out.format("%s, %s, %s, %s\n", numregistro, nombrecomercial, titular, formulado);
            }
            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // Lanza la query DROP TABLE Fitosanitarios;
    private static void dropFitosanitarios() {

        Connection conn = connect();

        try {

            // create the java statement
            Statement st = conn.createStatement();

            String query = "DROP TABLE fitosanitario";

            st.executeUpdate(query);
            System.out.println("Table fitosanitario dropped in database ");

            st.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
        }

    }


    // Lanza la query CREATE TABLE Fitosanitarios;
    private static void createFitosanitarios() {

        Connection conn = connect();

        try {

            // create the java statement
            Statement st = conn.createStatement();

            String sql = "CREATE TABLE fitosanitario " +
                "(numregistro VARCHAR(255) not NULL, " +
                " nombrecomercial VARCHAR(255), " +
                " titular VARCHAR(255), " +
                " formulado VARCHAR(255))";

            st.executeUpdate(sql);
            System.out.println("Created table Fitosanitario in database ");

            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
