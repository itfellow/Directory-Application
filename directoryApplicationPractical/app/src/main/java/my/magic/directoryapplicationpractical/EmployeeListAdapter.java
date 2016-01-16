package my.magic.directoryapplicationpractical;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sflim on 9/1/2016.
 */
public class EmployeeListAdapter extends BaseAdapter{

    //step1
    final Activity activity;
    final EmployeeDatabase.Employee[] employees;

    //step 2
    public EmployeeListAdapter(Activity activity, EmployeeDatabase.Employee[] employees) {
        this.activity = activity;
        this.employees = employees;
    }


    @Override
    public int getCount() {


        //step3
        // /return 0;
        return employees.length;
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return employees[position];

    }

    @Override
    public long getItemId(int position) {

        return position;
        //return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //inflate row layour
        View layout = activity.getLayoutInflater().inflate(R.layout.am_employee_item,parent,false);


        //get employee position
        final EmployeeDatabase.Employee employee = employees[position];

        //Respond to Clicks on the List View ITEM , week 3, to show the detail of employee
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Show employee detail activity/windows

                //Log.e("EmployeeListAdapter","Showing details for employee" + employee.ID);
                Log.d("EmployeeListAdapter", "Showing details for employee" + employee.ID);

                //TODO now to start the activity
                //Start employee details activity

                Intent intent = new Intent(activity, EmployeeDetailsActivity.class);

                //receiving from EmployeeDetailsActivity class
                intent.putExtra("EMPLOYEE_ID",employee.ID);   //tell which employee
                Log.e("EmployeeListAdapter", "Put details for employee " + employee.ID);

                intent.putExtra("EMPLOYEE_name", employee.name);
                Log.e("EmployeeListAdapter", "Put details for employee " + employee.name);

                activity.startActivity(intent);

                //TODO to show the record across or pass to an intent
                // all must have ids







            }
        });




        //TODO Fill u p layout with employee details
        TextView nameView = (TextView) layout.findViewById(R.id.amei_name);
        TextView positionView = (TextView) layout.findViewById(R.id.amei_position);


        //let update its
        nameView.setText(employee.name);
        positionView.setText(employee.position + "," + employee.department);

        //last step

        //return null;
        //java.lang.NullPointerException: Attempt to invoke virtual method 'int android.view.View.getImportantForAccessibility()' on a null object reference

        return layout;
    }
}
