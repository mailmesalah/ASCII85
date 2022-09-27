/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascii85;

import com.idataconnect.lib.ascii85codec.Ascii85InputStream;
import com.idataconnect.lib.ascii85codec.Ascii85OutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Sely
 */
public class ASCII85 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Please type string to convert to ASCII85");
        String readLine = new BufferedReader(new InputStreamReader(System.in)).readLine();
        byte[] encoded = convertStringToASCII85(readLine);
        System.out.println("Converted Back "+convertASCII85ToString(encoded));
             
    }
    
    private static byte[] convertStringToASCII85(String data) throws UnsupportedEncodingException, IOException{
        byte[] toEncode = data.getBytes();
        ByteArrayOutputStream boas = new ByteArrayOutputStream(1024);
        Ascii85OutputStream os = new Ascii85OutputStream(boas);
        os.write(toEncode);
        os.close();
        boas.close();

        byte[] encodedBytes = boas.toByteArray();

        System.out.println("Encoded "+new String(encodedBytes, "US-ASCII"));
        //return new String(encodedBytes, "US-ASCII");
        return encodedBytes;        
    }
    
    private static String convertASCII85ToString(byte[] encodedBytes) throws IOException{
        ByteArrayOutputStream boas = new ByteArrayOutputStream(1024);
        Ascii85InputStream is = new Ascii85InputStream(
                new ByteArrayInputStream(encodedBytes));
        boas.reset();
        int b;
        while ((b = is.read()) != -1)
            boas.write(b);

        boas.close();
        is.close();
        
        return new String(boas.toByteArray());
    }
}
