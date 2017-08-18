package com.mycompany.myapp.kettle;


import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.PluginFolder;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.Trans;
/**
 * Created by catalin on 17/08/17.
 */

public class PDI_Jobs {

    public static void main (String[] args) {
        try {
            StepPluginType.getInstance().getPluginFolders().add(new PluginFolder("/home/catalin/Escritorio/TFG/Kettle/plugins", false, true));

            KettleEnvironment.init();

            TransMeta transMeta = new TransMeta("Kettle_Scripts/T1.ktr");
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

//    public static void main(String[] args) {
//        try {
//            KettleEnvironment.init();
//
//            DatabaseMeta dbm = new DatabaseMeta();
//
//            dbm.setDatabaseInterface(DatabaseMeta.getDatabaseInterface("Hadoop Hive"));
//
//            dbm.setName("DB Conn");
//
//            dbm.setHostname("server.domain.com");
//            dbm.setDBName("default");
//            dbm.setDBPort("10000");
//
//            Database db = new Database(null, dbm);
//
//            db.connect();
//
//            ResultSet rs = db.openQuery("select * from account");
//
//            if(rs != null) {
//                System.out.println("");
//                System.out.println("");
//
//                int colWidth = 20;
//
//                int colCount = rs.getMetaData().getColumnCount();
//
//                for(int i = 1; i < colCount; i++) {
//                    System.out.print(HiveTest.fill(rs.getMetaData().getColumnName(i), colWidth));
//                }
//                System.out.println("");
//
//                while(rs.next()) {
//                    // Do something with your data
//                }
//
//                db.closeQuery(rs);
//            }
//            db.disconnect();
//
//        } catch(Throwable e) {
//            e.printStackTrace();
//        }
//    }

}
