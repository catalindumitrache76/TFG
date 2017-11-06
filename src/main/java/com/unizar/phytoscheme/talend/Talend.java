package com.unizar.phytoscheme.talend;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;


/**
 * Created by catalin on 2/11/17.
 */
@Component
public class Talend {

    // cada 30 min
    @Scheduled(initialDelay=1000, fixedRate=1800000)
    public static void programTalendJob () {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("fichero_configuracion.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("dir_proyecto_1"));
            System.out.println(prop.getProperty("autorizados_mapama_6"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Run a java app in a separate system process
        System.out.println("Lanzando Job TalendCrawler cada 1800 segundos ... 30 min");
        launchTalendJob ();
    }

    // Lanza el trabajo de Talend
    private static void launchTalendJob() {
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("java -jar Talend_Jars/TalendCrawlerProject-1.0-SNAPSHOT.one-jar.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            final BufferedReader readerInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            final BufferedReader readerError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String lineInput = null;
            String lineError = null;
            while ((lineInput = readerInput.readLine()) != null) {
                System.out.println(lineInput);
            }
            readerInput.close();
            while ((lineError = readerError.readLine()) != null) {
                System.out.println(lineError);
            }
            readerError.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
