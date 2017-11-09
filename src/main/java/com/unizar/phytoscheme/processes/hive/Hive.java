package com.unizar.phytoscheme.processes.hive;

import static com.unizar.phytoscheme.processes.common_methods.Common.executeExternalProcess;
import static com.unizar.phytoscheme.processes.common_methods.Common.fichero_configuracion;
import static com.unizar.phytoscheme.processes.common_methods.Common.getProperty;

/**
 * Created by catalin on 9/11/17.
 */
public class Hive {


    // Actualiza la base de datos Hive
    public static void updateHiveTable(String hadoop_dir, String hive_table) {
        System.out.println("Cargando datos de " + hadoop_dir + " a la tabla de Hive " + hive_table + ".");
        executeExternalProcess(getProperty("hive_executable_23") +
            " -hiveconf IMPORT_FROM_DIR="+ hadoop_dir +
            " -hiveconf IMPORT_INTO_TABLE="+ hive_table +
            " -f Scripts/Hive_Scripts/load_data_into_hive.hql");
    }
}
