package com.example.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView result,solution;
    Button buttonC,buttonopenbraket,buttonclosebraket,buttondivide,buttonmultipli,buttonplus,buttonsubstract,buttonequal;
    Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    Button buttonbackwords,buttondot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result_TextView);
        solution = findViewById(R.id.solution_TextView);

        assignId(buttonC, R.id.button_clear);
        assignId(buttonopenbraket, R.id.openbraket);
        assignId(buttonclosebraket, R.id.closebraket);
        assignId(buttondivide, R.id.button_division);
        assignId(buttonplus, R.id.button_plus);
        assignId(buttonsubstract, R.id.button_substract);
        assignId(buttonequal, R.id.button_equal);
        assignId(buttonmultipli, R.id.buttonmultiply);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(button0, R.id.button_0);
        assignId(buttonbackwords, R.id.button_backword);
        assignId(buttondot, R.id.button_dot);
    }
    void assignId(Button btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String datatoCalculate = solution.getText().toString();
        if(buttonText.equals("C")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("B")){
            if(!result.equals("0")) {
                datatoCalculate = datatoCalculate.substring(0, datatoCalculate.length() - 1);
            }
            else{
                solution.setText("0");
            }

        }
        else{
            datatoCalculate = datatoCalculate+buttonText;
        }
        solution.setText(datatoCalculate);
        String finalResult = getResult(datatoCalculate);
        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            org.mozilla.javascript.Context context1 = org.mozilla.javascript.Context.enter();
            context1.setOptimizationLevel(-1);
            org.mozilla.javascript.Scriptable scriptable1 = context1.initStandardObjects();
            String finalResult = context1.evaluateString(scriptable1,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Err";
        }

    }


}