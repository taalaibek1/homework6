// Я еще доделал код при нажатие на сердечко будет цвет меняться. 


package com.example.calculator;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class SecondActivity extends AppCompatActivity {

    private boolean isClicked=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

double result = getIntent().getDoubleExtra("result", 0);

        TextView textView = findViewById(R.id.text_view);

        if(result==(int)result){
    textView.setText(String.valueOf((int)result));;
        }else{
    textView.setText(String.valueOf(result));
        }

        ImageView imageView =  findViewById(R.id.favorite);
        imageView.setOnClickListener(view -> {
           /* Drawable drawable = imageView.getDrawable();
            drawable.setTint(ContextCompat.getColor(this, R.color.black));*/
            Drawable drawable = imageView.getDrawable();
            if(isClicked){
                drawable.setTint(ContextCompat.getColor(this, R.color.black));

            }
            else{

                drawable.setTint(ContextCompat.getColor(this, R.color.pink));

            }
            isClicked = !isClicked;
        });





        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onNextClick(View view) {
         finishAffinity();
    }



}
