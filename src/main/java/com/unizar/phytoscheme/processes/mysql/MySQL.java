package com.unizar.phytoscheme.mysql;

import java.sql.*;

import static common.Common.connect;
import static common.Common.getProperty;


/**
 * Created by catalin on 2/11/17.
 */
public class MySQL {




    // Lanza la query SELECT * FROM fitosanitario;
    public static void selectAllFitosanitario() {
        Connection conn = connect();
        try {
            // create the java statement
            Statement st = conn.createStatement();

            String query = "SELECT * FROM fitosanitario";

            // Execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            int registros = 0;
            // iterate through the java resultset
            while (rs.next())
            {
                registros ++;
                String numregistro = rs.getString("numregistro");
                String nombrecomercial = rs.getString("nombrecomercial");
                String titular = rs.getString("titular");
                String formulado = rs.getString("formulado");

                // print the results
                System.out.format("%s, %s, %s, %s\n", numregistro, nombrecomercial, titular, formulado);
            }
            System.out.println("Registros en la tabla: "+registros);
            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // Lanza la query DROP TABLE fitosanitario;
    public static void dropFitosanitario() {

        Connection conn = connect();

        try {

            // create the java statement
            Statement st = conn.createStatement();

            String query = "DROP TABLE fitosanitario";

            st.executeUpdate(query);
            System.out.println("Table fitosanitario dropped in database " + getProperty("database_in_use_19"));

            st.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
        }

    }


    // Lanza la query TRUNCATE TABLE fitosanitario;
    public static void truncateFitosanitario() {

        Connection conn = connect();

        try {

            // create the java statement
            Statement st = conn.createStatement();

            String query = "TRUNCATE TABLE fitosanitario";

            st.executeUpdate(query);
            System.out.println("Table fitosanitario truncated in database " + getProperty("database_in_use_19"));

            st.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
        }

    }


    // Lanza la query CREATE TABLE fitosanitario;
    public static void createFitosanitario() {

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
            System.out.println("Created table Fitosanitario in database " + getProperty("database_in_use_19"));

            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
