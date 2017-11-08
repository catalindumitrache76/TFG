package com.unizar.phytoscheme.talend;


import static common.Common.executeExternalProcess;
import static common.Common.fichero_configuracion;
import static common.Common.getProperty;


/**
 * Created by catalin on 2/11/17.
 */
public class Talend {



    // Lanza el trabajo de Talend
    private static void launchTalendJob() {
        executeExternalProcess("java -jar Talend_Jars/" +
            "TalendCrawlerEspanya.one-jar.jar " + fichero_configuracion);
    }

    private static void exportFromHiveToMySQL(String HiveTable, String MySQLTable){
        String command = getProperty("sqoop_executable_20") + " export --connect " +
            getProperty("database_connection_url_simple_21") + " --username " +
            getProperty("database_user_17") + " --password " +
            getProperty("database_password_18") + " --table " +
            MySQLTable + " --export-dir " +
            getProperty("hive_database_22") +
            HiveTable + "/ -m 1\n";
        System.out.println("Lanzando comando de Sqoop para importar la tabla "+HiveTable +" desde " +
            "Hive a la tabla "+MySQLTable + " de MySQL.");
        System.out.println("Comando: " + command);
        executeExternalProcess(command);
    }
}
