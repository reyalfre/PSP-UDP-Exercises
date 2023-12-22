package ejercicio1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class EmisorDatagram {
    public static void main(String[] args) throws InterruptedException {
        try {
            int portEnviar = 5555;
            System.out.println("EMISOR: *** Inicio ***");

            System.out.println("EMISOR: Iniciamos el DatagramSocket cliente en cualquier puerto. Presiona tecla enter para empezar.");
            Scanner teclado = new Scanner(System.in);
            teclado.nextLine();

            DatagramSocket datagramSocket = new DatagramSocket();
            System.out.println("EMISOR: *** Introduzca mensaje a enviar... ***");
            String mensaje = teclado.nextLine();

            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("EMISOR: Enviando datagrama UDP al puerto " + portEnviar);

            DatagramPacket datagrama =
                    new DatagramPacket(mensaje.getBytes(),
                            mensaje.getBytes().length,
                            addr, portEnviar);
            datagramSocket.send(datagrama);

            System.out.println("EMISOR: Mensaje enviado");

            // Escuchar la respuesta del receptor
            byte[] buffer = new byte[100];
            DatagramPacket respuestaDatagrama = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(respuestaDatagrama);

            String respuesta = new String(respuestaDatagrama.getData(), 0, respuestaDatagrama.getLength());
            System.out.println("EMISOR: Respuesta del receptor: " + respuesta);

            System.out.println("EMISOR: No hay nada más que hacer aquí, cerrando el socket datagrama");
            datagramSocket.close();
            teclado.close();
            System.out.println("\nEMISOR: *** Terminado ***");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

