package my.magic.directoryapplicationpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sflim on 9/1/2016.
 */
public class EmployeeDatabase extends SQLiteOpenHelper {


    public static class Employee{
        public String ID ;
        public String name;
        public String position;
        public String department;
        public String officePhone;
        public String address;
        public String email;
        public int age;
        public int gender;
        public int KPI;
        public String supervisorID;

        public String supervisorName;

    }


    public Employee[] searchbyName(String name) {

        //Access the database
        SQLiteDatabase db = getReadableDatabase();

        String sql= "SELECT ID FROM employee where name LIKE ?";

        //seleect rows
        Cursor cursor = db.rawQuery(sql, new String[] {"%" + name + "%"});


        Employee[] employees = new Employee[cursor.getCount()];

        //convert to array
        for (int c=0;c< employees.length;c++){

            //GET ID of the employee at this row
            cursor.moveToPosition(c);
            String id = cursor.getString(cursor.getColumnIndex("ID"));

            //get full employee details
            employees[c]= getEmployee(id);


        }

        //Finish
        cursor.close();
        db.close();



        return employees;
    }


    //method signature
    public Employee getEmployee(String id){

        Employee emp= new Employee();

        //access the database

        SQLiteDatabase db = getReadableDatabase();

        //Prepare SQL query

        String sql ="SELECT emp.name,emp.position, emp.department, emp.officePhone,emp.address,emp.email," +
                "emp.age, emp.gender, emp.KPI, emp.supervisorID, mgr.name AS 'supervisorName' "+
                "from employee emp LEFT OUTER JOIN employee mgr ON mgr.ID = emp.supervisorID where emp.ID = ?";


        //go to row
        // can change the list to more parameter or filter later
        Cursor cursor=db.rawQuery(sql,new String[] {id});
        cursor.moveToPosition(0);


        //Convert to Java Value from database
        Employee employee = new Employee();
        employee.name = cursor.getString(cursor.getColumnIndex("name"));
        employee.position=cursor.getString(cursor.getColumnIndex("position"));
        employee.department=cursor.getString(cursor.getColumnIndex("department"));
        employee.officePhone=cursor.getString(cursor.getColumnIndex("officePhone"));
        employee.address=cursor.getString(cursor.getColumnIndex("address"));
        employee.email=cursor.getString(cursor.getColumnIndex("email"));
        employee.age=cursor.getInt(cursor.getColumnIndex("age"));
        employee.gender=cursor.getInt(cursor.getColumnIndex("gender"));
        employee.KPI=cursor.getInt(cursor.getColumnIndex("KPI"));
        employee.supervisorID=cursor.getString(cursor.getColumnIndex("supervisorID"));
        employee.supervisorName=cursor.getString(cursor.getColumnIndex("supervisorName"));
        employee.ID = id;

        //finish
        cursor.close(); //ASIC
        db.close();
        // () round bracket - forInput-or parameters
        // {} curly bracket for-code
        // [] square bracket for arrays
        return employee;

    }


    public void addEmployee(SQLiteDatabase db, Employee employee){

        //Caused by: java.lang.IllegalStateException: getDatabase called recursively
        //SQLiteDatabase db = getWritableDatabase();
        //db.close();

        ContentValues values= new ContentValues();

        values .put("ID",employee.ID);
        values .put("name",employee.name);
        values .put("position",employee.position);
        values .put("department",employee.department);
        values .put("officePhone",employee.officePhone);
        values .put("address",employee.address);
        values .put("email",employee.email);
        values .put("age",employee.age);
        values .put("gender",employee.gender);
        values .put("KPI",employee.KPI);
        values.put("supervisorID", employee.supervisorID);

        //upload
        db.insert("employee","name",values);

        //commit
        // need to resuse
        //db.close();



//        employee.ID="";
//        employee.name="";
//        employee.position="";
//        employee.department="";
//        employee.officePhone="";
//        employee.address="";
//        employee.email="";
//        employee.age=1;
//        employee.gender=2;
//        employee.KPI=1;
//        employee.supervisorID="";






    }



    //constructor - special method
    public EmployeeDatabase(Context context) {
        //super(context, name, factory, version);
        //step 1
        super(context, "EmployeeDatabase", null, 3);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    //a call back method
    // something android need to know when db first created.
    //create tables
    //step 2

        String sql ="CREATE TABLE IF NOT EXISTS employee ("+
                "ID TEXT(12) PRIMARY KEY," +
                "name TEXT," +
                "position TEXT," +
                "department TEXT," +
                "officePhone TEXT," +
                "address TEXT," +
                "email TEXT,"+
                "age INT," +
                "gender INT," +
                "KPI INT," +
                "supervisorID TEXT(12))";

        //execute SQL
        db.execSQL(sql);

        //TODO add default entries
        Employee employee = new Employee();
        employee.ID="999999999901";
        employee.name="Boss no 1";
        employee.position="upper";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID=null; //the boss

        addEmployee(db,employee);

        employee.ID="11034563634";
        employee.name="employee 1";
        employee.position="executive";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=25;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss
        addEmployee(db,employee);

        employee.ID="12034563634";
        employee.name="my name 3";
        employee.position="upper";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss

        addEmployee(db,employee);


        employee.ID="13034563634";
        employee.name="my name 4";
        employee.position="upper";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss

        addEmployee(db,employee);

        employee.ID="14034563634";
        employee.name="my name 555";
        employee.position="Pos5";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss

        addEmployee(db,employee);


        employee.ID="15034563634";
        employee.name="my name 6";
        employee.position="upper";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss

        addEmployee(db,employee);


        employee.ID="16034563634";
        employee.name="my name 7";
        employee.position="upper";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss

        addEmployee(db,employee);


        employee.ID="17034563634";
        employee.name="my name 8";
        employee.position="upper";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss

        addEmployee(db,employee);


        employee.ID="18034563634";
        employee.name="my name 9";
        employee.position="upper";
        employee.department="IT";
        employee.officePhone="03-232323232";
        employee.address="xx, jalan xx, kl";
        employee.email="haha@sdfs.com";
        employee.age=50;
        employee.gender=1;
        employee.KPI=1;
        employee.supervisorID="999999999901"; //the boss

        addEmployee(db,employee);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //when app is updated from previous version
        //be lazy and delete all entries
        db.execSQL("DROP TABLE IF EXISTS employee");

        // populate with default values again
        onCreate(db);


    }



}
