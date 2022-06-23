package tinker.apps.myyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class List extends AppCompatActivity {

    DB linksDB;
    long id;
    private String[] link=new String[]{
            "UL6qEoU91yg", "T6qOmTBrO3U", "rpix3t5TLZw", "q7rB4W-AEoE",
            "waoB_3gM2r0", "_KKXY11PC54", "W3jicS767Io", "LYLRon4ppks",
           "hR2br0LA_U4", "L-Aa_Z4zKhc", "9a62h186_j4", "pYCZ6XJ_7RA",
            "uTq0PlXI7Ws", "5bBuNtMsRXY", "6a6x0WQn_ic", "gf3CYyNKA7o",
            "jiUHrhr3mtI", "NAsQou-90Is", "veb8e789xuo", "IEum-DwFkgI",
            "IfaItDqFr-w", "XyLePe-j7gc", "1ZgArSkCaTA", "PF4Qjd1EYUY",
            "hLdTKAluuzk", "WRVsOCh907o", "_1vd6lcK1o0", "RrxgN2Mek3k",
            "65IfBn_uq-w", "rjozgwySOUA", "yb6xwWxnGdg", "24ITDrsDSQI",
            "SjoZIkYnbQ", "SU1PxeIVLDE", "00YSnlPRi5s", "7nl_3GiDN2Q",
            "f3yZnmSldt4", "xpLjXYQEvAE", "x_HhEsGkTGU", "6NheNDDWcGQ" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        linksDB = new DB(this);
        id = linksDB.trunc();
        for (int i = 0; i < link.length; i++) {

            id = linksDB.insertData(link[i],i);
        }
        if (id <= 0) {
            Toast.makeText(getApplicationContext(),"Insertion not successful",Toast.LENGTH_SHORT).show();
        }
        else
        {

        }
        startActivity(new Intent(List.this, MainActivity.class));

    }
}