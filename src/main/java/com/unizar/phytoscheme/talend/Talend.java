package com.unizar.phytoscheme.talend;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by catalin on 2/11/17.
 */
@Component
public class Talend {

    // cada 30 min
    @Scheduled(initialDelay=1000, fixedRate=1800000)
    public static void launchTalendJob () {
        // Run a java app in a separate system process
        System.out.println("Lanzando Job TalendCrawler cada 1800 segundos ... 30 min");
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("java -jar Talend/TalendCrawlerProject-1.0-SNAPSHOT.one-jar.jar");
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
