package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {


    String display="";
    EditText inputText;
    TextView displayText;
    String currentOperator="";
    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_box);
        inputText.setText(display);
        displayText = findViewById(R.id.result_box);

        ImageButton deleteVar = findViewById(R.id.del_button);
        deleteVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNumber();
            }
        });
    }

    private void appendToLast(String str){
        this.inputText.getText().append(str);
    }

    public void onClickNumber(View v){
        Button b= (Button) v;
        display+=b.getText();
        appendToLast(display);
        display="";
    }

    public void onClickOperator(View v){
        Button b = (Button) v;
        display+=b.getText();
        if(endsWithOperator()){
            replace(display);
            currentOperator = b.getText().toString();
            display="";
        }
        else{
            appendToLast(display);
            currentOperator = b.getText().toString();
            display="";
        }
    }

    public void onClearButton(View v){
        inputText.getText().clear();
        displayText.setText("");
    }

    public void deleteNumber(){
        this.inputText.getText().delete(getInput().length()-1,getInput().length());
    }

    private String getInput(){
        return this.inputText.getText().toString();
    }

    private boolean endsWithOperator(){
        return getInput().endsWith("+") || getInput().endsWith("-")|| getInput().endsWith("/")|| getInput().endsWith("x");
    }

    private void replace(String str){
        inputText.getText().replace(getInput().length()-1,getInput().length(),str);
    }

    public void equalResult(View v){
        String input = getInput();

        if(!endsWithOperator()){
            if(input.contains("x"))
                input = input.replaceAll("x","*");

            Expression expression = new ExpressionBuilder(input).build();
            double result = expression.evaluate();
            displayText.setText(String.valueOf(result));
        }
        else
            displayText.setText("");

        System.out.println(result);
    }



}
