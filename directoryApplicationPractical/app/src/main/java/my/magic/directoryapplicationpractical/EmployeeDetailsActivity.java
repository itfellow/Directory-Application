package my.magic.directoryapplicationpractical;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class EmployeeDetailsActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        //setup toolbar
        //actionbar previous version
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //pass information between screen
        //Get the ID of the employee that needds to be shown
        String id = getIntent().getStringExtra("EMPLOYEE_ID");

        //get the open database
        EmployeeDatabase db = new EmployeeDatabase(this);
        EmployeeDatabase.Employee employee = db.getEmployee(id);

        //Get the View to update
        TextView nameView = (TextView) findViewById(R.id.ced_name);
        TextView titleView = (TextView) findViewById(R.id.ced_title);
        TextView ageView = (TextView) findViewById(R.id.ced_age);
        TextView genderView = (TextView) findViewById(R.id.ced_gender);
        TextView officePhoneView = (TextView) findViewById(R.id.ced_officephone);
        TextView emailView = (TextView) findViewById(R.id.ced_email);
        TextView addressView = (TextView) findViewById(R.id.ced_address);
        TextView kpiView = (TextView) findViewById(R.id.ced_kpi);
        TextView supervisorView = (TextView) findViewById(R.id.ced_supervisor);
        TextView idView = (TextView) findViewById(R.id.ced_id);


        //TODO Update views
        // String data= getIntent().getStringExtra("keyName");
        nameView.setText(employee.name);
        titleView.setText(employee.position + "," + employee.department);
        ageView.setText("" + employee.age);
        genderView.setText(employee.gender == 1 ? "Male" : "Female");
        officePhoneView.setText(employee.officePhone);
        emailView.setText(employee.email);
        addressView.setText(employee.address);
        kpiView.setText("" + employee.KPI);
        supervisorView.setText(employee.supervisorID);
        idView.setText(employee.ID);

        //replace activity title with name
        String[] names = employee.name.split(" ");
        getSupportActionBar().setTitle(names[0]);


        //remove toolbar
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        //TODO respond to call button click


        // add PhoneStateListener
        //PhoneStateListener phoneListener = new PhoneStateListener();
        //TelephonyManager telephonyManager = (TelephonyManager) this
        //        .getSystemService(Context.TELEPHONY_SERVICE);
        //telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        final Button callButton = (Button) findViewById(R.id.ced_openCall);
        callButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton.setText("Yey");
                //Intent callIntent = new Intent(Intent.ACTION_CALL);
                //callIntent.setData(Uri.parse("tel:0326103883"));
                //startActivity(callIntent);
                //activity.startActivity(intent);

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "EmployeeDetails Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://my.magic.directoryapplicationpractical/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "EmployeeDetails Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://my.magic.directoryapplicationpractical/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();


    }
}
