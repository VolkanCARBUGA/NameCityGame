package com.android.isimsehiroyunu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class SureliOyun extends AppCompatActivity {
    private String[] iller = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya",
            "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur",
            "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ",
            "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari",
            "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri",
            "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş",
            "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun",
            "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman",
            "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    private TextView txtSüre, puan, il, txtilBilgi;
    private EditText txtTahmin;
    private Button sbtnHarfAl, sbtnTahmin;
    private Random random, randomHarf;
    private int ilNo, ilSayı, baslangıçHarfsayı, toplamSüre = 181000;
    private String gelenİl, ilBoyutu = "", editTahmin;
    private ArrayList<Character> ilHarf;
    private int maxPuan = 100, azaltPuan, toplamPuan = 0, bolumToplamPuan = 0;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sureli_oyun);
        txtSüre = findViewById(R.id.Süre);
        il = findViewById(R.id.İl);
        puan = findViewById(R.id.puan);
        txtTahmin = findViewById(R.id.Tahmin);
        txtilBilgi = findViewById(R.id.ilBilgi);
        sbtnHarfAl = findViewById(R.id.sbtnHarfAl);
        sbtnTahmin = findViewById(R.id.sbtnTahmin);

        new CountDownTimer(toplamSüre, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtSüre.setText((millisUntilFinished / 60000) + ":" + (millisUntilFinished % 60000 / 1000));

            }

            @Override
            public void onFinish() {
                txtSüre.setText("Oyun Bitti");
                sbtnHarfAl.setEnabled(false);
                sbtnTahmin.setEnabled(false);
                txtTahmin.setEnabled(false);
                AlertDialog.Builder builder = new AlertDialog.Builder(SureliOyun.this);
                builder.setTitle("Yeni Oyun");
                builder.setMessage("Tekrar Oynamak İster Misin");
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                });
                builder.show();
            }
        }.start();
        randomHarf = new Random();
        randomDeger();
    }

    public void sbtnHarfAl(View view) {
        if (ilHarf.size() > 0) {
            randomHarfAl();

            toplamPuan -= azaltPuan;


            Toast.makeText(getApplicationContext(), "Kalan Puan :  " + toplamPuan, Toast.LENGTH_SHORT).show();
        } else
            sbtnHarfAl.setEnabled(false);
    }

    public void sbtnTahmin(View view) {
        sbtnHarfAl.setEnabled(true);
        editTahmin = txtTahmin.getText().toString().trim();
        if (!TextUtils.isEmpty(editTahmin)) {

            if (editTahmin.equals(gelenİl.toLowerCase())) {
                bolumToplamPuan += toplamPuan;
                puan.setText("Toplam Puan :  " + bolumToplamPuan);
                txtTahmin.setText("");
                randomDeger();
            } else
                Toast.makeText(getApplicationContext(), "Tahmin Yanlış ", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "Tahmin boş Olamaz ", Toast.LENGTH_SHORT).show();
    }

    private void randomDeger() {
        ilBoyutu = "";
        random = new Random();
        ilNo = random.nextInt(iller.length);
        gelenİl = iller[ilNo];
        txtilBilgi.setText(gelenİl.length() + " Harfli ilimiz ?");
        if (gelenİl.length() <= 4) {
            baslangıçHarfsayı = 0;
        } else if (gelenİl.length() >= 5 && gelenİl.length() <= 7) {
            baslangıçHarfsayı = 1;
        } else if (gelenİl.length() >= 8 && gelenİl.length() < 10) {
            baslangıçHarfsayı = 2;
        } else {
            baslangıçHarfsayı = 3;
        }
        for (int i = 0; i < gelenİl.length(); i++) {
            if (i < gelenİl.length() - 1)
                ilBoyutu += "_ ";
            else
                ilBoyutu += "_";

        }
        il.setText(ilBoyutu);
        ilHarf = new ArrayList<Character>();
        for (char c : gelenİl.toCharArray()) {
            ilHarf.add(c);
        }
        for (int c = 0; c < baslangıçHarfsayı; c++) {
            randomHarfAl();
        }
        azaltPuan = maxPuan / ilHarf.size();

        toplamPuan = maxPuan;
    }

    private void randomHarfAl() {
        ilSayı = randomHarf.nextInt(ilHarf.size());
        String[] txtHarfler = il.getText().toString().split(" ");
        char[] gelenİlHarf = gelenİl.toCharArray();
        for (int i = 0; i < gelenİl.length(); i++) {
            if (txtHarfler[i].equals("_") && gelenİlHarf[i] == ilHarf.get(ilSayı)) {
                txtHarfler[i] = String.valueOf(ilHarf.get(ilSayı));
                ilBoyutu = "";
                for (int j = 0; j < gelenİl.length(); j++) {
                    if (j == i)
                        ilBoyutu += txtHarfler[j] + " ";
                    else if (j < gelenİl.length() - 1)
                        ilBoyutu += txtHarfler[j] + " ";
                    else
                        ilBoyutu += txtHarfler[j];

                }
                break;
            }

        }
        il.setText(ilBoyutu);
        ilHarf.remove(ilSayı);
    }
}