package urna.seguranca.com.urna.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import urna.seguranca.com.urna.R;
import urna.seguranca.com.urna.models.Candidato;

public class MainActivity extends AppCompatActivity {

    ArrayList<Candidato> candidatos = new ArrayList<>();
    int votosBrancos = 0;
    int votosNulos = 0;

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

        loadCandidatos();

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

        bt0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Contagem dos votos");

                String votos = "";
                for(Candidato c : candidatos){
                    votos += c.getNome() + ": " + c.getVotos() + "\n";
                }

                votos += "Brancos: " + votosBrancos + "\n";
                votos += "Nulos: " + votosNulos;

                builder.setMessage(votos);

                builder.create().show();

                return true;
            }
        });

        btbranco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);

                fotoCandidato.setVisibility(View.VISIBLE);
                nome.setVisibility(View.GONE);
                nomeCandidato.setVisibility(View.VISIBLE);
                partido.setVisibility(View.GONE);
                partidoCandidato.setVisibility(View.GONE);

                fotoCandidato.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face));

                nomeCandidato.setText("VOTO EM BRANCO");

                for(TextView t : numeros){
                    t.setText("0");
                }

                if(tipoCargoAtual == 0){
                    numVot = "00";
                }else
                if(tipoCargoAtual == 1){
                    numVot = "000";
                }
            }
        });

        btcorrige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);
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
            }
        });

        btconfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);

                if(tipoCargoAtual == 0){
                    if(numVot.length() == 2){

                        boolean found = false;

                        for(Candidato c : candidatos){
                            if(c.getNumero().equals(numVot)){
                                c.incVoto();
                                found = true;
                                break;
                            }
                        }

                        
                        if(!found){
                            if(numVot.equals("00") && nomeCandidato.getText().toString().equals("VOTO EM BRANCO")){
                                votosBrancos++;
                            }else{
                                votosNulos++;
                            }
                        }

                        caixa3.setVisibility(View.VISIBLE);

                        numIndex = 0;
                        tipoCargoAtual = 1;
                        numVot = "";
                        for(TextView t : numeros){
                            t.setText("");
                        }

                        fotoCandidato.setVisibility(View.GONE);
                        nome.setVisibility(View.GONE);
                        nomeCandidato.setVisibility(View.GONE);
                        partido.setVisibility(View.GONE);
                        partidoCandidato.setVisibility(View.GONE);

                        tipoCargo.setText("Governador");


                        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.confirma);
                        mp.start();
                    }
                }else
                if(tipoCargoAtual == 1){
                    if(numVot.length() == 3){

                        boolean found = false;

                        for(Candidato c : candidatos){
                            if(c.getNumero().equals(numVot)){
                                c.incVoto();
                                found = true;
                                break;
                            }
                        }

                        if(!found){
                            if(numVot.equals("000") && nomeCandidato.getText().toString().equals("VOTO EM BRANCO")){
                                votosBrancos++;
                            }else{
                                votosNulos++;
                            }
                        }

                        caixa3.setVisibility(View.GONE);

                        numIndex = 0;
                        tipoCargoAtual = 0;
                        numVot = "";
                        for(TextView t : numeros){
                            t.setText("");
                        }

                        fotoCandidato.setVisibility(View.GONE);
                        nome.setVisibility(View.GONE);
                        nomeCandidato.setVisibility(View.GONE);
                        partido.setVisibility(View.GONE);
                        partidoCandidato.setVisibility(View.GONE);

                        tipoCargo.setText("Presidente");

                        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.confirma);
                        mp.start();

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.fim, null);
                        builder.setView(v);
                        builder.setPositiveButton("CONFIMAR", null);
                        builder.setNegativeButton("CANCELAR", null);
                        builder.create().show();
                    }
                }
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
        }
    }

    public void addNum(int num){

        switch (tipoCargoAtual){
            case 0:
                if(numVot.length() < 2){
                    numVot += String.valueOf(num);
                    numeros[numIndex].setText(String.valueOf(num));
                    numIndex++;

                    if(numVot.length() == 2){

                        fotoCandidato.setVisibility(View.VISIBLE);
                        nome.setVisibility(View.VISIBLE);
                        nomeCandidato.setVisibility(View.VISIBLE);
                        partido.setVisibility(View.VISIBLE);
                        partidoCandidato.setVisibility(View.VISIBLE);

                        boolean found = false;

                        for(Candidato c : candidatos){

                            if(c.getNumero().equals(numVot)){
                                nomeCandidato.setText(c.getNome());
                                partidoCandidato.setText(c.getPartido());
                                fotoCandidato.setImageBitmap(c.getFoto());
                                found = true;
                                break;
                            }
                        }

                        if(!found){
                            fotoCandidato.setVisibility(View.VISIBLE);
                            nome.setVisibility(View.VISIBLE);
                            nomeCandidato.setVisibility(View.VISIBLE);
                            partido.setVisibility(View.GONE);
                            partidoCandidato.setVisibility(View.GONE);

                            nomeCandidato.setText("NULO");
                            fotoCandidato.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face));
                        }
                    }
                }

                break;

            case 1:
                if(numVot.length() < 3){
                    numVot += String.valueOf(num);
                    numeros[numIndex].setText(String.valueOf(num));
                    numIndex++;

                    if(numVot.length() == 3){

                        fotoCandidato.setVisibility(View.VISIBLE);
                        nome.setVisibility(View.VISIBLE);
                        nomeCandidato.setVisibility(View.VISIBLE);
                        partido.setVisibility(View.VISIBLE);
                        partidoCandidato.setVisibility(View.VISIBLE);

                        boolean found = false;

                        for(Candidato c : candidatos){

                            if(c.getNumero().equals(numVot)){
                                nomeCandidato.setText(c.getNome());
                                partidoCandidato.setText(c.getPartido());
                                fotoCandidato.setImageBitmap(c.getFoto());
                                found = true;
                                break;
                            }
                        }

                        if(!found){
                            fotoCandidato.setVisibility(View.VISIBLE);
                            nome.setVisibility(View.VISIBLE);
                            nomeCandidato.setVisibility(View.VISIBLE);
                            partido.setVisibility(View.GONE);
                            partidoCandidato.setVisibility(View.GONE);

                            nomeCandidato.setText("NULO");
                            fotoCandidato.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face));
                        }
                    }
                }
                break;
        }
    }

    public void loadCandidatos(){

        //Presidentes
        Candidato diegoOriginal = new Candidato();
        diegoOriginal.setNome("Diego Colombo Dias");
        diegoOriginal.setNumero("13");
        diegoOriginal.setPartido("Partido da Realidade Virtual");
        diegoOriginal.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_original));

        Candidato diegoOtaku = new Candidato();
        diegoOtaku.setNome("Paulo Yagami Elric");
        diegoOtaku.setNumero("25");
        diegoOtaku.setPartido("Partido dos Otakus Unidos");
        diegoOtaku.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_otaku));

        Candidato diegoFunkeiro = new Candidato();
        diegoFunkeiro.setNome("Jhonin da Hornet");
        diegoFunkeiro.setNumero("17");
        diegoFunkeiro.setPartido("Partido do Funk Oestentação");
        diegoFunkeiro.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_funkeiro));

        //Governador
        Candidato diegoAssassino = new Candidato();
        diegoAssassino.setNome("Josafá Matador");
        diegoAssassino.setNumero("171");
        diegoAssassino.setPartido("Partido de Cléber");
        diegoAssassino.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_assassino));

        Candidato diegoYoutuber = new Candidato();
        diegoYoutuber.setNome("Joaquim Gameplays");
        diegoYoutuber.setNumero("555");
        diegoYoutuber.setPartido("Partido Digital Influencer do Brasil");
        diegoYoutuber.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_youtuber));

        Candidato diegoSupreme = new Candidato();
        diegoSupreme.setNome("Theo Albuquerque Bragança");
        diegoSupreme.setNumero("999");
        diegoSupreme.setPartido("Partido do Outfit do Brasil");
        diegoSupreme.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_supreme));

        candidatos.add(diegoOriginal);
        candidatos.add(diegoOtaku);
        candidatos.add(diegoFunkeiro);
        candidatos.add(diegoAssassino);
        candidatos.add(diegoYoutuber);
        candidatos.add(diegoSupreme);
    }
}
