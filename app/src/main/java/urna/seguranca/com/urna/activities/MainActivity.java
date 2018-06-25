package urna.seguranca.com.urna.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import urna.seguranca.com.urna.R;
import urna.seguranca.com.urna.models.Candidato;

public class MainActivity extends AppCompatActivity {

    String endereco = "192.168.1.101";
    int porta = 8002;

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
    LinearLayout caixa4;
    LinearLayout caixa5;
    LinearLayout instrucoes;

    TextView numeros[] = new TextView[5];

//    LinearLayout caixa4;
//    LinearLayout caixa5;
    TextView numero;
    TextView nome;
    TextView nomeCandidato;
    TextView partido;
    TextView partidoCandidato;

    Vibrator v;

    int numIndex = 0;
    String numVot = "";

    int tipoCargoAtual = 0;

    int voteCount = 0;
    String votePresTmp = "";
    String voteGovTmp = "";
    String votePresTmpName = "";
    String voteGovTmpName = "";

    Socket client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadCandidatos();
        new ConectarAoServidor().execute();

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
        instrucoes = findViewById(R.id.instrucoes);
        caixa4 = findViewById(R.id.caixa4);
        caixa5 = findViewById(R.id.caixa5);

        numeros[0] = findViewById(R.id.numero1);
        numeros[1] = findViewById(R.id.numero2);
        numeros[2] = findViewById(R.id.numero3);
        numeros[3] = findViewById(R.id.numero4);
        numeros[4] = findViewById(R.id.numero5);

        numero = findViewById(R.id.numero);
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

