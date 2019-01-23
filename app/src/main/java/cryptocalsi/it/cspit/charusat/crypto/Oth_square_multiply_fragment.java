package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;

public class Oth_square_multiply_fragment extends Fragment {

  TextView temp;
  EditText num1_et,num2_et,num3_et;
  BigInteger num1,num3;
  Button calculate;
  int num2;

  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    getActionBar().setTitle("Square and Multiply");
    return inflater.inflate(R.layout.other_square_multiply,container,false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      InputFilterMinMax filter1 = new InputFilterMinMax("0", "1000000000") {};
      InputFilterMinMax filter2 = new InputFilterMinMax("0", "10000") {};

    temp=(TextView)view.findViewById(R.id.plaintext_label);
    temp=(TextView)view.findViewById(R.id.key_label);
    temp=(TextView)view.findViewById(R.id.output);
    num1_et=(EditText)view.findViewById(R.id.plaintext_input);
    num1_et.setFilters(new InputFilter[]{filter1});
    num1_et.setInputType(InputType.TYPE_CLASS_NUMBER);
    num2_et=(EditText)view.findViewById(R.id.key_input);
    num2_et.setFilters(new InputFilter[]{filter2});
    num3_et = (EditText)view.findViewById(R.id.mod_input);
    num3_et.setFilters(new InputFilter[]{filter1});
    calculate = (Button)view.findViewById(R.id.button_calculate);
    calculate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(num1_et.getText().toString().equals("")||num2_et.getText().toString().equals("")){
          Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
        }
        else {
          num1 = new BigInteger(num1_et.getText().toString());
          num2 = Integer.valueOf(num2_et.getText().toString());
          num3 = new BigInteger(num3_et.getText().toString());
          temp.setText("Answer : "+String.valueOf(squareAndMultiply(num1,num2).mod(num3)));
        }
      }
    });
  }

  private ActionBar getActionBar() {
    return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
  }

  BigInteger squareAndMultiply(BigInteger base, int power)
  {

    if (power==0)
      return BigInteger.valueOf(1);

    if (power == 1)
      return base;

    if (power%2==1)
    {
      return base.multiply(squareAndMultiply(base.multiply(base),(power-1)/2));
    }

    else
      return squareAndMultiply(base.multiply(base),power/2);
  }
}