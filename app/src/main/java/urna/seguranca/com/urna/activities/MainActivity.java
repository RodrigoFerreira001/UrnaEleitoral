package urna.seguranca.com.urna.activities;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import urna.seguranca.com.urna.R;

public class MainActivity extends AppCompatActivity {

    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    Button bt7;
    Button bt8;
    Button bt9;
    Button bt0;
    Button btbranco;
    Button btcorrige;
    Button btconfirma;

    TextView tipoCargo;
    ImageView fotoCandidato;
    LinearLayout caixa1;
    LinearLayout caixa2;
    LinearLayout caixa3;

    TextView numeros[] = new TextView[3];

//    LinearLayout caixa4;
//    LinearLayout caixa5;
    TextView nome;
    TextView nomeCandidato;
    TextView partido;
    TextView partidoCandidato;

    Vibrator v;

    int numIndex = 0;
    String numVot = "";

    int tipoCargoAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        bt0 = findViewById(R.id.bt0);
        btbranco = findViewById(R.id.btbranco);
        btcorrige = findViewById(R.id.btcorrige);
        btconfirma = findViewById(R.id.btconfirma);

        tipoCargo = findViewById(R.id.tipo_cargo);
        fotoCandidato = findViewById(R.id.foto_candidato);
        caixa1 = findViewById(R.id.caixa1);
        caixa2 = findViewById(R.id.caixa2);
        caixa3 = findViewById(R.id.caixa3);
//        caixa4 = findViewById(R.id.caixa4);
//        caixa5 = findViewById(R.id.caixa5);

        numeros[0] = findViewById(R.id.numero1);
        numeros[1] = findViewById(R.id.numero2);
        numeros[2] = findViewById(R.id.numero3);

        nome = findViewById(R.id.nome);
        nomeCandidato = findViewById(R.id.nome_candidato);
        partido = findViewById(R.id.partido);
        partidoCandidato = findViewById(R.id.partido_candidato);

        if(tipoCargoAtual == 0){
            caixa3.setVisibility(View.GONE);
        }

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(1);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(2);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(3);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(4);
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(5);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(6);
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(7);
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(8);
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(9);
            }
        });

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(0);
            }
        });

        btbranco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(-1);
            }
        });

        btcorrige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(-2);
            }
        });

        btconfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
                buttonPress(-3);
            }
        });
    }

    public void buttonPress(int cod){
        switch (cod){
            case 1:
                addNum(1);
                break;

            case 2:
                addNum(2);
                break;

            case 3:
                addNum(3);
                break;

            case 4:
                addNum(4);
                break;

            case 5:
                addNum(5);
                break;

            case 6:
                addNum(6);
                break;

            case 7:
                addNum(7);
                break;

            case 8:
                addNum(8);
                break;

            case 9:
                addNum(9);
                break;

            case 0:
                addNum(0);
                break;

            case -1:
                break;

            case -2:

                Toast.makeText(this, "LENG: " + numVot.length(), Toast.LENGTH_SHORT).show();

                switch (tipoCargoAtual){
                    case 0:
                        numIndex = 0;
                        numVot = "";
                        for(TextView t : numeros){
                            t.setText("");
                        }

                        fotoCandidato.setVisibility(View.GONE);
                        nome.setVisibility(View.GONE);
                        nomeCandidato.setVisibility(View.GONE);
                        partido.setVisibility(View.GONE);
                        partidoCandidato.setVisibility(View.GONE);

                        break;

                    case 1:
                        break;
                }
                break;

            case -3:
                MediaPlayer mp = MediaPlayer.create(this, R.raw.confirma);
                mp.start();
                break;
        }
    }

    public void addNum(int num){

        Toast.makeText(this, "LENG: " + numVot.length(), Toast.LENGTH_SHORT).show();

        switch (tipoCargoAtual){
            case 0:
                if(numVot.length() < 2){
                    Toast.makeText(this, "Entrou Add", Toast.LENGTH_SHORT).show();
                    numVot += String.valueOf(num);
                    numeros[numIndex].setText(String.valueOf(num));
                    numIndex++;
                }

                if(numVot.length() == 2){
                    fotoCandidato.setVisibility(View.VISIBLE);
                    nome.setVisibility(View.VISIBLE);
                    nomeCandidato.setVisibility(View.VISIBLE);
                    partido.setVisibility(View.VISIBLE);
                    partidoCandidato.setVisibility(View.VISIBLE);
                }

                break;

            case 1:
                break;
        }
    }
}
