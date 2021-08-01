package com.crdesigns.appedgenet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.w3c.dom.Text;

import java.util.List;

public class Dashboard extends AppCompatActivity {
    TextView payment_label;
    Button btnVideo;
    final MySQLiteHelper db = new MySQLiteHelper(this);
    String idUsr;
    String Points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        idUsr = extras.getString("idUsr");
        String Email = extras.getString("Email");
        Points = extras.getString("Points");
        //Toast.makeText(getApplicationContext(),idUsr,Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
            View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnVideo:

                        List<String> lables = db.getPoints(idUsr);
                        if(lables.size() >= 1) {
                            if (!lables.get(0).contains("null")) {
                                Points =lables.get(0);
                                //payment_label.setText(lables.get(0));
                            }
                        }


                        Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                        intent.putExtra("idUsr" ,idUsr);
                        intent.putExtra("Points" ,Points);
                        startActivity(intent);
                }
            }
        };
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(handler);
        payment_label = (TextView) findViewById(R.id.payment_label);
        payment_label.setOnClickListener(handler);
        payment_label.setText(Points);
        ChipGroup chipGroup = findViewById(R.id.chip_group_main);

        List<String> lables = db.getInterests();
        if(lables.size() >= 1) {
            if (!lables.get(0).contains("null")) {
                for (int i=0; i<lables.size(); i++) {
                    Chip chip = new Chip(this);
                    chip.setText(lables.get(i));
                    chip.isClickable();
                    chip.isCheckable();
                    chip.isFocusable();
                    chip.setChipBackgroundColorResource(R.color.material_on_background_disabled);

                    List<String> labelsIU = db.getInterestsUser(idUsr,lables.get(i));
                    if(labelsIU.size() >= 1) {
                        if (labelsIU.get(0).contains(lables.get(i))) {
                            chip.setChipBackgroundColorResource(R.color.black);
                        }
                    }
                    chip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String text = ((Chip) view).getText().toString();
                            Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                            ColorStateList cl = chip.getChipBackgroundColor();

                            if(cl.getDefaultColor() == 1644167167){
                                chip.setChipBackgroundColorResource(R.color.black);
                            }
                            else{
                                chip.setChipBackgroundColorResource(R.color.material_on_background_disabled);
                            }


                        }
                    });
                    chipGroup.addView(chip);

                }
            }
        }





        //Chip chip2 = new Chip(this);
        //chip2.setText("XYZ");  //chip2
        //chipGroup.addView(chip);
        //chipGroup.addView(chip2);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Toast.makeText(getApplicationContext(),"Great!",Toast.LENGTH_LONG).show();

        List<String> lables = db.getPoints(idUsr);
        if(lables.size() >= 1) {
            if (!lables.get(0).contains("null")) {
                payment_label.setText(lables.get(0));
            }
        }
    }
}