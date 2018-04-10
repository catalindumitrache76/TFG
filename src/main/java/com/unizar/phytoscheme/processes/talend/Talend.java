package com.unizar.phytoscheme.processes.talend;


import static com.unizar.phytoscheme.processes.common_methods.Common.executeExternalProcess;
import static com.unizar.phytoscheme.processes.common_methods.Common.fichero_configuracion;
import static com.unizar.phytoscheme.processes.common_methods.Common.getProperty;


/**
 * Created by catalin on 2/11/17.
 */
public class Talend {


    // Lanza el trabajo de Talend para los datos de los productos autorizados de España
    public static void launchTalendJobEspanya() {
        System.out.println("------------------------------->>>>>>Lanzando TalendCrawlerEspanya.");
        executeExternalProcess("java -jar Talend_Jars/" +
            "TalendCrawlerEspanya.one-jar.jar " + fichero_configuracion);
        executeExternalProcess("rm " + getProperty("crudo_espanya_autorizados_5") + "*");
    }

    // Lanza el trabajo de Talend para los datos de las sustancias activas de Europa
    public static void launchTalendJobEuropa() {
        System.out.println("Lanzando TalendActiveSubstancesEuropaCrawler.");
        executeExternalProcess("java -jar Talend_Jars/" +
            "TalendActiveSubstancesEuropaCrawler-1.0.one-jar.jar " + fichero_configuracion);
    }

}
