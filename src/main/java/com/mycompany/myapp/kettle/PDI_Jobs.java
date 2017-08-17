package com.mycompany.myapp.kettle;

/**
 * Created by catalin on 17/08/17.
 */

public class PDI_Jobs {

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
