//package com.mycompany.myapp.kettle;
//
//import org.pentaho.di.core.KettleEnvironment;
//import org.pentaho.di.core.plugins.PluginFolder;
//import org.pentaho.di.core.plugins.StepPluginType;
//import org.pentaho.di.trans.TransMeta;
//import org.pentaho.di.trans.Trans;
///**
// * Created by catalin on 17/08/17.
// */
//
//public class PDI_Jobs {
//
//    public static void main (String[] args) {
//        try {
//            StepPluginType.getInstance().getPluginFolders().add(new PluginFolder("/home/catalin/Escritorio/TFG/Kettle/plugins", false, true));
//
//            KettleEnvironment.init();
//
//            TransMeta transMeta = new TransMeta("Kettle_Scripts/T1.ktr");
//            Trans trans = new Trans(transMeta);
//
//            trans.execute(null); // You can pass arguments instead of null.
//            trans.waitUntilFinished();
//            if ( trans.getErrors() > 0 )
//            {
//                throw new RuntimeException( "There were errors during transformation execution." );
//            }
//        }
//        catch (Exception e ) {
//            // TODO Put your exception-handling code here.
//            System.out.println(e);
//        }
//    }
//
//}
