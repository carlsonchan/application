package com.lifeband.lifeband.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lifeband.lifeband.NfcReader;
import com.lifeband.lifeband.PatientData;
import com.lifeband.lifeband.PatientRepository;
import com.lifeband.lifeband.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    private ProgressDialog mProgress;
    private List<DataModel> mDataList;

    private ImageLoader mImageLoader;

    private NfcAdapter nfcAdapter;
    private NfcReader nfcReader;

    private PatientData patientData;
    private PatientRepository patientRepository;
    private final String TAG_REQUEST = "MY_TAG";

    private class DataModel {

        private String mTitle;


    }

    public MainActivity(){
        super();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize NFC Adapter
        Toolbar myToolbar = (Toolbar) findViewById(R.id.apptoolbar);
        myToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(myToolbar);

        // FAB
        final Context con = getApplicationContext();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundColor(getResources().getColor(R.color.colorComplement));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(con, NfcActivity.class);
                startActivity(i);

            }
        });




        patientData = null;


//        ImageButton btnInformation= (ImageButton) findViewById(R.id.btnInformation);



        Button btnInformation = (Button) findViewById(R.id.btnInformation);
        Button btnEmergencyContact= (Button) findViewById(R.id.btnEmergencyContact);
        Button btnHistory= (Button) findViewById(R.id.btnHistory);
        Button btnMedication= (Button) findViewById(R.id.btnMedication);

        showToast("Sent");

        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), InformationActivity.class);
                startActivity(i);
                //makeSampleHttpRequest();
            }
        });

        btnEmergencyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), EmergencyContactActivity.class);
                startActivity(i);

 /*               Intent i = new Intent();
                Bundle b = new Bundle();
                b.putParcelable(Constants.CUSTOM_LISTING, currentListing);
                i.putExtras(b);
                i.setClass(this, SearchDetailsActivity.class);
                startActivity(i);*/
                //makeSampleHttpRequest();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), HistoryActivity.class);
                startActivity(i);
        /*        CustomListing currentListing = new CustomListing();
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putParcelable(Constants.CUSTOM_LISTING, currentListing);
                i.putExtras(b);
                i.setClass(this, SearchDetailsActivity.class);
                startActivity(i);*/
                //makeSampleHttpRequest();
            }
        });

        btnMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), MedicationActivity.class);
                startActivity(i);
     /*           Intent i = new Intent();
                Bundle b = new Bundle();
                b.putParcelable("", data);
                i.putExtras(b);
                i.setClass(this, SearchDetailsActivity.class);
                startActivity(i);*/
                //makeSampleHttpRequest();
            }
        });
      /*  while(patientData == null){

        }

        btnInformation.setText(patientData.getId());*/


                //Memory cache is always faster than DiskCache. Check it our for yourself.
        //mImageLoader = new ImageLoader(mVolleyQueue, new BitmapCache(max_cache_size));

        mDataList = new ArrayList<DataModel>();

    }



    public void onDestroy() {
        super.onDestroy();
    }

    public void onStop() {
        super.onStop();
        if(mProgress != null)
            mProgress.dismiss();
        // Keep the list of requests dispatched in a List<Request<T>> mRequestList;
		/*
		 for( Request<T> req : mRequestList) {
		 	req.cancel();
		 }
		 */
        //jsonObjRequest.cancel();
        //( or )
        //mVolleyQueue.cancelAll(TAG_REQUEST);
    }

    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }



}
