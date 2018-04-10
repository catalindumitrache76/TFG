package com.unizar.phytoscheme.processes;

import com.unizar.phytoscheme.processes.common_methods.Common;
import com.unizar.phytoscheme.processes.hive.Hive;
import com.unizar.phytoscheme.processes.hive.Hive_Errors;
import com.unizar.phytoscheme.processes.talend.Talend;
import com.unizar.phytoscheme.processes.mysql.MySQL;
import com.unizar.phytoscheme.processes.sqoop.Sqoop;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by catalin on 8/11/17.
 */

@Component
public class Scheduller {

    private Process hadoop = null;

    /**
     * Método que se lanza cada 30 minutos y:
     * 1º Lanza el workflow que recoge los fitosanitarios desde la fuente y los importa en JHipster,
     *    pasándo su almacenamiento por Hadoop
     * 2º Lanza el workflow que recoge las sustancias activas desde la fuente y realiza la misma importación anterior
     * 3º Lanza el proceso que integra ambos datos en un único esquema
     * 4º Lanza el proceso que muestra las incongruencias encontradas.
     * 5º Está pendiente de finalizar Hadoop cuando el programa termine.
     */
    @Scheduled(initialDelay=1, fixedRate=1800000)
    private void schedule() {
        hadoop = Common.startHadoop();
        program_Workflow_SustanciActivaConTraza_Hadoop_JHipster();
//        program_Workflow_Fitosanitario_Hadoop_JHipster();
//        program_Workflow_SustanciActiva_Hadoop_JHipster();
//        program_Join_Fito_SustanciaActiva();
//        show_mismatches();
        terminarProcesoHadoopListener();
    }

    /**
     * Lanza el workflow que recoge los fitosanitarios desde la fuente y los importa en JHipster,
     * pasándo su almacenamiento por Hadoop
     */
    private static void program_Workflow_Fitosanitario_Hadoop_JHipster() {

        String hadoop_dir = "'/user/TFG/Datos_procesados/Espanya/Productos_autorizados'";
        String hive_db = "tfghivedb";
        String hive_table = "fitosanitario_con_id";
        String mysql_table = "fitosanitario";

        Hive.createFitosanitarioSpainHiveTable();

        Talend.launchTalendJobEspanya ();

        Hive.insertIntoHiveTable (hadoop_dir, hive_db, hive_table);

        MySQL.truncateMySQLTable(mysql_table);

        Sqoop.exportFromHiveToMySQL(hive_table,mysql_table);
    }

    /**
     * Lanza el workflow que recoge las sustancias activas desde la fuente y realiza la misma importación anterior
     */
    private static void program_Workflow_SustanciActiva_Hadoop_JHipster () {

        String hadoop_dir = "'/user/TFG/Datos_procesados/Europa/ActiveSubstances'";
        String hive_database = "tfghivedb";
        String hive_table = "sustancia_activa_europa";
        String mysql_table = "sustancia_activa_europa";

        Hive.createActiveSubstanceEuropeHiveTable();

        Talend.launchTalendJobEuropa ();

        Hive.insertIntoHiveTable(hadoop_dir, hive_database, hive_table);

        MySQL.truncateMySQLTable(mysql_table);

        Sqoop.exportFromHiveToMySQL(hive_table,mysql_table);

    }

    /**
     * Lanza el workflow que recoge las sustancias activas desde la fuente y realiza la misma importación anterior
     */
    private static void program_Workflow_SustanciActivaConTraza_Hadoop_JHipster () {

        String hadoop_dir = "'/user/TFG/Datos_procesados/Europa/ActiveSubstancesWithTrace'";
        String hive_database = "tfghivedb";
        String hive_table = "sustancia_activa_europa_con_traza";
        String mysql_table = "sustancia_activa_europa_con_traza";

        Hive.createActiveSubstanceEuropeWithTraceHiveTable();

        Talend.launchTalendJobEuropaWithTrace ();

        Hive.insertIntoHiveTable(hadoop_dir, hive_database, hive_table);

        MySQL.truncateMySQLTable(mysql_table);

        Sqoop.exportFromHiveToMySQL(hive_table,mysql_table);

    }


    /**
     * Lanza el proceso que integra los datos sobre productos fitosanitarios y
     * las sustancias activas en un único esquema
     */
    private static void program_Join_Fito_SustanciaActiva () {

        String hive_database = "tfghivedb";
        String hive_table = "fitosanitario_sustancia_activa_europa";
        String mysql_table= "fitosanitario_sustancia_activa_europa";
        String hive_error_table_1 = "mismatch_fitosanitario_fito-sustancia";
        String hive_error_table_2 = "mismatch_sustancia_fito-sustancia";

        Hive.createFitosanitarioSustanciaActivaEuropaHiveTable();

        Hive.truncateHiveTable(hive_database, hive_table);

        Hive.insertIntoFitosanitarioSustanciaActivaEuropaHiveTable();

        MySQL.truncateMySQLTable(mysql_table);

        Sqoop.exportFromHiveToMySQL(hive_table,mysql_table);

        /**
         * Registra los errores encontrados (mismatch del join) en unas tablas que crea dinámicamente en HIVE
         */
        Hive_Errors.mismatch_fitosanitario();
        Hive_Errors.mismatch_sustancia_activa();

        /**
         * Muestra dichos mismatch por pantalla
         */
        Hive.select(hive_database, hive_error_table_1);
        Hive.select(hive_database, hive_error_table_2);

    }

    /**
     * Lanza el proceso que muestra las incongruencias encontradas durante la integración de los datos de ambas fuentes.
     */
    private void show_mismatches() {
        String hive_database = "tfghivedb";
        String hive_error_table_1 = "mismatch_fitosanitario_fito_sustancia";
        String hive_error_table_2 = "mismatch_sustancia_fito_sustancia";

        /**
         * Registra los errores encontrados (mismatch del join) en unas tablas que crea dinámicamente en HIVE
         */
        Hive_Errors.mismatch_fitosanitario();
        Hive_Errors.mismatch_sustancia_activa();

        /**
         * Muestra dichos mismatch por pantalla
         */
        Hive.select(hive_database, hive_error_table_1);
        Hive.select(hive_database, hive_error_table_2);
    }

    /**
     * Cierra los procesos Hadoop al finalizar el programa.
     */
    private void terminarProcesoHadoopListener(){
        Process hadoop = this.hadoop;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (hadoop!=null) {
                    try {
                        Common.stopHadoop();
                        System.out.println("Hadoop Cerrado");
                        System.exit(0);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.exit(0);
                    }
                } else {
                    System.out.println("Hadoop estaba cerrado");
                    System.exit(0);
                }
            }
        });
    }


}
