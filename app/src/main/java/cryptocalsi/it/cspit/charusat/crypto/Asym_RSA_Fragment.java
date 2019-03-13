package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Objects;

import javax.crypto.Cipher;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Asym_RSA_Fragment extends Fragment {

    private final static String RSA = "RSA";
    public static PublicKey uk;
    public static PrivateKey rk;
    EditText input_et;
    TextView output_tv;
    String copy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("RSA Cipher");
        return inflater.inflate(R.layout.asym_rsa,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        input_et=(EditText)view.findViewById(R.id.plaintext_input);
        output_tv=(TextView)view.findViewById(R.id.output);
        Button encryption=(Button)view.findViewById(R.id.button_encryption);
        try {
            generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(input_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {

                    try {
                        copy=encrypt(input_et.getText().toString());
                        output_tv.setText("CipherText : \n"+copy);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button decryption=(Button)view.findViewById(R.id.button_decryption);
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(input_et.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Please Enter Appropriate Value",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        copy=String.valueOf(decrypt(input_et.getText().toString()));
                        output_tv.setText("PlainText : \n"+copy);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        ImageView swap=(ImageView)view.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                input_et.setText(copy,TextView.BufferType.EDITABLE);
            }
        });

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }

    public static void generateKey() throws Exception {

        KeyPairGenerator gen = KeyPairGenerator.getInstance(RSA);

        gen.initialize(512, new SecureRandom());

        KeyPair keyPair = gen.generateKeyPair();

        uk = keyPair.getPublic();

        rk = keyPair.getPrivate();

    }



    private static byte[] encrypt(String text, PublicKey pubRSA)

            throws Exception {

        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.ENCRYPT_MODE, pubRSA);

        return cipher.doFinal(text.getBytes());

    }



    public final static String encrypt(String text) {

        try {

            return byte2hex(encrypt(text, uk));

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }



    public final static String decrypt(String data) {

        try {

            return new String(decrypt(hex2byte(data.getBytes())));

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }



    private static byte[] decrypt(byte[] src) throws Exception {

        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.DECRYPT_MODE, rk);

        return cipher.doFinal(src);

    }



    public static String byte2hex(byte[] b) {

        String hs = "";

        String stmp = "";

        for (int n = 0; n < b.length; n++) {

            stmp = Integer.toHexString(b[n] & 0xFF);

            if (stmp.length() == 1)

                hs += ("0" + stmp);

            else

                hs += stmp;

        }

        return hs.toUpperCase();

    }



    public static byte[] hex2byte(byte[] b) {

        if ((b.length % 2) != 0)

            throw new IllegalArgumentException("hello");



        byte[] b2 = new byte[b.length / 2];



        for (int n = 0; n < b.length; n += 2) {

            String item = new String(b, n, 2);

            b2[n / 2] = (byte) Integer.parseInt(item, 16);

        }

        return b2;

    }
}