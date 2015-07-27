package com.example.sharat.smarthood;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////////Hiding the action bar/////////////////////////////////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //////////////////////////////////End of Hiding the action bar/////////////////////////////////////////////

        WebView wv = (WebView)findViewById(R.id.webView);   //Innitilizing Webview
        WebSettings webSettings = wv.getSettings();         //Enabling Javascripts
        wv.getSettings().setJavaScriptEnabled(true);        //Enabling Javascripts

        final TextView tv = (TextView)findViewById(R.id.textView2); //Initializing Textview for progress
       final ImageView splash = (ImageView)findViewById(R.id.imageView);
        final ImageView splashicon = (ImageView)findViewById(R.id.imageView2);
        final ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
        splashicon.setImageResource(R.mipmap.sh);

        wv.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int newProgress)
            {
                tv.setText("%" + newProgress);
                if (newProgress == 100) {
                    splash.setVisibility(View.GONE);
                    splashicon.setVisibility(View.GONE);
                    tv.setVisibility(View.GONE);
                    pb.setVisibility(View.GONE);
                }
            }
        });

        wv.loadUrl("http://www.google.com");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /////////////////////////For confirmation on exit/////////////////////////
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    ///////////////////////////////////////End of confirmation////////////////////////////
}
