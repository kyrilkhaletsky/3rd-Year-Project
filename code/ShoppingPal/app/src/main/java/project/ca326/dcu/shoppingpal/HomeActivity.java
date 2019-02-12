package project.ca326.dcu.shoppingpal;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.Manifest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ZXingScannerView scannerView;
    private final int permission_code = 1;

    String [] selectedProfile;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Map<String,Object> map;

    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Authenticate user
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        userID = user.getUid();

        startSpinner();
    }

    //launches CreateProfile Activity
    public void createProfile(View view){
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        startActivity(intent);
    }

    //launches Dialog with selected items
    public void showItemsSelected(View view){
        if (selectedProfile == null){
            Toast.makeText(this, "Please select a Profile", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View items = getLayoutInflater().inflate(R.layout.show_items,null);
        ListView showitems = (ListView)items.findViewById(R.id.lvItemsSelected);
        //show items in selected profile
        adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, selectedProfile);
        showitems.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        alertDialog.setView(items);
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
        //dismiss dialog on button click
        Button buttonOK = (Button) dialog.findViewById(R.id.buttonOk);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //Creates the Settings toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    //launch settings activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id == R.id.action_settings){
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    //creates spinner object
    public void startSpinner(){
        spinner = (Spinner) findViewById(R.id.spinnerProfiles);
        //listens to new profiles created and updates spinner if new profiles have been added
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get inbuilt profiles and add them to "array"
                ArrayList<CharSequence> array = new ArrayList<>(Arrays.asList(HomeActivity.this.getResources().getTextArray(R.array.restrictions)));
                //try if use has created profiles previously
                //if profiles exist add them to the end of the array
                try {
                    map = (Map<String, Object>) dataSnapshot.child("users").child(userID).getValue();
                    //add to position 16 and onwards
                    int x = 16;
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        // key contains Profile Name
                        String key = entry.getKey();
                        array.add(x, key);
                        x++;
                    }
                }
                catch (java.lang.NullPointerException e){
                    //user has no profiles set up spinner as normal without user profiles
                    Log.i("Error", "No Personal Profiles Available");
                }
                //set up spinner and listen for item selected
                adapter = new ArrayAdapter<>(HomeActivity.this, android.R.layout.simple_spinner_item, array);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                restoreSpinner();
                spinner.setOnItemSelectedListener(HomeActivity.this);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Auto Generated Method
            }
        });
    }

    //restores spinner object
    public void restoreSpinner(){
        SharedPreferences sharedPref = getSharedPreferences("Preferences",MODE_PRIVATE);
        int profileVal = sharedPref.getInt("userProfile",-1);
        if(profileVal != -1) {
            spinner.setSelection(profileVal);
        }
    }

    //opens camera when button is pressed
    public void scanBarcode(View view) {
        //check if user given app camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, permission_code);
            return;
        }
        //opens camera
        if (selectedProfile == null){
            Toast.makeText(this, "Please Select a Profile", Toast.LENGTH_LONG).show();
        }
        else {
            scannerView = new ZXingScannerView(this);
            scannerView.setResultHandler(new ZXingScannerResultHandler());
            setContentView(scannerView);
            scannerView.startCamera();
        }
    }

    //selects an item from the spinner and passes it to InformationActivity
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Saves spinner value and saves it to sharedPrefs locally
        int profileVal = spinner.getSelectedItemPosition();
        SharedPreferences sharedPref = getSharedPreferences("Preferences", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("userProfile", profileVal);
        prefEditor.apply();
        //select corresponding items and and them to selectedProfile array
        switch (profileVal) {
            case 0:
                selectedProfile = null;
                break;
            case 1:
                selectedProfile = getResources().getStringArray(R.array.Wheat);
                break;
            case 2:
                selectedProfile = getResources().getStringArray(R.array.Crustaceans);
                break;
            case 3:
                selectedProfile = getResources().getStringArray(R.array.Eggs);
                break;
            case 4:
                selectedProfile = getResources().getStringArray(R.array.Fish);
                break;
            case 5:
                selectedProfile = getResources().getStringArray(R.array.Peanuts);
                break;
            case 6:
                selectedProfile = getResources().getStringArray(R.array.Soya);
                break;
            case 7:
                selectedProfile = getResources().getStringArray(R.array.Milk);
                break;
            case 8:
                selectedProfile = getResources().getStringArray(R.array.Celery);
                break;
            case 9:
                selectedProfile = getResources().getStringArray(R.array.Mustard);
                break;
            case 10:
                selectedProfile = getResources().getStringArray(R.array.Sesame);
                break;
            case 11:
                selectedProfile = getResources().getStringArray(R.array.Lupin);
                break;
            case 12:
                selectedProfile = getResources().getStringArray(R.array.Nuts);
                break;
            case 13:
                selectedProfile = getResources().getStringArray(R.array.Sulphites);
                break;
            case 14:
                selectedProfile = getResources().getStringArray(R.array.Molluscs);
                break;
            case 15:
                selectedProfile = getResources().getStringArray(R.array.Wada);
                break;
            default:
                //if a personal profile has been selected
                String text = spinner.getSelectedItem().toString();
                map.get(text);
                ArrayList<String> array  = new ArrayList<>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    //Key contains Profile name
                    String key = entry.getKey();
                    //if key equals to selected profile add corresponding items into an array
                    if (key.equals(text)) {
                        // value is the corresponding list
                        Object value = entry.getValue();
                        array.addAll((ArrayList) value);
                    }
                }
                //set the selected profile to the array built earlier
                selectedProfile = array.toArray(new String[0]);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Auto Generated Method
    }

    //stops camera and outputs barcode result to a Toast
    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler {
        @Override
        public void handleResult(Result result) {
            //check if its a valid barcode number
            String resultBarcode = result.getText();
            if(resultBarcode.length() != 13 || !resultBarcode.matches("[0-9]+")){
                Toast.makeText(getApplicationContext(), "Invalid product number: " + resultBarcode, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                scannerView.stopCamera();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
            intent.putExtra("BarcodeString", resultBarcode.toString());
            intent.putExtra("ProfileArray", selectedProfile);
            startActivity(intent);
            scannerView.stopCamera();
        }
    }

    //will only run once when a user needs to accept camera permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permission_code) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    //goes back to homepage when back button is pressed
    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_home);
        startSpinner();
        restoreSpinner();
    }

    //stops the camera on pause and returns home
    @Override
    public void onPause(){
        super.onPause();
        setContentView(R.layout.activity_home);
        startSpinner();
        restoreSpinner();
    }

    @Override
    public void onResume() {
        super.onResume();
        restoreSpinner();
    }
}