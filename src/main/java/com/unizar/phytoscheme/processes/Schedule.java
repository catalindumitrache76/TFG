package com.unizar.phytoscheme.processes;

import com.unizar.phytoscheme.processes.hive.Hive;
import com.unizar.phytoscheme.processes.talend.Talend;
import com.unizar.phytoscheme.processes.mysql.MySQL;
import com.unizar.phytoscheme.processes.sqoop.Sqoop;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by catalin on 8/11/17.
 */

@Component
public class Schedule {

    public static void program_Join_Fito_SustanciaActiva () {
        String hive_database = "tfghivedb";
        String hive_table = "fitosanitario_sustancia_activa_europa";

        Hive.createFitosanitarioSustanciaActivaEuropaHiveTable();

        Hive.truncateHiveTable(hive_database, hive_table);

        Hive.insertIntoFitosanitarioSustanciaActivaEuropaHiveTable();

    }

    //     cada 30 min
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void program_Workflow_Fitosanitario_Hadoop_JHipster() {

        String hadoop_dir = "'/user/TFG/Datos_procesados/Espanya/Productos_autorizados'";
        String hive_database = "tfghivedb";
        String hive_table = "fitosanitario_con_id";
        String mysql_table = "fitosanitario";

        Hive.createFitosanitarioSpainHiveTable();

        Talend.launchTalendJobEspanya ();

        Hive.insertIntoHiveTable (hadoop_dir, hive_database, hive_table);

        MySQL.truncateMySQLTable(mysql_table);

        Sqoop.exportFromHiveToMySQL(hive_table,mysql_table);
    }


    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void program_Workflow_SustanciActiva_Hadoop_JHipster () {

        String hadoop_dir = "'/user/TFG/Datos_procesados/Europa/ActiveSubstances'";
        String hive_database = "tfghivedb";
        String hive_table = "sustancia_activa_europa";
        String mysql_table = "sustancia_activa_europa";

        Talend.launchTalendJobEuropa ();

        Hive.createActiveSubstanceEuropeHiveTable();

        Hive.insertIntoHiveTable(hadoop_dir, hive_database, hive_table);

        MySQL.truncateMySQLTable(mysql_table);

        Sqoop.exportFromHiveToMySQL(hive_table,mysql_table);

    }

}
