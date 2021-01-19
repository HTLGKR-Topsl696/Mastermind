package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<Guess> gameAdadpter;
    private GameState state;

    static private List<String> alphabet;
    static private int codeLength;
    static private boolean doubleAllowed;
    static private int guessRounds;
    static private char correctPositionSign;
    static private char correctCodeElementSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadConfiguration();

        state = new GameState();

        mListView = findViewById(R.id.myList);
        gameAdadpter = new ArrayAdapter<Guess>(this, android.R.layout.simple_list_item_1, state.getGuesses());
        mListView.setAdapter(gameAdadpter);

        mListView.setOnClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public void loadGameState(View v) {
        try {
            FileInputStream fis = openFileInput("game.state");
            ObjectInputStream ois = new ObjectInputStream(fis);
            state = (GameState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveGameState(View v) {
        try {
            FileOutputStream fos = openFileOutput("game.state", MODE_PRIVATE | MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSettings(View v) {
        List settings = new ArrayList<>();
        settings.add("Alphabet");
        settings.add(alphabet.stream().reduce((a, b) -> a + ", " + b).get());
        settings.add("codeLength");
        settings.add(codeLength);
        settings.add("doubleAllowed");
        settings.add(doubleAllowed);
        settings.add("guessRounds");
        settings.add(guessRounds);
        settings.add("correctPositionSign");
        settings.add(correctPositionSign);
        settings.add("correctCodeElementSign");
        settings.add(correctCodeElementSign);
        settings.add("START NEW GAME");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, settings);
        mListView.setAdapter(adapter);
    }

    private void loadConfiguration() {
        AssetManager assets = getAssets();
        try {
            InputStream is = assets.open("config.conf");
            Scanner sc = new Scanner(is);
            sc.useDelimiter("\n");
            while (sc.hasNext()) {
                String[] line = sc.next().trim().split("=");
                String key = line[0].trim();
                String value = line[1].trim();
                switch (key) {
                    case "alphabet":
                        alphabet = Arrays.stream(value.split(",")).map((x) -> x.trim()).collect(Collectors.toList());
                        break;
                    case "codeLength":
                        codeLength = Integer.parseInt(value);
                        break;
                    case "doubleAllowed":
                        doubleAllowed = Boolean.parseBoolean(value);
                        break;
                    case "guessRounds":
                        guessRounds = Integer.parseInt(value);
                        break;
                    case "correctPositionSign":
                        correctPositionSign = value.charAt(0);
                        break;
                    case "correctCodeElementSign":
                        correctCodeElementSign = value.charAt(0);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("sffd");
    }
}