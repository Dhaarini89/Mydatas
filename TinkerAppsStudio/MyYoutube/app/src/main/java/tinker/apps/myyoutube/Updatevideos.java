package tinker.apps.myyoutube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Updatevideos extends AppCompatActivity {
   private static int value;
   private long id;
   DB linkdb;
    Button button;
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter recadapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText editText;
    TextView textView;
    private static int iterate;
    private static ArrayList<String> arraystring=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatevideos);
        linkdb=new DB(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        button = findViewById(R.id.Submit);
        editText=findViewById(R.id.edittextlink);
        textView=findViewById(R.id.textViewurl);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.count, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                iterate=1;
                value = Integer.parseInt(adapterView.getItemAtPosition(pos).toString());
                if(iterate==value){ button.setText("SUBMIT");} else{button.setText("NEXT VIDEO");} ;
                arraystring.clear();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = editText.getText().toString();
                        String keyword = url.substring(url.lastIndexOf("/") + 1);
                        arraystring.add(keyword);
                        if(button.getText().toString()=="SUBMIT") {
                               Integer total=linkdb.totalcount();
                            for (int i=0;i<arraystring.size();i++) {
                                id = linkdb.insertData(arraystring.get(i),i+total);
                           }
                              finish();
                        }
                        iterate=iterate+1;
                        textView.setText("Provide URL for Video "+iterate);
                        editText.setText(" ");
                        if(iterate==value){ button.setText("SUBMIT");} else{button.setText("NEXT VIDEO");} ;
                    }
                });
                }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        Button buttoncancel=findViewById(R.id.Cancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   finish();
            }
        });




    }


}