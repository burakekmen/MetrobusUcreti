package com.burakekmen.metrobusucreti.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.burakekmen.metrobusucreti.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    RadioButton rdTam, rdOgrenci, rdOgretmen, rdYasli;
    Spinner spKalkis, spVaris;
    Button btnHesapla;
    TextView txtUcret;

    AdView adViewUst, adViewAlt;
    AdRequest adRequestUst, adRequestAlt;

    protected List<String> duraklar;
    protected ArrayAdapter<String> durakAdapter;

    String kalkisDurak=null, varisDurak= null;
    int kalkisPosition=0, varisPosition=42;
    List<RadioButton> butonlar;
    int seciliKartTipi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        init();
        spinnerDoldur();
        reklamlariDoldur();
    }

    private void init(){
        rdTam = (RadioButton) findViewById(R.id.activity_main_rdTam);
        rdOgrenci = (RadioButton)findViewById(R.id.activity_main_rdOgrenci);
        rdOgretmen = (RadioButton)findViewById(R.id.activity_main_rdOgretmen);
        rdYasli = (RadioButton) findViewById(R.id.activity_main_rdYasli);

        rdTam.setOnClickListener(this);
        rdOgrenci.setOnClickListener(this);
        rdOgretmen.setOnClickListener(this);
        rdYasli.setOnClickListener(this);

        spKalkis = (Spinner) findViewById(R.id.activity_main_spKalkis);
        spVaris = (Spinner) findViewById(R.id.activity_main_spVaris);

        btnHesapla = (Button) findViewById(R.id.activity_main_btnHesapla);
        txtUcret = (TextView) findViewById(R.id.activity_main_txtUcret);

        butonlar = new ArrayList<>();
        butonlar.add(rdTam);
        butonlar.add(rdOgrenci);
        butonlar.add(rdOgretmen);
        butonlar.add(rdYasli);

        spKalkis.setOnItemSelectedListener(this);
        spVaris.setOnItemSelectedListener(this);

        btnHesapla.setOnClickListener(this);

    }

    private void reklamlariDoldur(){
        adViewUst = (AdView) this.findViewById(R.id.adViewUst);
        adViewAlt = (AdView) this.findViewById(R.id.adViewAlt);

        adRequestUst = new AdRequest.Builder().build();
        adRequestAlt = new AdRequest.Builder().build();

        adViewUst.loadAd(adRequestUst);
        adViewAlt.loadAd(adRequestAlt);
    }

    private void spinnerDoldur(){
        duraklar = new ArrayList<String>();
        duraklar.add("Söğütlüçeşme");
        duraklar.add("Fikirtepe");
        duraklar.add("Uzunçayır");
        duraklar.add("Acıbadem");
        duraklar.add("Burhaniye");
        duraklar.add("15 Temmuz Şehitler Köprüsü");
        duraklar.add("Zincirlikuyu");
        duraklar.add("Mecidiyeköy");
        duraklar.add("Çağlayan");
        duraklar.add("Okmeydanı Hastane");
        duraklar.add("Darülaceze - Perpa");
        duraklar.add("Okmeydanı");
        duraklar.add("Halıcıoğlu");
        duraklar.add("Ayvansaray - Eyüp Sultan");
        duraklar.add("Edirnekapı");
        duraklar.add("Bayrampaşa - Maltepe");
        duraklar.add("Topkapı");
        duraklar.add("Cevizlibağ");
        duraklar.add("Merter");
        duraklar.add("Zeytinburnu");
        duraklar.add("İncirli");
        duraklar.add("Bahçelievler");
        duraklar.add("Şirinevler");
        duraklar.add("Yenibosna");
        duraklar.add("Sefaköy");
        duraklar.add("Beşyol");
        duraklar.add("Florya");
        duraklar.add("Cennet Mahallesi");
        duraklar.add("Küçükçekmece");
        duraklar.add("B. Şehir Bld. Sosyal Tes.");
        duraklar.add("Şükrübey");
        duraklar.add("Avcılar Merkez Üniv. Kampüsü");
        duraklar.add("Cihangir Üniv. Mahallesi");
        duraklar.add("Mustafa Kemal Paşa");
        duraklar.add("Saadetdere Mahallesi");
        duraklar.add("Haramidere Sanayi");
        duraklar.add("Haramidere");
        duraklar.add("Güzelyurt");
        duraklar.add("Beylikdüzü");
        duraklar.add("Beylikdüzü Belediyesi");
        duraklar.add("Cumhuriyet Mahallesi");
        duraklar.add("Hadımköy");
        duraklar.add("Beylikdüzü Sondurak");

        durakAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, duraklar);
        durakAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKalkis.setAdapter(durakAdapter);

        spVaris.setAdapter(durakAdapter);
        spVaris.setSelection(duraklar.size()-1);
    }


    private int secimleriDuzenle(AdapterView parent, int position){
            if(position>1)
                parent.setSelection(position-1);
            else
                parent.setSelection(position+1);

            if(position>=duraklar.size()-1)
                parent.setSelection(position-1);
            else
                parent.setSelection(position+1);

        return position;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        switch (parent.getId()){
            case R.id.activity_main_spKalkis:
                kalkisDurak = parent.getItemAtPosition(position).toString();
                if(kalkisDurak == varisDurak) {
                   kalkisPosition = secimleriDuzenle(parent, position);
                }else
                    kalkisPosition=position;
                break;
            case R.id.activity_main_spVaris:
                varisDurak = parent.getItemAtPosition(position).toString();
                if(kalkisDurak == varisDurak) {
                  varisPosition = secimleriDuzenle(parent, position);
                }else
                    varisPosition = position;
                break;
            default:
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void secenekleriKapat(int pozisyon){
        for(int i=0; i<butonlar.size(); i++){
            if(i != pozisyon)
                butonlar.get(i).setChecked(false);
        }
    }


    private boolean kartTipiSeciliMi(){
        boolean sonuc= false;

        for(int i=0; i<butonlar.size(); i++){
            if(butonlar.get(i).isChecked()) {
                sonuc = true;
                seciliKartTipi = i;
                break;
            }

        }
        return sonuc;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.activity_main_rdTam:
                secenekleriKapat(0);
                break;
            case R.id.activity_main_rdOgrenci:
                secenekleriKapat(1);
                break;
            case R.id.activity_main_rdOgretmen:
                secenekleriKapat(2);
                break;
            case R.id.activity_main_rdYasli:
                secenekleriKapat(3);
                break;
            case R.id.activity_main_btnHesapla:
                if(kalkisDurak == varisDurak)
                    Toast.makeText(this, "Lütfen Farklı Durak Seçiniz!", Toast.LENGTH_SHORT).show();
                else {
                    if (kartTipiSeciliMi()) {
                        txtUcret.setText("₺ " + String.valueOf(ucretHesapla(seciliKartTipi, getDurakSayisi(kalkisPosition, varisPosition))));
                    }else
                        Toast.makeText(this, "Kart Tipi Seçiniz!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

        }
    }

    public int getDurakSayisi(int kalkisPos, int varisPos){
        int durakSayisi = varisPos - kalkisPos;
        if(durakSayisi < 0)
            durakSayisi*= -1;

        return durakSayisi+1; // Kalkış Durağını da hesaba katıyordu ve 1 durak eksik hesaplıyordu.
    }

    public double ucretHesapla(int kartTipi, int durakSayisi) {
        double ucret = 0;

        if (durakSayisi >= 1 && durakSayisi <= 3) {
            switch (kartTipi) {
                case 0:
                    ucret = 1.95;
                    break;
                case 1:
                    ucret = 1.10;
                    break;
                case 2:
                    ucret = 1.45;
                    break;
                case 3:
                    ucret = 1.45;
                    break;
                default:
                    break;
            }
        } else if (durakSayisi >= 4 && durakSayisi <= 9) {
            switch (kartTipi) {
                case 0:
                    ucret = 3.00;
                    break;
                case 1:
                    ucret = 1.20;
                    break;
                case 2:
                    ucret = 1.85;
                    break;
                case 3:
                    ucret = 1.85;
                    break;
                default:
                    break;
            }
        } else if (durakSayisi >= 10 && durakSayisi <= 15) {
            switch (kartTipi) {
                case 0:
                    ucret = 3.25;
                    break;
                case 1:
                    ucret = 1.25;
                    break;
                case 2:
                    ucret = 1.90;
                    break;
                case 3:
                    ucret = 1.90;
                    break;
                default:
                    break;
            }
        } else if (durakSayisi >= 16 && durakSayisi <= 21) {
            switch (kartTipi) {
                case 0:
                    ucret = 3.40;
                    break;
                case 1:
                    ucret = 1.25;
                    break;
                case 2:
                    ucret = 2.00;
                    break;
                case 3:
                    ucret = 2.00;
                    break;
                default:
                    break;
            }
        } else if (durakSayisi >= 22 && durakSayisi <= 27) {
            switch (kartTipi) {
                case 0:
                    ucret = 3.50;
                    break;
                case 1:
                    ucret = 1.25;
                    break;
                case 2:
                    ucret = 2.00;
                    break;
                case 3:
                    ucret = 2.00;
                    break;
                default:
                    break;
            }
        } else if (durakSayisi >= 28 && durakSayisi <= 33) {
            switch (kartTipi) {
                case 0:
                    ucret = 3.60;
                    break;
                case 1:
                    ucret = 1.25;
                    break;
                case 2:
                    ucret = 2.10;
                    break;
                case 3:
                    ucret = 2.10;
                    break;
                default:
                    break;
            }
        } else if (durakSayisi >= 34 && durakSayisi <= 39) {
            switch (kartTipi) {
                case 0:
                    ucret = 3.85;
                    break;
                case 1:
                    ucret = 1.25;
                    break;
                case 2:
                    ucret = 2.10;
                    break;
                case 3:
                    ucret = 2.10;
                    break;
                default:
                    break;
            }
        } else {
            switch (kartTipi) {
                case 0:
                    ucret = 3.85;
                    break;
                case 1:
                    ucret = 1.25;
                    break;
                case 2:
                    ucret = 2.10;
                    break;
                case 3:
                    ucret = 2.10;
                    break;
                default:
                    break;
            }
        }

        return ucret;
    }
}
