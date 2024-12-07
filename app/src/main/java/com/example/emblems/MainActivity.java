package com.example.emblems;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button Rost, Kaz, Ufa, Od, back;
    FrameLayout main_layout, second_layout;
    ImageView imageView;
    ArrayList<Bitmap> bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Rost = findViewById(R.id.Rostov);
        Kaz = findViewById(R.id.Kasakhstan);
        Ufa = findViewById(R.id.Ufa);
        Od = findViewById(R.id.Odess);
        back = findViewById(R.id.back_btn);

        main_layout = findViewById(R.id.main_layout);
        second_layout = findViewById(R.id.second_layout);
        imageView = findViewById(R.id.image_v);

        bm = new ArrayList<>();
        try{
            bm.add(BitmapFactory.decodeResource(getResources(), R.drawable.rostov));
            bm.add(BitmapFactory.decodeResource(getResources(), R.drawable.kaz));
            bm.add(BitmapFactory.decodeResource(getResources(), R.drawable.ufa));
            bm.add(BitmapFactory.decodeResource(getResources(), R.drawable.odess));
        }
        catch(Resources.NotFoundException ex){
            Log.d("Eml_test", "Исключение ->" + ex.getMessage());
        }

        //Event Listener'ы для перехода на 2 форму с выбранным флагом
        Rost.setOnClickListener(view -> {
            change_layout(0);
            Toast.makeText(MainActivity.this, "Сейчас показан: флаг Ростова", Toast.LENGTH_SHORT).show();
        });

        Kaz.setOnClickListener(view -> {
            change_layout(1);
            Toast.makeText(MainActivity.this, "Сейчас показан: флаг Казахстана", Toast.LENGTH_SHORT).show();
        });

        Ufa.setOnClickListener(view -> {
            change_layout(2);
            Toast.makeText(MainActivity.this, "Сейчас показан: флаг Уфы", Toast.LENGTH_SHORT).show();
        });

        Od.setOnClickListener(view -> {
            change_layout(3);
            Toast.makeText(MainActivity.this, "Сейчас показан: флаг Одессы", Toast.LENGTH_SHORT).show();
        });

        //Event Listener для возврата на главную форму
        back.setOnClickListener(view -> {
            main_layout.setVisibility(View.VISIBLE);
            second_layout.setVisibility(View.INVISIBLE);

            Toast.makeText(MainActivity.this, "Возрат в меню", Toast.LENGTH_SHORT).show();
        });
    }

    private void change_layout(int imageChangeID){
        main_layout.setVisibility(View.INVISIBLE);
        second_layout.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(bm.get(imageChangeID));
    }

}