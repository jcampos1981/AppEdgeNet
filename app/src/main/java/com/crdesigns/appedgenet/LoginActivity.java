package com.crdesigns.appedgenet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    boolean existeDB = false;
    TextView txtEmail;
    TextView txtPassword;
    Button btnLogin;
    final MySQLiteHelper db = new MySQLiteHelper(this);
    //final MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBWork();

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnLogin:
                        txtEmail = findViewById(R.id.txtEmail);
                        txtPassword = findViewById(R.id.txtPassword);
                        List<String> lables = db.getUser(txtEmail.getText().toString(), txtPassword.getText().toString());
                        if(lables.size() >= 1) {
                            if (lables.get(0).contains("null")) {
                                Toast.makeText(getApplicationContext(),"User doesn't exists.",Toast.LENGTH_LONG).show();
                            }
                            else{
                                String[] separated = lables.get(0).split("\\|");
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                intent.putExtra("idUsr" ,separated[0]);
                                intent.putExtra("Email" ,separated[1]);
                                intent.putExtra("Points" ,separated[2]);
                                startActivity(intent);
                            }

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"User doesn't exists.",Toast.LENGTH_LONG).show();
                        }
                }
            }
        };
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(handler);
    }

    private void DBWork(){
        existeDB = db.doesDatabaseExist(getApplicationContext(), "UsersDB");
        if (!existeDB) {
            db.addInterests(new Interests("Music"));
            db.addInterests(new Interests("Cats"));
            db.addInterests(new Interests("City"));
            db.addUsers(new Users("user", "user", "0"));
            db.addInterestsUser(new InterestsUsers("1","Cats"));
            db.addVideo(new VideoUsers(
                    "https://player.vimeo.com/external/163914755.sd.mp4?s=b98042d47eca319e213e11087408bf73008c4178&profile_id=164",
                    "https://edge.stg-alefedge.com/v1/content?url=00c104e612cb7f461e6446d77125a0edb3b8a827df1bd1149f3a08cad05d4af7803edd46efcee7da7d3f9c34799b0043d0d0f934752949b6978b2409e57a890b"
                    ,"1","0")); //cat
            db.addVideo(new VideoUsers(
                    "https://player.vimeo.com/external/330970621.sd.mp4?s=1b02e92dde89b0f65cc63e24cbd4837d66e16f3e&profile_id=165",
                    "https://edge.stg-alefedge.com/v1/content?url=00c104e612cb7f461e6446d77125a0edb3b8a827df1bd1149f3a08cad05d4af7ce28087a359bcdf9fa9f9535c26ca26916beecbeff01196ae60d59de27d41553",
                    "1","0")); //cat
            db.addVideo(new VideoUsers(
                    "https://player.vimeo.com/external/315137091.sd.mp4?s=6a21c942a4aa09eaedf5d577ade566881a56d69e&profile_id=165",
                    "https://edge.stg-alefedge.com/v1/content?url=00c104e612cb7f461e6446d77125a0edb3b8a827df1bd1149f3a08cad05d4af7e957e1996e3304ba27ae79831d85fc727f9116e217cc00baf5676928c13975445748a5b3558caf50b652d9b89bedb145",
                    "1","0")); //city
            db.addVideo(new VideoUsers("https://player.vimeo.com/external/161442861.sd.mp4?s=4b57703c2094b22ccfdf6a848d603cd8a7c208ba&profile_id=164",
                    "https://edge.stg-alefedge.com/v1/content?url=00c104e612cb7f461e6446d77125a0edb3b8a827df1bd1149f3a08cad05d4af7b15f3210a5257eccad8da3e24c2ed8eee1011d348cb7c1cd5e877d40d3fc8e10",
                    "1","0")); //music
        }
    }
}