import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class RegistradorCredenciales {
    //ESTA CLASE REALIZA EL REGISTRO DE CREDENCIALES
    //SOLICITA EL IDENTIFICADOR Y LA CONTRASEÑA DEL USUARIO,GENERA EL RESUMEN Y LO ALMACENA EN UN FICHERO
    //MUESTRA EL RESUMEN CONVERTIDO A FORMATO HEXADECIMAL
    private static final String ENCODING_TYPE = "UTF-8";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce identificador (email): ");
        String identificador = sc.nextLine();
        System.out.println("Introduce contraseña: ");
        String password = sc.nextLine();
        try{
            byte[] resumen = HASHManager.getDigest(password.getBytes(ENCODING_TYPE)); //GENERA RESUMEN
            Files.write(new File(identificador + ".credencial").toPath(), resumen); //ALMACENA EN FICHERO
            mostrarResumenHexadecimal(resumen);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        sc.close();
    }

    private static void mostrarResumenHexadecimal(byte[] resumen) {
        String resumenHexadecimal = String.format("%064x", new BigInteger(1, resumen));
        System.out.println(resumenHexadecimal);
    }
}
