package team.Voyager.openinwhatsapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

    EditText et1;
    EditText et2;
    Button bt;
    TextView quitApp;
    TextView minimize;
    TextView fullScreen;
    TextView contactus;
    LinearLayout rootLayout;
    FrameLayout.LayoutParams params;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 =findViewById(R.id.editText);
        et2 = findViewById(R.id.code);
        bt=findViewById(R.id.button);
        quitApp=findViewById(R.id.quit_app);
        minimize=findViewById(R.id.minimize);
        fullScreen=findViewById(R.id.full_screen);
        contactus=findViewById(R.id.contact);
        rootLayout=findViewById(R.id.root_layout);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        if (sharedPref.getBoolean("fscreen",false)==true){
            params=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
            rootLayout.setLayoutParams(params);
//            editor.putBoolean("fscreen",true);
//                    rootLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        }
        else{
            params=new FrameLayout.LayoutParams((int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 140, getResources().getDisplayMetrics())),(int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 270, getResources().getDisplayMetrics())),17);
            rootLayout.setLayoutParams(params);

        }
//        params = (FrameLayout.LayoutParams) rootLayout.getLayoutParams();
//        params.setMargins(100, 100, 100, 100);
//        rootLayout.setLayoutParams(params);


        Log.d("MyTag", "onCreate: "+et2.getText().length()+" "+et2.getText().toString()+" "+sharedPref.getBoolean("first",false));

        if (et2.getText().length()==0 && sharedPref.getBoolean("first",false)==true){

            et2.setText(sharedPref.getString("code","91"));
        }

//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("code", "+91");
//        editor.commit();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                String phone=et2.getText().toString()+ et1.getText().toString();
                if (phone.charAt(0)=='+'){
                    phone=phone.substring(1);
                }
                Intent sendIntent = new Intent("android.intent.action.MAIN");

                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(phone) + "@s.whatsapp.net");
//                if (sharedPref.getBoolean("fir"))
                editor.putBoolean("first",true);
                if (et2.getText().length()!=0){

                    editor.putString("code",et2.getText().toString());
                }
                editor.commit();
                Log.d("MyTag", " "+sharedPref.getBoolean("first",false));


                try {
                    startActivity(sendIntent);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Couldn't find WhatsApp", Toast.LENGTH_LONG).show();
                }
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
                

                if (sharedPref.getBoolean("fscreen",false)==false){
                    params=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
                    rootLayout.setLayoutParams(params);
                    editor.putBoolean("fscreen",true);
//                    rootLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                }
                else{
//                    float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 140, getResources().getDisplayMetrics());
                    params=new FrameLayout.LayoutParams((int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 140, getResources().getDisplayMetrics())),(int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 270, getResources().getDisplayMetrics())),17);
//                    params=new FrameLayout.LayoutParams((int) (350 * Resources.getSystem().getDisplayMetrics().density),(int) (700 * Resources.getSystem().getDisplayMetrics().density),17);
                    rootLayout.setLayoutParams(params);
//                    rootLayout.setGravity(Gravity.CENTER);
                    editor.putBoolean("fscreen",false);

                }
                editor.commit();

            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri1 = Uri.parse("https://www.facebook.com/teamvoyagerfb"); // missing 'http://' will cause crashed
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
            }
        });

    }
}