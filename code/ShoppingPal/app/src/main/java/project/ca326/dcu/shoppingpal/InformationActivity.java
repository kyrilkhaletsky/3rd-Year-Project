package project.ca326.dcu.shoppingpal;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class InformationActivity extends AppCompatActivity {

    String barcodeNum;
    String [] selectedProfile;
    String [] foundItems;
    String [] safeList = {"N/A"};
    String itemName= "N/A";

    String messageSafe = "Product is Safe for Consumption";
    String messageUnSafe = "Product Not Safe for Consumption";
    String noData = "No Data Available for this Product";

    ListView listRestricted;
    ListView listContains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        barcodeNum = getIntent().getExtras().getString("BarcodeString");
        selectedProfile = getIntent().getStringArrayExtra("ProfileArray");
        //set up objects
        TextView nameView = (TextView) findViewById(R.id.tvProductName);
        TextView barcodeView = (TextView) findViewById(R.id.tvbarcode);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.container);
        listRestricted = (ListView) findViewById(R.id.lvItemsRestricted);
        listContains = (ListView) findViewById(R.id.lvItemsFound);

        barcodeView.setText(barcodeNum);

        Log.i("Message", Arrays.toString(selectedProfile));
        Log.i("Message", barcodeNum);

        //implement other datasets here if tescoDataset returns null
        foundItems = tescoDataset();
        if(foundItems == null){
            setTitle(noData);
            nameView.setText(itemName);
            setNotAvailable();
        }
        //if ingredients are found
        else{
            //run comparison if ingredients and selected profile
            String[] matched = compareLists(selectedProfile, foundItems);
            //set the name of the product
            nameView.setText(itemName);
            //if there is no match set product as safe and change color/background accordingly
            if (matched.length == 0) {
                layout.setBackgroundResource(R.drawable.background_safe);
                changeColor("#0fc13c");
                ArrayAdapter<String> safe = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, safeList);
                listRestricted.setAdapter(safe);
                setTitle(messageSafe);
                Log.i("Message", messageSafe);
            }
            //if there is a match set product as unsafe and change color/background accordingly
            else {
                layout.setBackgroundResource(R.drawable.background_unsafe);
                changeColor("#f44242");
                ArrayAdapter<String> found = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matched);
                listRestricted.setAdapter(found);
                setTitle(messageUnSafe);
                Log.i("Message", messageUnSafe);
            }
            //print list that's found
            ArrayAdapter<String> contains = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foundItems);
            listContains.setAdapter(contains);
        }
    }

    //set N/A when no ingredients are found
    public void setNotAvailable(){
        ArrayAdapter<String> safe = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, safeList);
        listRestricted.setAdapter(safe);
        listContains.setAdapter(safe);
    }

    //change colour of status bar and toolbar
    public void changeColor(String colorCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor(colorCode));
        }
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCode)));
    }

    //Comparison algorithm
    public static String [] compareLists(String [] selected, String [] found){
        //check if string s1 contains in string s2 and add to a list
        List<String> newlist = new ArrayList<String>();
        outerloop: for(String s1 : found){
            for (String s2 : selected){
                if(s1.contains(s2)){
                    newlist.add(s1);
                    //continue outerloop if a match if found to prevent dups
                    continue outerloop;
                }
            }
        }
        Log.i("Message", newlist.toString());
        //convert to a String array and return
        return newlist.toArray(new String[newlist.size()]);
    }

    //tesco dataset product retrieval
    public String [] tescoDataset() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            //connect to product data with barcodeNum variable
            URL url = new URL("https://dev.tescolabs.com/product/?gtin=" + barcodeNum + "&KEY=" + "c8da351d589149e8b96167c24654d6fa");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                //extract String of ALL data(TESCO SYSTEM IS FLAWED -- OUTLINED IN DOCUMENTATION)
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                String str = stringBuilder.toString();
                //extract name of product
                itemName = str.substring(str.indexOf("\"description\":") + 16, str.indexOf("\"brand"))
                        .split("\",")[0];
                //if valid product but no ingredients listed
                if (!str.contains("ingredients")){
                    return null;
                }
                //if valid product and contains ingredients
                else {
                    //extract ingredients using java regex
                    String ingredients = str.substring(str.indexOf("\"ingredients\": [") + 16, str.indexOf("\"gda\":"))
                            .replaceAll("[\n\r]", "")
                            .replace("\"", "")
                            .replaceAll("(?i)(?:contains|antioxidant|preservative)s?\\s*", "")
                            .replaceAll("\\<.*?\\>", "")
                            .replaceAll("\\((?:\\d|\\.)+%\\)", "");
                    //split to a list
                    List<String> list = new ArrayList<String>(Arrays.asList(ingredients.split("[:\\,\\.\\(\\)]+")));
                    List<String> newlist = new ArrayList<String>();
                    //trim each string and convert to lowercase if its not empty
                    for (String s : list) {
                        if (s.matches(".*\\w.*")) {
                            newlist.add(s.trim().toLowerCase());
                        }
                    }
                    //remove duplicates using a linked hashset and add to foundItems
                    newlist = new ArrayList<String>(new LinkedHashSet<String>(newlist));
                    foundItems = new String[newlist.size()];
                    Log.i("Message", newlist.toString());
                    return newlist.toArray(foundItems);
                }

            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            //no product data found return null
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}