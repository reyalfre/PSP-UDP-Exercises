package ejercicio2;

import java.util.Random;

public class PruebaEmisores {
    public static void main(String[] args) {
        int numeroEmisores = 50 ;
        int puertoDestino = 5555;

        for (int i = 0; i < numeroEmisores; i++) {
            String mensajeEnviar = generarAleatorio();
            EmisorDatagram emisor = new EmisorDatagram(mensajeEnviar, i + 1, puertoDestino);
            Thread hiloEmisor = new Thread(emisor);
            hiloEmisor.start();
        }
    }

    private static String generarAleatorio() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
