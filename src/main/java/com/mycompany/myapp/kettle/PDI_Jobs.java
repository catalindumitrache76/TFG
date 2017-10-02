package com.mycompany.myapp.kettle;

import org.apache.hadoop.fs.Path;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.Trans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class PDI_Jobs {


    private static void copiaFicheroDeHadoopALocal(Configuration conf, String remote, String locale) throws IOException, URISyntaxException{

        FileSystem fileSystem = FileSystem.get(new URI("hdfs://localhost:54310"),conf);

        if(fileSystem instanceof DistributedFileSystem) {
            System.out.println("HDFS is the underlying filesystem");

            Path remoto = new Path(remote);
            Path local = new Path(locale);

            fileSystem = FileSystem.get(conf);
            fileSystem.copyToLocalFile(remoto, local);
        }
        else {
            System.out.println("Other type of file system "+fileSystem.getClass());
        }
    }


    private static void copiaFicheroLocalAHadoop(Configuration conf, String locale, String remote) throws IOException, URISyntaxException{

        FileSystem fileSystem = FileSystem.get(new URI("hdfs://localhost:54310"),conf);
        if(fileSystem instanceof DistributedFileSystem) {
            System.out.println("HDFS is the underlying filesystem");

            Path local = new Path(locale);
            Path remoto = new Path(remote);

            fileSystem = FileSystem.get(conf);
            fileSystem.copyFromLocalFile(local, remoto);
        }
        else {
            System.out.println("Other type of file system "+fileSystem.getClass());
        }
    }

    public static void main (String[] args) throws IOException, URISyntaxException {

        Configuration conf = new Configuration();
        conf.addResource(new Path("/home/catalin/Escritorio/TFG/HADOOP/hadoop-2.8.0/etc/hadoop/core-site.xml"));
        conf.addResource(new Path("/home/catalin/Escritorio/TFG/HADOOP/hadoop-2.8.0/etc/hadoop/hdfs-site.xml"));


        /* Copia el fichero salida_a_fichero_externo.txt a HADOOP */
//        copiaFicheroLocalAHadoop(conf, "/home/catalin/Escritorio/TFG/TFG/Kettle_Scripts/salida_a_fichero_externo.txt", "/user/data/prueba_concepto");

        /* Copia el fichero de Hadoop a la carpeta Kettle_Scripts */
//        copiaFicheroDeHadoopALocal(conf,"/user/data/prueba_concepto/salida_a_fichero_externo.txt", "/home/catalin/Escritorio/TFG/TFG/Kettle_Scripts/salida_a_fichero_externo_desde_hadoop.txt");



        try {
            KettleEnvironment.init(false);
            EnvUtil.environmentInit();
            TransMeta transMeta;

            // Funciona a pesar de mostrar muchos mensajes de error en la consola
            transMeta = new TransMeta("Kettle_Scripts/A_fichero_externo.ktr");

            Trans trans = new Trans(transMeta);
            trans.setLogLevel(LogLevel.DEBUG);

            trans.execute(null); // You can pass arguments instead of null.
            trans.waitUntilFinished();
            if ( trans.getErrors() > 0 )
            {
                throw new RuntimeException( "There were errors during transformation execution." );
            }
        }
        catch (KettleException e ) {
            System.out.println(e.getMessage());

            e.printStackTrace();
        }


    }



}