                if(numVot.length() == 0){
                    numero.setVisibility(View.GONE);
                    fotoCandidato.setVisibility(View.GONE);
                    nome.setVisibility(View.GONE);
                    nomeCandidato.setVisibility(View.VISIBLE);
                    partido.setVisibility(View.GONE);
                    partidoCandidato.setVisibility(View.GONE);

                    fotoCandidato.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face));

                    nomeCandidato.setText("VOTO EM BRANCO");

                    caixa1.setVisibility(View.GONE);
                    caixa2.setVisibility(View.GONE);
                    caixa3.setVisibility(View.GONE);
                    caixa4.setVisibility(View.GONE);
                    caixa5.setVisibility(View.GONE);



                    instrucoes.setVisibility(View.VISIBLE);

                    for(TextView t : numeros){
                        t.setText("0");
                    }

                    if(tipoCargoAtual == 0){
                        numVot = "00";
                    }else
                    if(tipoCargoAtual == 1){
                        numVot = "00000";
                    }
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

                if(tipoCargoAtual == 0){
                    caixa1.setVisibility(View.VISIBLE);
                    caixa2.setVisibility(View.VISIBLE);
                }else{
                    caixa1.setVisibility(View.VISIBLE);
                    caixa2.setVisibility(View.VISIBLE);
                    caixa3.setVisibility(View.VISIBLE);
                    caixa4.setVisibility(View.VISIBLE);
                    caixa5.setVisibility(View.VISIBLE);

                }

                numero.setVisibility(View.VISIBLE);
                fotoCandidato.setVisibility(View.GONE);
                nome.setVisibility(View.GONE);
                nomeCandidato.setVisibility(View.GONE);
                partido.setVisibility(View.GONE);
                partidoCandidato.setVisibility(View.GONE);
                instrucoes.setVisibility(View.GONE);
            }
        });

        btcorrige.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Configurar Urna");
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.config, null);

                final EditText eip = v.findViewById(R.id.ip);
                final EditText eporta = v.findViewById(R.id.porta);
                builder.setView(v);

                eip.setText(endereco);
                eporta.setText(String.valueOf(porta));

                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        endereco = eip.getText().toString();
                        porta = Integer.parseInt(eporta.getText().toString());
                    }
                }).setNegativeButton("Cancelar", null);

                builder.create().show();

                return true;
            }
        });

        btconfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(100);

                if(tipoCargoAtual == 0){
                    if(numVot.length() == 2){

                        boolean found = false;

                        Candidato tmp = new Candidato();

                        for(Candidato c : candidatos){
                            if(c.getNumero().equals(numVot)){
                                c.incVoto();
                                found = true;
                                tmp.setNome(c.getNome());
                                break;
                            }
                        }


                        if(!found){
                            if(numVot.equals("00") && nomeCandidato.getText().toString().equals("VOTO EM BRANCO")){
                                tmp.setNome("BRANCO");
                                votosBrancos++;
                            }else{
                                tmp.setNome("NULO");
                                votosNulos++;
                            }
                        }


                        caixa1.setVisibility(View.VISIBLE);
                        caixa2.setVisibility(View.VISIBLE);
                        caixa3.setVisibility(View.VISIBLE);
                        caixa4.setVisibility(View.VISIBLE);
                        caixa5.setVisibility(View.VISIBLE);

                        votePresTmp = numVot;
                        votePresTmpName = tmp.getNome();

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
                        instrucoes.setVisibility(View.GONE);

                        tipoCargo.setText("Deputado Estadual");


                        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.confirma);
                        mp.start();
                    }
                }else
                if(tipoCargoAtual == 1){
                    if(numVot.length() == 5){

                        boolean found = false;

                        Candidato tmp = new Candidato();

                        for(Candidato c : candidatos){
                            if(c.getNumero().equals(numVot)){
                                c.incVoto();
                                found = true;
                                tmp.setNome(c.getNome());
                                break;
                            }
                        }

                        if(!found){
                            if(numVot.equals("00000") && nomeCandidato.getText().toString().equals("VOTO EM BRANCO")){
                                votosBrancos++;
                                tmp.setNome("BRANCO");
                            }else{
                                tmp.setNome("NULO");
                                votosNulos++;
                            }
                        }

                        caixa1.setVisibility(View.VISIBLE);
                        caixa2.setVisibility(View.VISIBLE);
                        caixa3.setVisibility(View.GONE);
                        caixa4.setVisibility(View.GONE);
                        caixa5.setVisibility(View.GONE);

                        voteGovTmp = numVot;
                        voteGovTmpName = tmp.getNome();

                        numIndex = 0;
                        tipoCargoAtual = 0;
                        numVot = "";
                        for(TextView t : numeros){
                            t.setText("");
                        }

                        numero.setVisibility(View.VISIBLE);
                        fotoCandidato.setVisibility(View.GONE);
                        nome.setVisibility(View.GONE);
                        nomeCandidato.setVisibility(View.GONE);
                        partido.setVisibility(View.GONE);
                        partidoCandidato.setVisibility(View.GONE);
                        instrucoes.setVisibility(View.GONE);

                        tipoCargo.setText("Presidente");

                        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.confirma);
                        mp.start();

                        voteCount++;

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.fim, null);
                        builder.setView(v);
                        builder.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final Bitmap boleta = geraBoleta(votePresTmp, voteGovTmp, votePresTmpName, voteGovTmpName);

                                AlertDialog.Builder boletaBuilder = new AlertDialog.Builder(MainActivity.this);
                                LayoutInflater inflaterBoleta = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                View boletaView = inflaterBoleta.inflate(R.layout.boleta, null);

                                ImageView imgBoleta = boletaView.findViewById(R.id.boleta);
                                imgBoleta.setImageBitmap(boleta);

                                boletaBuilder.setView(boletaView);
                                boletaBuilder.setTitle("Sua boleta está correta?");

                                boletaBuilder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int j) {
                                        new EnviarBoleta().execute(boleta);
                                    }
                                }).setNegativeButton("NÃO", null);

                                boletaBuilder.create().show();
                            }
                        });

                        builder.create().show();
                    }
                }
            }
        });

        btconfirma.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new ConectarAoServidor().execute();
                return true;
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
                        nome.setText("NOME");
                        nomeCandidato.setVisibility(View.VISIBLE);
                        partido.setVisibility(View.VISIBLE);
                        partidoCandidato.setVisibility(View.VISIBLE);
                        instrucoes.setVisibility(View.VISIBLE);

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
                            fotoCandidato.setVisibility(View.GONE);
                            nome.setVisibility(View.VISIBLE);
                            nome.setText("NÚMERO ERRADO");
                            nomeCandidato.setVisibility(View.VISIBLE);
                            partido.setVisibility(View.GONE);
                            partidoCandidato.setVisibility(View.GONE);

                            nomeCandidato.setText("VOTO NULO");
                            fotoCandidato.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face));
                        }
                    }
                }

                break;

            case 1:
                if(numVot.length() < 5){
                    numVot += String.valueOf(num);
                    numeros[numIndex].setText(String.valueOf(num));
                    numIndex++;

                    if(numVot.length() == 5){

                        fotoCandidato.setVisibility(View.VISIBLE);
                        nome.setVisibility(View.VISIBLE);
                        nome.setText("NOME");
                        nomeCandidato.setVisibility(View.VISIBLE);
                        partido.setVisibility(View.VISIBLE);
                        partidoCandidato.setVisibility(View.VISIBLE);
                        instrucoes.setVisibility(View.VISIBLE);

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
                            fotoCandidato.setVisibility(View.GONE);
                            nome.setVisibility(View.VISIBLE);
                            nome.setText("NÚMERO ERRADO");
                            nomeCandidato.setVisibility(View.VISIBLE);
                            partido.setVisibility(View.GONE);
                            partidoCandidato.setVisibility(View.GONE);

                            nomeCandidato.setText("VOTO NULO");
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
        diegoOriginal.setNumero("01");
        diegoOriginal.setPartido("Partido da Realidade Virtual");
        diegoOriginal.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_original));

        Candidato diegoOtaku = new Candidato();
        diegoOtaku.setNome("Paulo Yagami Elric");
        diegoOtaku.setNumero("02");
        diegoOtaku.setPartido("Partido dos Otakus Unidos");
        diegoOtaku.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_otaku));

        Candidato diegoFunkeiro = new Candidato();
        diegoFunkeiro.setNome("Jhonin da Hornet");
        diegoFunkeiro.setNumero("03");
        diegoFunkeiro.setPartido("Partido do Funk Oestentação");
        diegoFunkeiro.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_funkeiro));

        //Governador
        Candidato diegoAssassino = new Candidato();
        diegoAssassino.setNome("Josafá Matador");
        diegoAssassino.setNumero("00004");
        diegoAssassino.setPartido("Partido de Cléber");
        diegoAssassino.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_assassino));

        Candidato diegoYoutuber = new Candidato();
        diegoYoutuber.setNome("Joaquim Gameplays");
        diegoYoutuber.setNumero("00005");
        diegoYoutuber.setPartido("Partido Digital Influencer do Brasil");
        diegoYoutuber.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_youtuber));

        Candidato diegoSupreme = new Candidato();
        diegoSupreme.setNome("Theo Albuquerque Bragança");
        diegoSupreme.setNumero("00006");
        diegoSupreme.setPartido("Partido do Outfit do Brasil");
        diegoSupreme.setFoto(BitmapFactory.decodeResource(getResources(),R.drawable.diego_supreme));

        candidatos.add(diegoOriginal);
        candidatos.add(diegoOtaku);
        candidatos.add(diegoFunkeiro);
        candidatos.add(diegoAssassino);
        candidatos.add(diegoYoutuber);
        candidatos.add(diegoSupreme);
    }

    public Bitmap geraBoleta(String numPresidente, String numDeputado, String nomePres, String nomeGov){

        int numLinePresidente = 0;
        int numLineDeputado = 0;

        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(0, 0, 0));
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setStrokeWidth(10);
        // convert to bitmap:

        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.boleta);

        //Utils.matToBitmap(m, bm);
        Bitmap mutable = bm.copy(Bitmap.Config.ARGB_8888, true);
        // find the imageview and draw i

        Canvas c = new Canvas(mutable);

        if(!nomePres.equals("BRANCO")){
            for(int i = 0; i < numPresidente.length(); i++){

                if(numPresidente.charAt(i) ==  '0'){
                    float centroY = 866 + (numLinePresidente * 56);
                    float centroX = 234 + (9 * 130);
                    c.drawRect(centroX - 33, centroY - 15, centroX + 33, centroY + 15, myPaint);
                    numLinePresidente ++;

                }else{
                    float centroY = 866 + (numLinePresidente * 56);
                    float centroX = 234 + (((numPresidente.charAt(i) - '0') - 1) * 130);
                    c.drawRect(centroX - 33, centroY - 15, centroX + 33, centroY + 15, myPaint);
                    numLinePresidente ++;

                }
            }


            Paint textPresidentePaint = new Paint();
            textPresidentePaint.setColor(Color.rgb(0, 0, 0));
            textPresidentePaint.setTextAlign(Paint.Align.CENTER);
            textPresidentePaint.setTextSize(50);
            textPresidentePaint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
            textPresidentePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            c.drawText(numPresidente, 574 * 3, 310 * 3, textPresidentePaint );

            Bitmap bmPresidente = null;
            boolean found = false;
            for(Candidato ca : candidatos){
                if(ca.getNumero().equals(numPresidente)){
                    found = true;
                    bmPresidente = ca.getFoto();
                    break;
                }
            }

            if(!found){
                bmPresidente = BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face);
            }

            Rect src = new Rect(0,0,bmPresidente.getWidth()-1, bmPresidente.getHeight()-1);
            Rect dest = new Rect(540 * 3,239 * 3,605 * 3, 295 * 3);
            c.drawBitmap(bmPresidente, src, dest, null);

        }else{
            Paint textPresidentePaint = new Paint();
            textPresidentePaint.setColor(Color.rgb(0, 0, 0));
            textPresidentePaint.setTextAlign(Paint.Align.CENTER);
            textPresidentePaint.setTextSize(50);
            textPresidentePaint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
            textPresidentePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            c.drawText("BRANCO", 574 * 3, 310 * 3, textPresidentePaint );


            Bitmap bmPresidente = BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face);

            Rect src = new Rect(0,0,bmPresidente.getWidth()-1, bmPresidente.getHeight()-1);
            Rect dest = new Rect(540 * 3,239 * 3,605 * 3, 295 * 3);
            c.drawBitmap(bmPresidente, src, dest, null);
        }


        if(!nomeGov.equals("BRANCO")){
            for(int i = 0; i < numDeputado.length(); i++){

                if(numDeputado.charAt(i) ==  '0'){
                    float centroY = 1077 + (numLineDeputado * 52);
                    float centroX = 231 + (9 * 130.5f);
                    c.drawRect(centroX - 33, centroY - 15, centroX + 33, centroY + 15, myPaint);
                    numLineDeputado ++;

                }else{
                    float centroY = 1077 + (numLineDeputado * 52);
                    float centroX = 231 + (((numDeputado.charAt(i) - '0') - 1) * 130.5f);
                    c.drawRect(centroX - 33, centroY - 15, centroX + 33, centroY + 15, myPaint);
                    numLineDeputado ++;

                }

            }


            Paint textDeputadoPaint = new Paint();
            textDeputadoPaint.setColor(Color.rgb(0, 0, 0));
            textDeputadoPaint.setTextAlign(Paint.Align.CENTER);
            textDeputadoPaint.setTextSize(50);
            textDeputadoPaint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
            textDeputadoPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            c.drawText(numDeputado, 574 * 3, 418 * 3, textDeputadoPaint );

            Bitmap bmGovernador = null;
            boolean found = false;
            for(Candidato ca : candidatos){
                if(ca.getNumero().equals(numDeputado)){
                    found = true;
                    bmGovernador = ca.getFoto();
                    break;
                }
            }

            if(!found){
                bmGovernador = BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face);
            }

            Rect src = new Rect(0,0,bmGovernador.getWidth()-1, bmGovernador.getHeight()-1);
            Rect dest = new Rect(540 * 3,343 * 3,605 * 3, 399 * 3);
            c.drawBitmap(bmGovernador, src, dest, null);

        }else{
            Paint textDeputadoPaint = new Paint();
            textDeputadoPaint.setColor(Color.rgb(0, 0, 0));
            textDeputadoPaint.setTextAlign(Paint.Align.CENTER);
            textDeputadoPaint.setTextSize(50);
            textDeputadoPaint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
            textDeputadoPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            c.drawText("BRANCO", 574 * 3, 418 * 3, textDeputadoPaint );

            Bitmap bmGovernador = BitmapFactory.decodeResource(getResources(),R.drawable.deafault_face);

            Rect src = new Rect(0,0,bmGovernador.getWidth()-1, bmGovernador.getHeight()-1);
            Rect dest = new Rect(540 * 3,343 * 3,605 * 3, 399 * 3);
            c.drawBitmap(bmGovernador, src, dest, null);
        }

        return mutable;
    }


    public class ConectarAoServidor extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                client = new Socket(endereco, porta);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(!aBoolean){
                Toast.makeText(getBaseContext(), "Erro ao abrir comunicação", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class EnviarBoleta extends AsyncTask<Bitmap, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Bitmap... bitmaps) {

            boolean error = false;


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmaps[0].compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            int fileSize = byteArray.length;
            int chunks = fileSize / 1024;
            int rest = fileSize - (chunks * 1024);


            String fileName = "voto" + String.valueOf(voteCount) + ".jpg;-;-";
            fileName += StringUtils.repeat("0", 1024 - fileName.length());

            try {
                OutputStream out = client.getOutputStream();
                out.write(fileName.getBytes());
            } catch (IOException e) {
                error = true;
            }

            String fileSizeStr = String.valueOf(fileSize) + ";-;-";
            fileSizeStr += StringUtils.repeat("0", 1024 - fileSizeStr.length());

            try {
                OutputStream out = client.getOutputStream();
                out.write(fileSizeStr.getBytes());
            } catch (IOException e) {
                error = true;
            }

            for(int i = 0; i < chunks; i++){
                try {
                    OutputStream out = client.getOutputStream();
                    out.write(Arrays.copyOfRange(byteArray, (i * 1024), (i * 1024) + 1024));
                } catch (IOException e) {
                    error = true;
                }
            }

            if(rest > 0){
                try {
                    OutputStream out = client.getOutputStream();
                    out.write(Arrays.copyOfRange(byteArray, (chunks * 1024), (chunks * 1024) + rest));
                } catch (IOException e) {
                    error = true;
                }
            }

            return error;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

//            if(!aBoolean){
//                Toast.makeText(MainActivity.this, "Erro ao enviar boleta", Toast.LENGTH_SHORT).show();
//            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new EncerrarConexao().execute();
    }

    public class EncerrarConexao extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            String fileName = "EOFCLOSE;-;-";
            fileName += StringUtils.repeat("0", 1024 - fileName.length());

            try {
                OutputStream out = client.getOutputStream();
                out.write(fileName.getBytes());
            } catch (IOException e) {}

            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
