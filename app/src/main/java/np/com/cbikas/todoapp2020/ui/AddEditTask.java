package np.com.cbikas.todoapp2020.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import np.com.cbikas.todoapp2020.R;

public class AddEditTask extends AppCompatActivity {
    Toolbar toolbar;
    private EditText datepiker;
    DatePickerDialog datePickerDialog;
    private EditText edittitle, editdescription;
    private RadioGroup editradioGroup;
    private RadioButton edithigh, editmedium, editlow;
    private Button submit;
    private Date date;
    public static final String EXTRA_TITLE="np.com.cbikas.todoapp2020.ui.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION="np.com.cbikas.todoapp2020.ui.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="np.com.cbikas.todoapp2020.ui.EXTRA_PRIORITY";
    public static final String EXTRA_DATE="np.com.cbikas.todoapp2020.ui.EXTRA_DATE";
    public static final String EXTRA_ID="np.com.cbikas.todoapp2020.ui.EXTRA_ID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        edittitle = findViewById(R.id.etTitle);
        editdescription = findViewById(R.id.etDescription);
        editradioGroup = findViewById(R.id.radiogroup);
        datepiker=findViewById(R.id.datepiker);
        toolbar=findViewById(R.id.add_toolbar);
        editlow=findViewById(R.id.low);
        edithigh=findViewById(R.id.high);
        editmedium=findViewById(R.id.medium);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
         Intent intent=getIntent();
         edithigh.setChecked(true);
         if(intent.hasExtra(EXTRA_ID)){
             getSupportActionBar().setTitle("Edit Task");
             edittitle.setText(intent.getStringExtra(EXTRA_TITLE));
             editdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
             int p = intent.getIntExtra(EXTRA_PRIORITY,1);
             Toast.makeText(this,p+" ",Toast.LENGTH_LONG).show();
             if(p == 1){
                 editlow.setChecked(true);
             }else if(p == 2){
                 editmedium.setChecked(true);
             }else if(p == 3){
                 edithigh.setChecked(true);
             }
           Toast.makeText(this,"id "+intent.getIntExtra(EXTRA_ID,-1),Toast.LENGTH_LONG);


         }else{
             getSupportActionBar().setTitle("Add Task");
         }




    }


    private void saveTask(){

        String title = edittitle.getText().toString();
        String description=editdescription.getText().toString();
        int priority1;
        int checkedId = ((RadioGroup) findViewById(R.id.radiogroup)).getCheckedRadioButtonId();
        switch (checkedId){
            case R.id.high:
                priority1 = 3;
                break;
            case R.id.medium:
                priority1 = 2;
                break;
            case R.id.low:
                priority1= 1;
                break;
            default:
                priority1 = 1;

        }

        if(title.trim().isEmpty()|| description.trim().isEmpty()){
            Toast.makeText(this,"Please insert a title and description",Toast.LENGTH_SHORT).show();;
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority1);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();

    }

    public Date getDate(View view) {
       final Calendar calendar=Calendar.getInstance();
       int YEAR =calendar.get(Calendar.YEAR);
       int MONTH =calendar.get(Calendar.MONTH);
       int DATE=calendar.get(Calendar.DATE);
       final Date date12;
       DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int year, int month, int date) {

               int year1=year;
               int month1 =month;
               int date1=date;

           }
       },YEAR,MONTH,DATE);
       datePickerDialog.show();

  return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_task:
                saveTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
