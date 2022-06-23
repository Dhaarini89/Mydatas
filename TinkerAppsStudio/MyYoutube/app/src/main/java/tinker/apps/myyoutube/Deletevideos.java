package tinker.apps.myyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Deletevideos extends AppCompatActivity {

    DB deleteDB;
    Button buttonsubmit,buttoncancel,buttonnext;
    EditText textViewrow,textViewcolumn;
   private static int id;
    ArrayList<Integer> dbid = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletevideos);
        deleteDB=new DB(this);
        textViewrow=findViewById(R.id.edittextrow);
        textViewcolumn=findViewById(R.id.edittextcolumn);

        buttoncancel=findViewById(R.id.Cancel);
        buttonnext=findViewById(R.id.NextVideo);
        buttonsubmit=findViewById(R.id.Submit);

        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String row=textViewrow.getText().toString().trim();
                String column=textViewcolumn.getText().toString().trim();
                id= ((Integer.parseInt(row)-1)*4)+(Integer.parseInt(column)-1);
                dbid.add(id);
                textViewrow.setText("");
                textViewcolumn.setText(" ");

            }
        });
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer temp;
                String row=textViewrow.getText().toString().trim();
                String column=textViewcolumn.getText().toString().trim();
                id= ((Integer.parseInt(row)-1)*4)+(Integer.parseInt(column)-1);
                for(int a=0;a<dbid.size();a++)
                {
                    for(int b=a+1;b<dbid.size();b++)
                    {
                        if(dbid.get(a)>dbid.get(b))
                        {
                            temp=dbid.get(a);
                            //dbid.get(a)=dbid.get(b);
                            //dbid.get(b)=temp;
                        }
                    }
                }
                int abc=deleteDB.deleteProduct(id);
                for(int i=id+1;i<(deleteDB.totalcount()+1);i++) {
                    int updatecheck = deleteDB.updateno(i);
                }
                finish();
            }
        });
    }
}