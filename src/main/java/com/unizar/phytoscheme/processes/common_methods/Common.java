package com.unizar.phytoscheme.processes.common_methods;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Created by catalin on 2/11/17.
 */
public class Common {

    public static final String fichero_configuracion = "Scripts/fichero_configuracion.properties";


    public static Process startHadoop(){
        Process proc = executeExternalProcess(getProperty("start_hadoop_28"));
        return proc;
    }

    public static Process stopHadoop(){
        Process proc = executeExternalProcess(getProperty("stop_hadoop_29"));
        return proc;
    }
    // Devuelve del fichero de configuración el valor de la propiedad indicada en key.
    public static String getProperty(String key){
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
    public static Connection connect () {
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




    // Ejecuta un comando de shell y devuelve el resultado en forma de proceso.
    public static Process executeExternalProcess(String command){
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            final BufferedReader readerInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            final BufferedReader readerError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String lineInput = null;
            String lineError = null;
            while ((lineInput = readerInput.readLine()) != null) {
                System.out.println(lineInput);
            }
            readerInput.close();
            while ((lineError = readerError.readLine()) != null) {
                System.out.println(lineError);
            }
            readerError.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return proc;
    }



}
