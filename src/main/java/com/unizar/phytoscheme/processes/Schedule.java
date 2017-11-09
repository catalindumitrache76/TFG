package com.unizar.phytoscheme.processes;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.unizar.phytoscheme.processes.hive.Hive.updateHiveTable;
import static com.unizar.phytoscheme.processes.mysql.MySQL.*;
import static com.unizar.phytoscheme.processes.sqoop.Sqoop.*;
import static com.unizar.phytoscheme.processes.talend.Talend.*;

/**
 * Created by catalin on 8/11/17.
 */

@Component
public class Schedule {

    // cada 30 min
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void programHadoop_JHipster() {

        System.out.println("Lanzando Job TalendCrawler.");

        launchTalendJob ();

        System.out.println("Lanzando Job de Hive.");

        String hadoop_dir = "'/user/TFG/Datos_procesados/Espanya/Productos_autorizados'";
        String hive_table = "tfghivedb.fitosanitario_con_id";

        updateHiveTable (hadoop_dir, hive_table);

        String hiveTable = "fitosanitario_con_id" ;
        String mysqlTable = "fitosanitario";

        System.out.println("Lanzando Job de Sqoop.");

        truncateFitosanitario();

        exportFromHiveToMySQL(hiveTable,mysqlTable);
    }
/*
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void programTalendJob () {
        System.out.println("Lanzando Job TalendCrawler cada 1800 segundos ... 30 min");
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
        System.out.println("Lanzando Job de Sqoop cada 1800 segundos ... 30 min");

        truncateFitosanitario();
        exportFromHiveToMySQL(hiveTable,mysqlTable);
    }

    // cada 30 min
    @Scheduled(initialDelay=1, fixedRate=1800000)
    public static void mySQLQuery () {
        System.out.println("Query a MYSQL: cada 1800 segundos ... 30 min");
        selectAllFitosanitario();
        dropFitosanitario();
        createFitosanitario();
        truncateFitosanitario();
    }
    */

}
