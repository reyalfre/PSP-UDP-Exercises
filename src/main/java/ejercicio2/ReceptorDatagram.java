package ejercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceptorDatagram {
    public static void main(String[] args) throws InterruptedException {
        try {
            int portRecibir = 5555;
            System.out.println("RECEPTOR: *** Inicio ***");
            System.out.println("RECEPTOR: Creando DatagramSocket en puerto " + portRecibir);

            DatagramSocket datagramSocket = new DatagramSocket(portRecibir);

            System.out.println("RECEPTOR: Esperando mensajes...");

            while (true) {
                byte[] mensaje = new byte[100];
                DatagramPacket datagrama1 = new DatagramPacket(mensaje, 100);
                datagramSocket.receive(datagrama1);

                InetAddress direccionCliente = datagrama1.getAddress();
                int puertoCliente = datagrama1.getPort();

                // Procesar el mensaje y enviar la respuesta al cliente
                String mensajeRecibido = new String(datagrama1.getData(), 0, datagrama1.getLength());
                String respuesta = "ECHO " + mensajeRecibido;

                byte[] respuestaBytes = respuesta.getBytes();
                DatagramPacket respuestaDatagrama = new DatagramPacket(respuestaBytes, respuestaBytes.length, direccionCliente, puertoCliente);
                datagramSocket.send(respuestaDatagrama);

                System.out.println("RECEPTOR: Respuesta enviada al cliente");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

