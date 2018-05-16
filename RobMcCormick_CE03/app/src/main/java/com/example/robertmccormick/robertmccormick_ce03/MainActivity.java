// Robert McCormick
// JAV1
// RobMcCormick_CE03 word length app

package com.example.robertmccormick.robertmccormick_ce03;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import java.util.List;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button add,view;
    EditText editText;
    TextView averageNum, mediumNum;
    NumberPicker numberPicker;
    double counter=0;
    String catchWord = "";

    ArrayList<String> word=new ArrayList<String>();
    List valid = Arrays.asList(word);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        add = (Button) findViewById(R.id.add);
        view = (Button) findViewById(R.id.view);
        editText = (EditText) findViewById(R.id.edittext);
        averageNum = (TextView) findViewById(R.id.avgnumber);
        mediumNum = (TextView) findViewById(R.id.mednumber);
        numberPicker = (NumberPicker) findViewById(R.id.numberpicker);




        //String newValueNotInList="newValue";

        add.setOnClickListener(new View.OnClickListener() {

            String string = editText.getText().toString();



            @Override
            public void onClick(View view) {

                try {

                    if (editText.getText().toString().trim().length() == 0) {



                       Toast toast =  Toast.makeText(MainActivity.this,"Have you left the field empty?",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, 250, 400);
                        toast.show();

                        string = editText.getText().toString();


                    } else {

                        catchWord = editText.getText().toString();

                        if (editText.getText() != null) {

                               //valid.contains(editText.getText().toString())) == false

                            if (word.contains(editText.getText().toString())== false) {


                                word.add(editText.getText().toString() );

                                numberPicker.setMinValue(0);
                                numberPicker.setMaxValue(word.size() - 1);

                            editText.setText("");

                            for (int i = 0; i < word.size(); i++) {

                                String singleword = word.get(i);
                                counter += singleword.length();


                            }

                            DecimalFormat decimalFormat = new DecimalFormat("#.##");

                            double totalsize = word.size();
                            double avg = counter / totalsize;

                            averageNum.setText(decimalFormat.format(avg));
                            counter = 0;


                            int mediumSize = word.size() / 2;
                            if (word.size() % 2d == 0) {

                                String a = word.get(mediumSize);
                                String b = word.get(mediumSize - 1);


                                double evenMedium = (double) a.length() + (double) b.length() / 2;
                                mediumNum.setText(decimalFormat.format(evenMedium));
                            } else {
                                String mediumString = word.get(mediumSize);
                                double mediumLength = mediumString.length();


                                mediumNum.setText(decimalFormat.format(mediumLength));
                            }

                        }
                        else {

                                Toast toast = Toast.makeText(MainActivity.this,"I already have this word.",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 400);
                                toast.show();
                            }


                        }

                    }



                }
                catch (Exception e) {

                    Toast.makeText(MainActivity.this,"Can you enter a word?",Toast.LENGTH_LONG).show();
                }

            }
        });


        view.setOnClickListener(new View.OnClickListener() {

            String string = editText.getText().toString();
            @Override
            public void onClick(View view) {

                if ( word.isEmpty()) {

                    Toast toast = Toast.makeText(MainActivity.this,"Have you left the field empty?",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 400);
                    toast.show();

                    string = editText.getText().toString();


                } else {

                    final int gettedvalue = numberPicker.getValue();
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage(word.get(gettedvalue));
                    alertDialogBuilder.setTitle("Selected Word:");

                    alertDialogBuilder.setPositiveButton("Close",

                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {


                                }
                            });


                    alertDialogBuilder.setNegativeButton("Remove Word",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    word.remove(gettedvalue);

                                     try {   

                                    numberPicker.setMaxValue(word.size() - 1);


                                        for (int i = 0; i < word.size(); i++) {

                                            String singleword = word.get(i);
                                            counter += singleword.length();


                                        }

                                        DecimalFormat decimalFormat = new DecimalFormat("#.##");

                                        double totalsize = word.size();
                                        double avg = counter / totalsize;

                                        averageNum.setText(decimalFormat.format(avg));
                                        counter = 0;


                                        int mediumSize = word.size() / 2;
                                        if (word.size() % 2d == 0) {

                                            String a = word.get(mediumSize);
                                            String b = word.get(mediumSize - 1);


                                            double evenMedium = (double) a.length() + (double) b.length() / 2;
                                            mediumNum.setText(decimalFormat.format(evenMedium));
                                        } else {
                                            String mediumString = word.get(mediumSize);
                                            double mediumLength = mediumString.length();


                                            mediumNum.setText(decimalFormat.format(mediumLength));
                                        }
                                    } catch (Exception e) {
                                        Toast toast = Toast.makeText(MainActivity.this, "Now I'm Empty", Toast.LENGTH_LONG);
                                         toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 400);
                                         toast.show();
                                    }
                                }
                            });

                    final AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }

            }
        });




    }
}