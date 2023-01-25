import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ValidadorCredenciales {
    //ESTA CLASE VALIDA LAS CREDENCIALES
    //SOLICITA LA INFORMACION DEL IDENTIFICADOR Y DE LA CONTRASEÑA DE USUARIO
    //GENERA UN RESUMEN, RECUPERA EL ANTERIOR RESUMEN Y COMPARA SI AMBOS RESUMENES SON IGUALES
    //MUESTRA LOS DOS RESUMENES CONVERTIDOS A FORMATO HEXADECIMAL
    private static final String ENCODING_TYPE = "UTF-8";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce identificador (email): ");
        String identificador = sc.nextLine();
        System.out.println("Introduce contraseña: ");
        String password = sc.nextLine();
        try{
            byte[] resumen = HASHManager.getDigest(password.getBytes(ENCODING_TYPE));
            byte[] resumen_almacenado = Files.readAllBytes(new File( identificador + ".credencial").toPath());
            if(HASHManager.compararResumenes(resumen,resumen_almacenado)){
                System.out.println("Autorizado");
            }else {
                System.out.println("Error de validacion");
            }
            mostrarResumenHexadecimal(resumen);
            mostrarResumenHexadecimal(resumen_almacenado);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        sc.close();
    }

    private static void mostrarResumenHexadecimal(byte[] resumen) {
        String resumenHexadecimal = String.format("%064x", new BigInteger(1,resumen));
        System.out.println(resumenHexadecimal);
    }
}
