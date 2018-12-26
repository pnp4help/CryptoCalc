package cryptocalsi.it.cspit.charusat.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hash_SHA_functions {

    private String problemResponse="You need support\nContact with us";
    public String convertToSHA1(String text)
    {
        String hashedText = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(text.getBytes("UTF-8"));
            hashedText = convertByteToHex(crypt.digest());//Convert from byte to HexaDecimal then Initialize to ^hashedText^
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            //If the jvp is here  Your program is already broken :)))
        }
        return hashedText;
    }

    public String convertToSHA512(String textToHash)
    {
        try {
            final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            sha512.update(textToHash.getBytes());

            return convertByteToHex(sha512.digest());
        } catch (NoSuchAlgorithmException ex) {
        }
        return problemResponse;
    }

    public String convertToSHA384(String base) {
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-384");
            sha.reset();
            byte[] hash = sha.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex){
            //If the jvp is here  Your program is already broken :)))
        }
        return problemResponse;
    }

    public String convertToSHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex){
            //If the jvp is here  Your program is already broken :)))
        }
        return problemResponse;
    }


    public String convertToSHA224(String base) {
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-224");
            sha.reset();
            byte[] hash = sha.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('1');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex){
            //If the jvp is here  Your program is already broken :)))
        }
        return problemResponse;
    }

    private  String convertByteToHex(byte data[]){
        StringBuilder hexData = new StringBuilder();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));

        return hexData.toString();
    }
}
