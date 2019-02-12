package project.ca326.dcu.shoppingpal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonSave;
    Button buttonAdd;
    EditText addName;
    EditText addItems;
    ListView showItems;

    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        addName = (EditText)findViewById(R.id.etProfileName);
        addItems = (EditText)findViewById(R.id.etItems);
        showItems = (ListView)findViewById(R.id.lvItems);
        buttonAdd.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //get the item from input
        String getInput = addItems.getText().toString();
        if (view == buttonAdd) {
            //checks if item has already been added
            if (items.contains(getInput.toLowerCase().trim())) {
                Toast.makeText(getBaseContext(), "Item has already been Added", Toast.LENGTH_SHORT).show();
            }
            //doesn't allow the user to input empty string
            else if (getInput == null || getInput.trim().equals("")) {
                Toast.makeText(getBaseContext(), "Input field cannot be Empty", Toast.LENGTH_SHORT).show();
            }
            //if input ok change string to lowercase and remove any possible whitespace characters
            else {
                items.add(getInput.toLowerCase().replaceAll("\\s+",""));
                //add item to an array and clear the field for the next item
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateActivity.this, android.R.layout.simple_list_item_1, items);
                showItems.setAdapter(adapter);
                ((EditText) findViewById(R.id.etItems)).setText("");
            }
        }
        if (view == buttonSave) {
            //remove possible whitespace
            String key = addName.getText().toString().replaceAll("\\s+","");
            //if array and name are not empty
            if (!key.equals("") && !items.isEmpty()) {
                //profile name(key) and according items in relation to the userID
                myRef.child("users").child(userID).child(key).setValue(items);
                //clear all fields for next Profile addition
                ((EditText) findViewById(R.id.etProfileName)).setText("");
                showItems.setAdapter(null);
                items.clear();
                Toast.makeText(getBaseContext(), "Profile added Successfully", Toast.LENGTH_SHORT).show();

                //reset the sharedPref value to 0 if a new profile was created
                SharedPreferences sharedPref = getSharedPreferences("Preferences", 0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("userProfile", 0);
                prefEditor.apply();
            }
            //if any fields are left empty
            else {
                Toast.makeText(getBaseContext(), "Name or Item fields cannot be Empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
