package my.magic.directoryapplicationpractical;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.math.RoundingMode;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //last step
    EmployeeDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //last step
        //Load database
        db = new EmployeeDatabase(this);


        //this step to filter the result set
        // response to search features
        Button searchButton = (Button) findViewById(R.id.am_search_button);
        //call back function
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: respond to button click
                    //search((String) findViewById(R.id.am_search_input));
                    //refresh();
                    reset();

            }
        });


        //response to search input

        EditText searchInputView = (EditText) findViewById(R.id.am_search_input);
        searchInputView.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //dont care
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //dont care
            }

            @Override
            public void afterTextChanged(Editable s) {
                refresh();
            }


        });


        //show first/initially
        refresh();



        //fill up ListView
        //need to modify the list view, changing the layout
        // give ID to activy in content main - am_listview
//        ListView listview = (ListView) findViewById(R.id.am_listview);
//        //get all Emplyee
//        EmployeeDatabase.Employee employee[] = db.searchbyName("");
//        EmployeeListAdapter adapter = new EmployeeListAdapter(this, employee);
//        //show list
//        listview.setAdapter(adapter);








//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        }


      //  );


    }

// Un-Used comment for now
// @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    public void reset(){
        EditText searchInputView = (EditText) findViewById(R.id.am_search_input);
        searchInputView.getEditableText().clear();
        refresh();

    }


    public void refresh(){
        EditText searchInputView = (EditText) findViewById(R.id.am_search_input);
        String name = searchInputView.getEditableText().toString();
        //search for it
        search(name);
    }

    public void search(String name){


        ListView listview = (ListView) findViewById(R.id.am_listview);
        //get all Emplyee
        EmployeeDatabase.Employee employee[] = db.searchbyName(name);
        EmployeeListAdapter adapter = new EmployeeListAdapter(this, employee);
        //show list
        listview.setAdapter(adapter);


    }


}
