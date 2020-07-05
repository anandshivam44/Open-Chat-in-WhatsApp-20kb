package team.Voyager.openinwhatsapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    EditText et;
    Button bt;
    TextView quitApp;
    Button minimize;
    Button fullScreen;
    RelativeLayout rootLayout;
    boolean fullSc=false;
    FrameLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editText);
        bt=findViewById(R.id.button);
        quitApp=findViewById(R.id.quit_app);
        minimize=findViewById(R.id.minimize);
        fullScreen=findViewById(R.id.full_screen);
        rootLayout=findViewById(R.id.root_layout);
        params = (FrameLayout.LayoutParams) rootLayout.getLayoutParams();
        params.setMargins(100, 100, 100, 100);
        rootLayout.setLayoutParams(params);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone="91"+et.getText().toString();
                Intent sendIntent = new Intent("android.intent.action.MAIN");

                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(phone) + "@s.whatsapp.net");

                startActivity(sendIntent);
            }
        });
        quitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        minimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.moveTaskToBack(true);
            }
        });
        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                

                if (fullSc==false){
                    params.setMargins(0, 0, 0, 0);
                    rootLayout.setLayoutParams(params);
                    fullSc=true;
                }
                else{
                    params.setMargins(100, 100, 100, 100);
                    rootLayout.setLayoutParams(params);
                    fullSc=false;
                }

            }
        });

    }
}