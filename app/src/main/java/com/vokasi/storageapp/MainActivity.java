package com.vokasi.storageapp;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME="namafile.txt";
    public static final String PREFNAME="PREFNAME";
    TextView textBaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBaca=findViewById(R.id.textBaca);

    }
    public void buatFile(View view) {
        simpanIn();
    }
    public void ubahFile(View view) {
        ubahEx();
    }
    public void bacaFile(View view) {
        bacaIn();
    }
    public void hapusFile(View view) {
        hapusEx();
    }

    void simpanEx(){
        File path= Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES);
        String isiFile="Coba Isi Data File Eksternal";
        File file=new File(path.toString(),FILENAME);
        FileOutputStream outputStream=null;
        try {

            outputStream=new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ubahEx(){
        File path= Environment.getExternalStorageDirectory();
        String isiFile="Update Isi Data File Eksternal";
        File file=new File(path.toString(),FILENAME);
        FileOutputStream outputStream=null;
        try {

            outputStream=new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bacaEx(){
        File path= Environment.getExternalStorageDirectory();
        File file=new File(path.toString(),FILENAME);
        if(file.exists()){
            StringBuilder text=new StringBuilder();
            try {
                BufferedReader br=new
                        BufferedReader(new FileReader(file));
                String line=br.readLine();
                while (line!=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textBaca.setText(text.toString());
        }else {
            textBaca.setText("");
        }
    }

    void hapusEx(){
        File path= Environment.getExternalStorageDirectory();
        File file=new File(path.toString(),FILENAME);
        if(file.exists()){
            file.delete();
        }
    }

    void simpanIn(){
        String isiFile="Coba Isi Data File Internal";
        File path=getDir("NOTES",MODE_PRIVATE);
        File file=new File(path.toString(),FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void ubahIn(){
        String isiFile="Update Isi Data File Internal";
        File file=new File(getFilesDir(),FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void bacaIn(){
        File path=getDir("NOTES",MODE_PRIVATE);
        File file=new File(path.toString(),FILENAME);
        if(file.exists()){
            StringBuilder text=new StringBuilder();
            try {
                BufferedReader br=new
                        BufferedReader(new FileReader(file));
                String line=br.readLine();
                while (line!=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textBaca.setText(text.toString());
        }else {
            textBaca.setText("");
        }
    }

    void hapusIn(){
        File file=new File(getFilesDir(),FILENAME);
        if(file.exists()){
            file.delete();
        }
    }


    void simpanPref(){
        String isiFile="Coba Isi Data File";
        SharedPreferences sharedPreferences
                =getSharedPreferences(PREFNAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=
                sharedPreferences.edit();
        editor.putString(FILENAME,isiFile);
        editor.commit();
    }
    void ubahPref(){
        String isiFile="Update Isi Data File";
        SharedPreferences sharedPreferences
                =getSharedPreferences(PREFNAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=
                sharedPreferences.edit();
        editor.putString(FILENAME,isiFile);
        editor.commit();
    }
    void bacaPref(){
        SharedPreferences sharedPreferences
                =getSharedPreferences(PREFNAME,MODE_PRIVATE);
        if(sharedPreferences.contains(FILENAME)){
            String mytext=sharedPreferences
                    .getString(FILENAME,"");
            textBaca.setText(mytext);
        }else {
            textBaca.setText("");
        }
    }
    void hapusPref(){
        SharedPreferences sharedPreferences
                =getSharedPreferences(PREFNAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=
                sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
