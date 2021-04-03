package edu.cs.uga.countryquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CountryQuizDBHelper extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "CountryQuizDBHelper";

    private static final String DB_NAME = "countryquiz.db";
    private static final int    DB_VERSION = 1;
    private static CountryQuizDBHelper helperInstance;

    //Define all names (strings) for table and column names.
    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRIES_COLUMN_ID = "_id";
    public static final String COUNTRIES_COLUMN_COUNTRY = "country";
    public static final String COUNTRIES_COLUMN_CONTINENT = "continent";

    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZZES_COLUMN_ID = "_id";
    public static final String QUIZZES_COLUMN_DATE = "date";
    public static final String QUIZZES_COLUMN_QUESTION1 = "question1";
    public static final String QUIZZES_COLUMN_QUESTION2 = "question2";
    public static final String QUIZZES_COLUMN_QUESTION3 = "question3";
    public static final String QUIZZES_COLUMN_QUESTION4 = "question4";
    public static final String QUIZZES_COLUMN_QUESTION5 = "question5";
    public static final String QUIZZES_COLUMN_QUESTION6 = "question6";
    public static final String QUIZZES_COLUMN_RESULT = "result";

    //A Create table SQL statement to create a table for countries
    //_id is an auto increment primary key, i.e. the database will automatically
    //generate unique id values as keys
    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
                + COUNTRIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COUNTRIES_COLUMN_COUNTRY + " TEXT, "
                + COUNTRIES_COLUMN_CONTINENT + " TEXT "
            + ")";

    //A Create table SQL statement to create a table for quizzes
    //_id is an auto increment primary key, i.e. the database will automatically
    //generate unique id values as keys
    private static final String CREATE_QUIZZES =
            "create table " + TABLE_QUIZZES + " ("
                    + QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZZES_COLUMN_DATE + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION1 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION2 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION3 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION4 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION5 + " TEXT, "
                    + QUIZZES_COLUMN_QUESTION6 + " TEXT, "
                    + QUIZZES_COLUMN_RESULT + " INTEGER"
                    + ")";

    //since this constructor is private, it can only called from this class in the getInstance method
    private CountryQuizDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Access method to single instance of the class.
    //It is synchronized, so that only one thread executes this method.
    public static synchronized CountryQuizDBHelper getInstance(Context context) {
        //check if the instance already exists and if not, create the instance
        if(helperInstance == null) {
            helperInstance = new CountryQuizDBHelper(context.getApplicationContext());
        }
        return helperInstance;
    }

    //We must override onCreate method, which will be used to create the database if
    //it does not exist yet.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COUNTRIES);
        Log.d(DEBUG_TAG, "Table " + TABLE_COUNTRIES + " created");
        db.execSQL(CREATE_QUIZZES);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " created");
    }

    //We should override onUpgrade method, which will be used to upgrade the database if
    //its version (DB_VERSION) has changed. This will be done automatically by Android
    //if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        Log.d(DEBUG_TAG, "Table " + TABLE_COUNTRIES + " upgraded");
        db.execSQL("drop table if exists " + TABLE_QUIZZES);
        onCreate(db);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " upgraded");
    }
}
