package tinker.apps.myyoutube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

public class DB {
    
        MyDB myhelper;
        public int flag;
        public String value;
        private Integer redflag;
        public DB(Context context)
        {
            myhelper = new MyDB(context);
        }

        public long insertData(String URLA,Integer Noval)
        {
            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyDB.URLA, URLA.trim());
            contentValues.put(MyDB.Noval, Noval);
            long id = dbb.insert(MyDB.TABLE_NAME, null , contentValues);
            dbb.close();
            return id;
        }

    public String getlink() {
        String sql = "select "+MyDB.URLA+" from " + MyDB.TABLE_NAME  ;
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        StringBuffer buffer = new StringBuffer();
        if(cursor.moveToFirst()){
            do{
                String URLA = cursor.getString(0);
                buffer.append( URLA + " \n");
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return buffer.toString();
    }
        public  int deleteProduct(Integer value) {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String deleteproduct = "Noval=?";
            String[] args = {value.toString()};
            int abc = db.delete(MyDB.TABLE_NAME, deleteproduct, args);
            db.close();
            return 0;
        }

        public int totalcount() {
            String sql = "select  * from " + MyDB.TABLE_NAME;
            SQLiteDatabase db = myhelper.getReadableDatabase();
            Cursor C=db.rawQuery(sql,null);
            int count=C.getCount();
            return  count;

        }

    public int updateno(Integer Noval)
    {   //String data=Product_Name.trim();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.Noval,Noval-1);
        String[] whereArgs= {Noval.toString()};
        int count =db.update(MyDB.TABLE_NAME,contentValues, MyDB.Noval+" = ?",whereArgs );
        db.close();
        return count;
    }

        public  int trunc()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            int count =db.delete(MyDB.TABLE_NAME , null,null);
            return  count;
        }

        static class MyDB extends SQLiteOpenHelper
        {
            private static final String DATABASE_NAME = "MyDB";    // Database Name
            private static final String TABLE_NAME = "Mytable";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String URLA = "URLA";
            private static final String Noval="Noval";//Column II
               private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                    " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+URLA+" VARCHAR(255) ,"+Noval+" INTEGER );";
            private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
            private Context context;

            public MyDB(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_Version);
                this.context=context;
            }

            public void onCreate(SQLiteDatabase db) {

                try {
                    db.execSQL(CREATE_TABLE);
                } catch (Exception e) {
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
                    db.execSQL(DROP_TABLE);
                    onCreate(db);
                }catch (Exception e) {
                }
            }

        }
    }

