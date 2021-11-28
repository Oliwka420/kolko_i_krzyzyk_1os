package com.seriaoliwkacoding.gra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    Boolean ktoZaczyna; //kto zacznie gre -> sharedPreferences
    boolean czyWygrana; //deklaracja zmiennej typu boolean - sprawdza czy gra juz sie skonczyla
    boolean checkTablica[] = new boolean[9]; //tablica czy zaznaczone
    Button tablica[] = new Button[9]; //tablica z buttonami
    String MY_PREFS_NAME; //nazwa preferencji

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView zmienna = findViewById(R.id.zmiennanapis); //deklaracja zmiennej
        tablica[0] = findViewById(R.id.b1); //deklaracja 1 przycisku
        tablica[1] = findViewById(R.id.b2); //deklaracja 2 przycisku
        tablica[2] = findViewById(R.id.b3); //deklaracja 3 przycisku
        tablica[3] = findViewById(R.id.b4); //deklaracja 4 przycisku
        tablica[4] = findViewById(R.id.b5); //deklaracja 5 przycisku
        tablica[5] = findViewById(R.id.b6); //deklaracja 6 przycisku
        tablica[6] = findViewById(R.id.b7); //deklaracja 7 przycisku
        tablica[7] = findViewById(R.id.b8); //deklaracja 8 przycisku
        tablica[8] = findViewById(R.id.b9); //deklaracja 9 przycisku
        czyWygrana = false; //ustawienie wygranej na false (gra sie toczy)
        for (int i = 0; i < 9;i++){ //ustawienie wartosci na false w tablicy zaznaczeń
            checkTablica[i] = false;
        }
        ktoZaczyna(); //sprawdzanie kto wygrał poprzednią grę (SharedPreferences)
        zmienna.setText("ty"); //ustawienie zmiennej na podanie informacji kogo kolej
    }
    /*
            ░█░█░▀█▀░█▀█░░░▀▀█░█▀█░█▀▀░▀▀█░█▀█░▀█▀░█▀▀
            ░█▀▄░░█░░█░█░░░▄▀░░█▀█░█░░░▄▀░░█░█░░█░░█▀▀
            ░▀░▀░░▀░░▀▀▀░░░▀▀▀░▀░▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀▀▀
     */
    public void ktoZaczyna(){

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE); //pobranie preferencji
        ktoZaczyna = prefs.getBoolean("ktoZaczyna", false);  //wczytanie zwycięzcy z poprzedniej gry
            if(ktoZaczyna){ //jeśli pobrana preferencja jest pozytywna (true)
                losowanieLiczby(); //zaczyna komputer (losuje przycisk i go wypelnia)
            }
    }
    /*
            ░█░░░█▀█░█▀▀░█▀█░█░█░█▀█░█▀█░▀█▀░█▀▀
            ░█░░░█░█░▀▀█░█░█░█▄█░█▀█░█░█░░█░░█▀▀
            ░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀▀▀
     */
    public void losowanieLiczby() {
        if (!czyWygrana) { //jesli czyWygrana jest false (gra sie toczy)
            int max = 8; //dekalracja liczby maksymalnej do wylosowania
            int min = 0; //deklaracja liczby minimanlnej do wylosowania
            double x = Math.floor(Math.random() * (max - min + 1) + min); //wylosowanie liczby
            int wartoscInt = (int) x; //rzutowanie double x na typ int
            while (checkTablica[wartoscInt]) { //dopoki nie wylosuje wartosci true w tablicy zaznaczen
                x = Math.floor(Math.random() * (max - min + 1) + min);//wylosowanie liczby
                wartoscInt = (int) x;  //rzutowanie double x na typ int
                if (!checkTablica[wartoscInt]) { //jesli wylosuje liczbe ktora w tablicy zaznaczen nie jest zaznacozna
                    break; //przerwij pętle
                }
            }
            checkTablica[wartoscInt] = true; //zaznacz w tablicy sprawdzen wylosowana liczbe na zaznaczone (true)
            tablica[wartoscInt].setText("o"); //ustawienie zawartosci wylosowanego przycisku na "O"
            tablica[wartoscInt].setEnabled(false); //zablokowanie wylosowanego przycisku
        }
    }
