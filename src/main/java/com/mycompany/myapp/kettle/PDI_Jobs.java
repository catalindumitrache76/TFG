package com.mycompany.myapp.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.plugins.PluginFolder;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.Trans;


/**
 * Created by catalin on 17/08/17.
 */

public class PDI_Jobs {

    public static void main (String[] args) {
        // funciona = true -> Se ejecuta el job que realiza output a un fichero de texto
        // funciona = false -> Se ejecuta el job que realiza output a Hadoop (no funciona)
        boolean funciona = true;
        try {
            StepPluginType.getInstance().getPluginFolders().add(new PluginFolder("/home/catalin/Escritorio/TFG/Kettle/plugins", false, true));
            StepPluginType.getInstance().getPluginFolders().add(new PluginFolder("/home/catalin/Escritorio/TFG/Plugins_Kettle", false, true));

            KettleEnvironment.init();

            TransMeta transMeta;

            if (funciona) {
                // Este funciona a pesar de mostrar muchos mensajes de error en la consola
                transMeta = new TransMeta("Kettle_Scripts/A_fichero_externo.ktr");
            }else {
                //Este no funciona porque le falta un plugin para hadoop ... creo que el de pentaho-big-data-plugin
                transMeta = new TransMeta("Kettle_Scripts/A_Hadoop.ktr");
            }

            Trans trans = new Trans(transMeta);

            trans.execute(null); // You can pass arguments instead of null.
            trans.waitUntilFinished();
            if ( trans.getErrors() > 0 )
            {
                throw new RuntimeException( "There were errors during transformation execution." );
            }
        }
        catch (Exception e ) {
            // TODO Put your exception-handling code here.
            System.out.println(e);
        }
    }

}
