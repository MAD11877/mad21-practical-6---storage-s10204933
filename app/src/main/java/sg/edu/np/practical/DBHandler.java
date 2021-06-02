package sg.edu.np.practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "usersDB.db";
    public static String USERS = "Users";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_DESC = "Description";
    public static String COLUMN_ID = "ID";
    public static String COLUMN_FOLLOWED = "Followed";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + USERS + "("
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_DESC + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " INTEGER"
                + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESC, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userArrayList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + USERS, null);
        while (cursor.moveToNext()) {
            User user = new User(cursor.getString(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getInt(3));
            userArrayList.add(user);
        }
        return userArrayList;
    }

    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(USERS, values, "ID = " + user.getId(), null);
        db.close();
    }

}
