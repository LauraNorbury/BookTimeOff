package ie.holiday.booktimeoff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //login and register classes
    private static final int Database_Version = 1;
    private static final String Database_Name = "contacts.db";
    private static final String Table_Name = "contacts";
    private static final String Colum_ID = "id";
    private static final String Colum_Name = "name";
    private static final String Colum_Email = "email";
    private static final String Colum_Password = "pass";

    //view class

    private static final String DB_Table = "bookings";
    private static final String ID = "id";
    private static final String Reason = "reason";
    private static final String Date1 = "date1";
    private static final String Date2 = " date2";

    // private static final String Create_Table = "Create Table: " + DB_Table+" (" + ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
    //  +Reason+ " TEXT"+Date1+" Text"+ Date2+ "text" + ")";


    //TimeSheets DB

    private static final String DB_Table_TimeSheets = "timesheets";
    private static final String ID_TSHEETS = "id";
    private static final String Time_In = "timeIn";
    private static final String Time_Out = "timeOut";


    SQLiteDatabase db;

    private static final String Table_Create = "create table contacts (id integer primary key not null, name text not null ,email text not null ,  pass text not null)";

    private static final String Create_Table = "create table bookings (id integer primary key not null, reason text not null, Date1 text not null, Date2 not null)";


    private static final String Create_Table_TimeSheets = "create table timesheets (id integer primary key not null, timeIn text not null, timeOut text not null)";

    public DatabaseHelper(Context context) {

        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Table_Create);

        db.execSQL(Create_Table);
        db.execSQL(Create_Table_TimeSheets);
        this.db = db;

    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();

        //create content values

        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();


        values.put(Colum_ID, count);
        values.put(Colum_Name, c.getName());
        values.put(Colum_Email, c.getEmail());
        values.put(Colum_Password, c.getPass());

        db.insert(Table_Name, null, values);
        db.close();
    }

    public String searchPass(String email) {
        db = this.getReadableDatabase();
        String query = "select email, pass from " + Table_Name;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";


        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);


                if (a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;
    }

    //create method to insert into view class

    public void insertUserBookingData(UserBookingData b) {
        db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        //contentvalues.put(Reason, reason);
        // contentvalues.put(Date1, date1);
        //contentvalues.put(Date2, date2);

        String query = "select * from " + DB_Table;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        contentvalues.put(Reason, b.getReason());
        contentvalues.put(Date1, b.getFromDate());
        contentvalues.put(Date2, b.getToDate());


        //long result = db.insert(DB_Table, null, contentvalues);

        //return result != -1;  //if result = -1 no data inserted

        db.insert(DB_Table, null, contentvalues);
        db.close();
    }

    public void insertTimeSheetsData(Timesheets t) {
        db = this.getWritableDatabase();
        ContentValues TimeSheetsValues = new ContentValues();

        String query = "select * from " + DB_Table_TimeSheets ;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

      //  TimeSheetsValues.put(Time_In, t.getTimeIn());
       // TimeSheetsValues.put(Time_Out, t.getTimeOut());

        db.insert(DB_Table_TimeSheets, null, TimeSheetsValues);
        db.close();
    }

    //}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + Table_Name;
        db.execSQL(query);
        this.onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + DB_Table);

        onCreate(db);

    }


    public Cursor getUserBookingData() {

        db = this.getWritableDatabase();

        String query = "SELECT * FROM " + DB_Table;
        Cursor data = db.rawQuery(query, null);
        //
        return data;
    }

    public Cursor getUserBookingID(String name) {
        db = this.getWritableDatabase();

        String query = "SELECT" + ID + "FROM" + DB_Table + "WHERE" + Date2 +  "='" + name + "'";

        Cursor data = db.rawQuery(query, null);

        return data;

    }


    public void update(String newDate, int id, String oldDate){

        db = this.getWritableDatabase();

        String query = "UPDATE" + DB_Table + "SET" + Date2 + " = '" + newDate + "' WHERE" + ID + "='" +
               id + "'" +  "AND " + Date2 + " =' " + oldDate + "'";
        db.execSQL(query);
    }

    public void delete( int id, String name) {

        db = this.getWritableDatabase();

        String query = "DELETE FROM" + DB_Table + " WHERE " + ID + " = '" + id + "'" + "AND" + Date1 + " = '"
                + name + "'";

        db.execSQL(query);

    }

}



