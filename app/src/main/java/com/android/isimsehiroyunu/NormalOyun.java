package com.android.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyun extends AppCompatActivity {
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
    private TextView bilgi, txtİl, txtPuan;
    EditText tahmin;
    Button btnHarfAl, btnTahmin;

    private Random random, randomHarf;
    private int ilNo, ilSayı, baslangıçHarfsayı;
    private String gelenİl, ilBoyutu = "", editTahmin;
    private ArrayList<Character> ilHarf;
    private int maxPuan=100 , azaltPuan, toplamPuan=0, bolumToplamPuan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);
        bilgi = findViewById(R.id.txtilBilgi);
        txtİl = findViewById(R.id.İl);
        tahmin = findViewById(R.id.Tahmin);
        btnHarfAl = findViewById(R.id.btnHarfAl);
        btnTahmin = findViewById(R.id.btnTahmin);
        txtPuan = findViewById(R.id.txtPuan);
        randomHarf = new Random();
        randomDeger1();
    }

    public void btnHarfAl(View view) {
        if (ilHarf.size() > 0) {
            randomHarfAl();

            toplamPuan -= azaltPuan;



            Toast.makeText(getApplicationContext(), "Kalan Puan :  " + toplamPuan, Toast.LENGTH_SHORT).show();
        } else
            btnHarfAl.setEnabled(false);

    }

    public void btnTahmin(View view) {
        btnHarfAl.setEnabled(true);
        editTahmin = tahmin.getText().toString().trim();
        if (!TextUtils.isEmpty(editTahmin)) {

            if (editTahmin.equals(gelenİl.toLowerCase())) {
                bolumToplamPuan += toplamPuan;
                txtPuan.setText("Toplam Puan :  " + bolumToplamPuan);
                tahmin.setText("");
                randomDeger1();
            } else
                Toast.makeText(getApplicationContext(), "Tahmin Yanlış ", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "Tahmin boş Olamaz ", Toast.LENGTH_SHORT).show();
    }


    private void randomDeger1() {
        ilBoyutu = "";
        random = new Random();
        ilNo = random.nextInt(iller.length);
        gelenİl = iller[ilNo];
        bilgi.setText(gelenİl.length() + " Harfli ilimiz ?");

        if (gelenİl.length() >= 5 && gelenİl.length() <= 7) {
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
        txtİl.setText(ilBoyutu);
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
        String[] txtHarfler = txtİl.getText().toString().split(" ");
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
        txtİl.setText(ilBoyutu);
        ilHarf.remove(ilSayı);
    }
}