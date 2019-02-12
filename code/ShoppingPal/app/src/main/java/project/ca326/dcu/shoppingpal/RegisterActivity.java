package project.ca326.dcu.shoppingpal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Authenticate user
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            //homepage
            finish();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonRegister.setOnClickListener(this);
    }

    public void registerUser(){
        //format email and password
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            //empty email
            Toast.makeText(this, "Please enter an Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //empty password
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }
        //if email and password are entered
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                //user successfully registered
                                //start homepage
                                finish();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                            //registration unsuccessful, report error to the user with toast
                            else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed, " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        catch (Exception e){
            Log.i("Error", e.getMessage(), e);
        }
    }

    //opens registry when pressed
    @Override
    public void onClick(View view){
        if(view == buttonRegister){
            registerUser();
        }
    }

    //goes back to Login page
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}