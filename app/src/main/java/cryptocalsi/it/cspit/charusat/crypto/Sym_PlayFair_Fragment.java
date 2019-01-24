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

import java.util.Objects;

public class Sym_PlayFair_Fragment extends Fragment {

    EditText input_et,key_et;
    TextView output_tv;
    String copy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("PlayFair Cipher");
        return inflater.inflate(R.layout.sym_1_2_4_5_6_7_8,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        input_et=view.findViewById(R.id.plaintext_input);
        input_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        key_et=view.findViewById(R.id.key_input);
        key_et.setInputType(InputType.TYPE_CLASS_TEXT);
        key_et.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        output_tv=(TextView)view.findViewById(R.id.output);
        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!input_et.getText().toString().matches("[A-Z]+") || !key_et.getText().toString().matches("[A-Z]+")){
                    Toast.makeText(getContext(),"Only Alphabets are allowed",Toast.LENGTH_SHORT).show();
                }
                else {
                    PlayfairCipher x=new PlayfairCipher();
                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();
                    x.setKey(key_string);
                    x.KeyGen();
                    String Encryptedtext=x.encryptMessage(input_string);
                    copy=Encryptedtext;
                    output_tv.setText("CipherText : "+Encryptedtext);
                }
            }
        });

        Button decryption=(Button)view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_et.getText().toString().equals("")||key_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else if(!input_et.getText().toString().matches("[A-Z]+") || !key_et.getText().toString().matches("[A-Z]+")){
                    Toast.makeText(getContext(),"Only Alphabets are allowed",Toast.LENGTH_SHORT).show();
                }
                else {
                    PlayfairCipher x=new PlayfairCipher();
                    String input_string=input_et.getText().toString();
                    String key_string=key_et.getText().toString();
                    x.setKey(key_string);
                    x.KeyGen();
                    String decryptext=x.decryptMessage(input_string);
                    copy=decryptext;
                    output_tv.setText("PlainText : "+decryptext);
                }
            }
        });

        ImageView swap=(ImageView)view.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_et.setText(copy,TextView.BufferType.EDITABLE);

            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}

class PlayfairCipher
{
    private String KeyWord;
    private String Key;
    private char matrix_arr[][] = new char[5][5];
    private static char filler = 'X';
    private static boolean lastFiller = false;

    public void setKey(String k)
    {
        String K_adjust = new String();
        boolean flag = false;
        K_adjust = K_adjust + k.charAt(0);
        for (int i = 1; i < k.length(); i++)
        {
            for (int j = 0; j < K_adjust.length(); j++)
            {
                if (k.charAt(i) == K_adjust.charAt(j))
                {
                    flag = true;
                }
            }
            if (!flag)
                K_adjust = K_adjust + k.charAt(i);
            flag = false;
        }
        KeyWord = K_adjust;
    }

    public void KeyGen()
    {
        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++)
        {
            current = (char) (i + 65);
            if (current == 'J')
                continue;
            for (int j = 0; j < KeyWord.length(); j++)
            {
                if (current == KeyWord.charAt(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                Key = Key + current;
            flag = true;
        }
        System.out.println(Key);
        matrix();
    }

    private void matrix()
    {
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix_arr[i][j] = Key.charAt(counter);
                System.out.print(matrix_arr[i][j] + " ");
                counter++;
            }
        }
    }

    private String format(String old_text)
    {
        int i = 0;
        int len = 0;
        String text = new String();
        len = old_text.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (old_text.charAt(tmp) == 'J')
            {
                text = text + 'I';
            }
            else
                text = text + old_text.charAt(tmp);
        }
        for (i = 0; i < text.length(); i = i + 2)
        {
            try {
                if (text.charAt(i + 1) == text.charAt(i))
                {
                    text = text.substring(0, i + 1) + filler + text.substring(i + 1);
                }
            }
            catch (StringIndexOutOfBoundsException s) {
                text += filler;
                lastFiller = true;
            }
        }
        return text;
    }

    private String[] Divid2Pairs(String new_string)
    {
        String Original = format(new_string);
        int size = Original.length();
        if (size % 2 != 0)
        {
            size++;
            Original = Original + filler;
            PlayfairCipher.lastFiller = true;
        }
        String x[] = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++)
        {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }

    public int[] GetDiminsions(char letter)
    {
        int[] key = new int[2];
        if (letter == 'J')
            letter = 'I';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix_arr[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

    public String encryptMessage(String Source)
    {
        if(Source.length() % 2 != 0 && Source.charAt(Source.length() - 1) == 'X'){
            filler = 'Q';
        }

        String src_arr[] = Divid2Pairs(Source);
        String Code = new String();
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0])
            {
                if (part1[1] < 4)
                    part1[1]++;
                else
                    part1[1] = 0;
                if (part2[1] < 4)
                    part2[1]++;
                else
                    part2[1] = 0;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] < 4)
                    part1[0]++;
                else
                    part1[0] = 0;
                if (part2[0] < 4)
                    part2[0]++;
                else
                    part2[0] = 0;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Code;
    }

    public String decryptMessage(String Code)
    {
        String Original = new String();
        String src_arr[] = Divid2Pairs(Code);
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0])
            {
                if (part1[1] > 0)
                    part1[1]--;
                else
                    part1[1] = 4;
                if (part2[1] > 0)
                    part2[1]--;
                else
                    part2[1] = 4;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] > 0)
                    part1[0]--;
                else
                    part1[0] = 4;
                if (part2[0] > 0)
                    part2[0]--;
                else
                    part2[0] = 4;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Original = Original + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        for(int i = 1; i < Original.length(); i++){
            try{
                if((Original.charAt(i) == filler) && (Original.charAt(i-1) == Original.charAt(i+1))){
                    Original = Original.substring(0,i) + Original.substring(i+1, Original.length());
                }
            }
            catch(StringIndexOutOfBoundsException s){

            }
        }
        if(lastFiller){
            Original = Original.substring(0, Original.length() - 1);
            lastFiller = false;
        }
        return Original;
    }
}
