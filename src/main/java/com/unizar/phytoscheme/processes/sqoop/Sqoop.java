package com.unizar.phytoscheme.processes.sqoop;

import static com.unizar.phytoscheme.processes.common_methods.Common.executeExternalProcess;
import static com.unizar.phytoscheme.processes.common_methods.Common.getProperty;

/**
 * Created by catalin on 8/11/17.
 */
public class Sqoop {

    public static void exportFromHiveToMySQL(String hiveTable, String mySQLTable){
        String command = getProperty("sqoop_executable_20") + " export --connect " +
            getProperty("database_connection_url_simple_21") + " --username " +
            getProperty("database_user_17") + " --password " +
            getProperty("database_password_18") + " --table " +
            mySQLTable + " --export-dir " +
            getProperty("hive_database_22") +
            hiveTable + "/ -m 1 --fields-terminated-by \";\" --direct \n";
        System.out.println("Lanzando comando de Sqoop para importar la tabla "+hiveTable +" desde " +
            "Hive a la tabla "+mySQLTable + " de MySQL.");
        System.out.println("Comando: " + command);

        executeExternalProcess(command);
    }

}
