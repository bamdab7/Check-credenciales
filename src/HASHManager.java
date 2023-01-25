import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HASHManager {
    //CLASE ENCARGADA DE LAS OPERACIONES RELACIONADAS CON LOS RESUMENES
    private static final String ALGORITMO = "SHA-256";

    //OBTIENE EL RESUMEN DEL BLOQUE DE BYTES PROPORCIONADO
    public static byte[] getDigest(byte[] mensaje) throws NoSuchAlgorithmException {
            byte[] resumen= null;
            try{
                MessageDigest algoritmo = MessageDigest.getInstance(ALGORITMO);
                algoritmo.reset();
                algoritmo.update(mensaje);
                resumen =  algoritmo.digest();
            } catch (NoSuchAlgorithmException e) {
                throw e;
            }
            return resumen;
    }

    //COMPARA DOS ARRAY DE BYTES (RESUMENS) INDICANDO SI SON O NO IGUALES
    public static  boolean compararResumenes(byte[] resumen1,byte[]resumen2){
        boolean sonIguales;
        try{
            MessageDigest algoritmo = MessageDigest.getInstance(ALGORITMO);
            algoritmo.reset();
            sonIguales = algoritmo.isEqual(resumen1,resumen2);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return sonIguales;
    }
}
