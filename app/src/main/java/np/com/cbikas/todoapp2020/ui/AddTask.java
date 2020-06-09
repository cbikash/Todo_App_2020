package np.com.cbikas.todoapp2020.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import np.com.cbikas.todoapp2020.R;

public class AddTask extends AppCompatActivity {
   Toolbar toolbar;
   private EditText datepiker;
   DatePickerDialog datePickerDialog;
  private TextInputLayout title,description;
  private RadioGroup radioGroup;
  private RadioButton high,medium,low;
  private Button submit;
  private Date date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        toolbar=findViewById(R.id.toolbaradd);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Todo List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         title=findViewById(R.id.etTitle);
         description=findViewById(R.id.etDescription);
         radioGroup=findViewById(R.id.radiogroup);
         high=findViewById(R.id.high);
         medium=findViewById(R.id.medium);
         low=findViewById(R.id.low);
         submit=findViewById(R.id.addtodo);


    }



}