/*
        ░█░█░█░░░▀█▀░█░█░█▀█░▀█▀░█▀▀░█▀▀░▀█▀░█▀▀░░░▀▀█░░░█▀█░█▀█░█▀▄░█▀█░█▄█░█▀▀░▀█▀░█▀▄░█▀▀░█▄█
        ░█▀▄░█░░░░█░░█▀▄░█░█░░█░░█▀▀░█░░░░█░░█▀▀░░░▄▀░░░░█▀▀░█▀█░█▀▄░█▀█░█░█░█▀▀░░█░░█▀▄░█▀▀░█░█
        ░▀░▀░▀▀▀░▀▀▀░▀░▀░▀░▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░░░▀▀▀░░░▀░░░▀░▀░▀░▀░▀░▀░▀░▀░▀▀▀░░▀░░▀░▀░▀▀▀░▀░▀
 */

    public void opcje(int i){
            if(!czyWygrana) { //tylko jeśli wygrana jest false (gra sie toczy)
                TextView zmienna = findViewById(R.id.zmiennanapis); //deklaracja zmiennej z informacja kogo kolej
                tablica[i].setText("x");  //zaznaczony przycisk wypelnij X
                checkTablica[i] = true;  //zaznacz w tablicy ze dany element jest juz zaznaczony(wartosc true)
                tablica[i].setEnabled(false); //ustaw dany przycisk na zablokowany
                Handler handler = new Handler(); //sleep
                handler.postDelayed(new Runnable() { //sleep
                    public void run() {
                        zmienna.setText("ty"); //ustawienie zmiennej informacyjnej kogo kolej na "ty"
                        czywygrana(); //sprawdz czy wygrana
                        losowanieLiczby(); // wywolanie funkcji losowania liczby
                        czywygrana(); //sprawdz czy wygrana
                    }
                }, 500); //ile ms sleep
                zmienna.setText("komputer"); //zmien informacje kogo kolej na komputer
            }
    }
    /*
                ░█▀▄░█▀█░▀▀█░█▀█░░░█▀▄░█▀█░█▀█░█░█░█▀▀░█░█
                ░█▀▄░█▀█░▄▀░░█▀█░░░█░█░█▀█░█░█░░█░░█░░░█▀█
                ░▀▀░░▀░▀░▀▀▀░▀░▀░░░▀▀░░▀░▀░▀░▀░░▀░░▀▀▀░▀░▀
     */
    public void ify(String b) {
        TextView text = findViewById(R.id.zwyklynapis);
        TextView zmiennanapis = findViewById(R.id.zmiennanapis);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        czyWygrana = true;
        text.setText("Wygrywa gracz: "); //ustawienie zmiennej na "Wygrywa gracz: '
        zmiennanapis.setText(b); //ustawienie zmiennej na informacje kto wygral
        if (b == "o"){ // jesli przeslany button ma wartosc o to:
            prefs.edit().putBoolean("ktoZaczyna", true).apply(); //przeslij do bazy danych wartosc true
        }else if (b == "x"){ //jeśli przesłana zawartość to "x" to:
            prefs.edit().putBoolean("ktoZaczyna", false).apply(); //przeslij do bazy danych wartosc false
        }
    }
    /*
                ░█▀▀░▀▀█░█░█░░░█░█░▀█▀░█▀█
                ░█░░░▄▀░░░█░░░░█▄█░░█░░█░█
                ░▀▀▀░▀▀▀░░▀░░░░▀░▀░▀▀▀░▀░▀
     */
    public void czywygrana() {
        TextView text = findViewById(R.id.zwyklynapis); //dekalracja zmiennej
        TextView zmiennanapis = findViewById(R.id.zmiennanapis); //dekalracja zmiennej
        /*
        ░█▀▄░█▀▀░█░█░█░░░█▀█░█▀▄░█▀█░█▀▀░▀▀█░█▀█░░░▀▀█░█▄█░▀█▀░█▀▀░█▀█░█▀█░█░█░█▀▀░█░█
        ░█░█░█▀▀░█▀▄░█░░░█▀█░█▀▄░█▀█░█░░░░░█░█▀█░░░▄▀░░█░█░░█░░█▀▀░█░█░█░█░░█░░█░░░█▀█
        ░▀▀░░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀▀░░▀░▀░░░▀▀▀░▀░▀░▀▀▀░▀▀▀░▀░▀░▀░▀░░▀░░▀▀▀░▀░▀
         */
        String b1 = tablica[0].getText().toString();
        String b2 = tablica[1].getText().toString();
        String b3 = tablica[2].getText().toString();
        String b4 = tablica[3].getText().toString();
        String b5 = tablica[4].getText().toString();
        String b6 = tablica[5].getText().toString();
        String b7 = tablica[6].getText().toString();
        String b8 = tablica[7].getText().toString();
        String b9 = tablica[8].getText().toString();
        if (!czyWygrana) { //jesli gra nadal sie toczy to sprawdzaj czy juz przypadkiem sie nie powinna skonczyc (ktos wygra)
            if (checkTablica[0] && b1 == b2 && b1 == b3) { //poziome 1
                ify(b1);//przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            } else if (checkTablica[3] && b4 == b5 && b4 == b6) { //poziome 2
                ify(b4); //przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            } else if (checkTablica[6] && b7 == b8 && b7 == b9) { //poziome 3
                ify(b7);//przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            } else if (checkTablica[0] && b1 == b4 && b1 == b7) { //pionowe 1
                ify(b7);//przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            } else if (checkTablica[1] && b2 == b5 && b2 == b8) { //pionowe 2
                ify(b2);//przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            } else if (checkTablica[2] && b3 == b6 && b3 == b9) { //pionowe 3
                ify(b3);//przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            }else if (checkTablica[0] && b1 == b5 && b1 == b9){ //ukos 1
                ify(b1);//przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            }else if (checkTablica[2] && b3 == b5 && b3 == b7){ //ukos 2
                ify(b3);//przeslanie zawartosci przycisku do ifa który zapisuje w bazie danych i informuje o wygranej zwycięzce
            }
        }
    }
    /*
                ░█▀█░█▀▄░▀▀█░█░█░█▀▀░▀█▀░█▀▀░█░█░▀█▀
                ░█▀▀░█▀▄░▄▀░░░█░░█░░░░█░░▀▀█░█▀▄░░█░
                ░▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀
     */
    public void wypelnij1(View view) {
        opcje(0);
    }
    public void wypelnij2(View view) {
        opcje(1);
    }
    public void wypelnij3(View view) {
        opcje(2);
    }
    public void wypelnij4(View view) {
        opcje(3);
    }
    public void wypelnij5(View view) {
        opcje(4);
    }
    public void wypelnij6(View view) {
        opcje(5);
    }
    public void wypelnij7(View view) {
        opcje(6);
    }
    public void wypelnij8(View view) {
        opcje(7);
    }
    public void wypelnij9(View view) {
        opcje(8);
    }

    public void restart(View view) {
        TextView text = findViewById(R.id.zwyklynapis); //deklaracja zmiennej

                /*
WYPEŁNIENIE TABLICY SPRAWDZAJĄCEJ ZAZNACZENIE NA FAlSE (NIE ZAZNACZONE), USTAWIENIE PRZYCISKÓW NA ODBLOKOWANE ORAZ USTAWIENIE ZAWARTOŚCI NA PUSTE
         */
        for (int i = 0; i < 9;i++){
            checkTablica[i] = false;
            tablica[i].setEnabled(true);
            tablica[i].setText("");
        }
        czyWygrana = false; //ustawienie czyWygrana na false (gra sie toczy)
        text.setText("Aktualny ruch: "); //ustawienie zmiennej na "aktualny ruch:"

    }
}