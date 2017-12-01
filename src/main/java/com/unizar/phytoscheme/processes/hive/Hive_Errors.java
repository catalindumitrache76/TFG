package com.unizar.phytoscheme.processes.hive;

import static com.unizar.phytoscheme.processes.common_methods.Common.executeExternalProcess;
import static com.unizar.phytoscheme.processes.common_methods.Common.getProperty;

/**
 * Created by catalin on 22/11/17.
 */
public class Hive_Errors {


    // Almacena la información de los productos fitosanitarios autorizados de España que no han sido mapeados
    // con las sustancias activas europeas en una tabla nueva llamada mismatch_fitosanitario_fito_sustancia
    public static void mismatch_fitosanitario() {
        System.out.println("Almacenando la información de los productos fitosanitarios autorizados que no " +
            "han sido mapeados con las sustancias activas europeas");
        executeExternalProcess(getProperty("hive_executable_23") +
            " -f Scripts/Hive_Scripts/mismatch_fitosanitario_fito_sustancia.hql");
    }

    // Almacena la información de las sustancias activas autorizadas que no han sido mapeados
    // con los productos fitosanitarios autorizados de España en una tabla nueva llamada
    // mismatch_sustancia_fito_sustancia
    public static void mismatch_sustancia_activa() {
        System.out.println("Almacenando la información de los productos fitosanitarios autorizados que no " +
            "han sido mapeados con las sustancias activas europeas");
        executeExternalProcess(getProperty("hive_executable_23") +
            " -f Scripts/Hive_Scripts/mismatch_sustancia_fito_sustancia.hql");
    }

}
