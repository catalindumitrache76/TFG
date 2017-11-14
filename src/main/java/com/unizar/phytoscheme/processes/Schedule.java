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

        MySQL.truncateFitosanitario();

        Sqoop.exportFromHiveToMySQL(hive_table,mysql_table);
    }


    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void program_Workflow_SustanciActiva_Hadoop_JHipster () {

        String hadoop_dir = "'/user/TFG/Datos_procesados/Europa/ActiveSubstances'";
        String hive_database = "tfghivedb";
        String hive_table = "sustancia_activa_europa";

        Talend.launchTalendJobEuropa ();

        Hive.createActiveSubstanceEuropeHiveTable();

//        Hive.insertIntoHiveTable(hadoop_dir, hive_database, hive_table);

    }

/*

    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void programTalendJob () {
        launchTalendJobEuropa();
    }
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void programTalendJob () {
        launchTalendJob ();
    }

    // cada 30 min
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void programHiveJob () {
        System.out.println("Lanzando Job de Hive cada 1800 segundos ... 30 min");
        String hadoop_dir = "'/user/TFG/Datos_procesados/Espanya/Productos_autorizados'";
        String hive_table = "tfghivedb.fitosanitario_con_id";
        updateHiveTable (hadoop_dir, hive_table);
    }

    // cada 30 min
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void programSqoopJob () {
        String hiveTable = "fitosanitario_con_id" ;
        String mysqlTable = "fitosanitario";

        truncateFitosanitario();
        exportFromHiveToMySQL(hiveTable,mysqlTable);
    }

    // cada 30 min
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void mySQLQuery () {
        selectAllFitosanitario();
        dropFitosanitario();
        createFitosanitario();
        truncateFitosanitario();
    }
    */

}
