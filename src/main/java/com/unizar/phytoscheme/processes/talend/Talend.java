package com.unizar.phytoscheme.processes.talend;


import static com.unizar.phytoscheme.processes.common_methods.Common.executeExternalProcess;
import static com.unizar.phytoscheme.processes.common_methods.Common.fichero_configuracion;


/**
 * Created by catalin on 2/11/17.
 */
public class Talend {


    // Lanza el trabajo de Talend
    public static void launchTalendJob() {
        executeExternalProcess("java -jar Talend_Jars/" +
            "TalendCrawlerEspanya.one-jar.jar " + fichero_configuracion);
    }


}
