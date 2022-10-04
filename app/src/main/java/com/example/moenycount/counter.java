package com.example.moenycount;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class counter extends AppCompatActivity {

    private EditText hundred,fifty,twenty,ten,five,two,one;
    private EditText twentyfiveC,tenC,fiveC,oneC;
    private EditText total,keep;
    private Button add;
    private TextView shrrt,counted,remove;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        hundred = (EditText) findViewById(R.id.hundred);
        fifty = (EditText) findViewById(R.id.fifty);
        twenty = (EditText) findViewById(R.id.twenty);
        ten = (EditText) findViewById(R.id.ten);
        five = (EditText) findViewById(R.id.five);
        two = (EditText) findViewById(R.id.two);
        one = (EditText) findViewById(R.id.one);
        twentyfiveC = (EditText) findViewById(R.id.twentyfiveC);
        tenC = (EditText) findViewById(R.id.tenC);
        fiveC = (EditText) findViewById(R.id.fiveC);
        oneC = (EditText) findViewById(R.id.oneC);
        total = (EditText) findViewById(R.id.total);
        keep = (EditText) findViewById(R.id.keep);

        add = (Button) findViewById(R.id.Add);
        shrrt = (TextView) findViewById(R.id.missing);
        counted = (TextView) findViewById(R.id.counted);
        remove = (TextView) findViewById(R.id.take);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double hund = 100 * Integer.parseInt(hundred.getText().toString());
                double fift = 50 * Integer.parseInt(fifty.getText().toString());
                double twent =20 * Integer.parseInt(twenty.getText().toString());
                double tens = 10 * Integer.parseInt(ten.getText().toString());
                double fiv = 5 * Integer.parseInt(five.getText().toString());
                double twos = 2* Integer.parseInt(two.getText().toString());
                double ones = Integer.parseInt(one.getText().toString());
                double twenfive =  0.25 * Integer.parseInt(twentyfiveC.getText().toString());
                double tencc = 0.10 * Integer.parseInt(tenC.getText().toString());
                double fivc = 0.05 * Integer.parseInt(fiveC.getText().toString());
                double onecc = 0.01 *Integer.parseInt(oneC.getText().toString());
                double tot = Integer.parseInt(total.getText().toString());
                double kep = Integer.parseInt(keep.getText().toString());

                double [] values = {hund, fift,twent,tens,fiv,twos,ones,twenfive,tencc,fivc,onecc};
                double sum = 0;
                for(int i =0; i < values.length;i++){
                    sum = sum + values[i];
                }
                counted.setText("Counted: "+sum);
               double shrt = tot - sum;
               shrt = Math.round(shrt);
                shrrt.setText("Short: " + shrt);
                double rem = sum - kep;
                remove.setText("Remove: "+ rem);


            }
        });
    }
}