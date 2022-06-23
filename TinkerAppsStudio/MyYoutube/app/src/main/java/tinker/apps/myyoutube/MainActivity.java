package tinker.apps.myyoutube;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DB linksDB;
    private static Handler handler;
    private long id;
    private GridView gridView;
    ArrayList youtubelist=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private Myadapter myadapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linksDB=new DB(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Integer lastVersion = prefs.getInt("version_code", 1);
        if (lastVersion == 1) {
            /* Insert the available list of products into each of the category table */
            startActivity(new Intent(MainActivity.this, List.class));
        }
        prefs.edit().putInt("version_code", 0).commit();
        ArrayList<Exampleclass> exampleclass = new ArrayList<>();
       String data_link =linksDB.getlink();
       String data_url[]=data_link.split("\n");
       for(int i=0;i<data_url.length;i++)
       {
           youtubelist.add(data_url[i]);

       }

        gridView= findViewById(R.id.gridView);
         myadapter=new Myadapter(this,youtubelist);
        gridView.setAdapter(myadapter);
        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        myadapter.notifyDataSetChanged();



    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

   @Override
    public void onResume()
    {
        super.onResume();
          }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_add:
                ArrayList<Exampleclass> exampleclassupdated = new ArrayList<>();
        /*Customize the List of Products in each Category by adding anything missed or required */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("URL for new video");

        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(10,1000,10,0);
        lp.setMarginStart(100);
        input.setBackgroundResource(R.drawable.edittext_border);
                input.setLayoutParams(lp);
                input.setTextSize(15);
                builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int a) {
               linksDB=new DB(getApplicationContext());
                String url = input.getText().toString().trim();
                String keyword = url.substring(url.lastIndexOf("/") + 1);
                Integer total=linksDB.totalcount();
                if (!keyword.isEmpty()) {
                    id = linksDB.insertData(keyword,total+1);
                    String data_link =linksDB.getlink();
                    String data_url[]=data_link.split("\n");
                    youtubelist.clear();
                    for (int i=0;i<data_url.length;i++) {
                        youtubelist.add(data_url[i]);
                    }

                    gridView= findViewById(R.id.gridView);
                    Myadapter myadapter=new Myadapter(MainActivity.this,youtubelist);
                    gridView.setAdapter(myadapter);
                    gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);


                } else {
                    Toast.makeText(getApplicationContext(), "Enter a Valid String",Toast.LENGTH_SHORT).show();
                }
            }

        });
        builder.show();
        return true;
    }

        return super.onOptionsItemSelected(item);
    }


}