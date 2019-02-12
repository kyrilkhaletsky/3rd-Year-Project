package project.ca326.dcu.shoppingpal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        //log out user and shut down all previous activities
        if(view == buttonLogout){
            resetSelected();
            firebaseAuth.signOut();
            finishAffinity();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        //delete the user account
        if(view == buttonDelete){
            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
            alert.setMessage("Are you sure you want to Delete your account?")
                    .setCancelable(false)
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            resetSelected();
                            firebaseAuth.signOut();
                            user.delete();
                            finishAffinity();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = alert.create();
            dialog.setTitle("Alert");
            alert.show();
        }
    }

    //Reset selected profile if the user signs out or logs out of account
    public void resetSelected(){
        SharedPreferences sharedPref = getSharedPreferences("Preferences", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("userProfile", 0);
        prefEditor.apply();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
