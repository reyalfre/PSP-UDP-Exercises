package ejercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EmisorDatagram implements Runnable {
    private String mensajeEnviar;
    private int numeroEmisor;
    private int puertoDestino;

    public EmisorDatagram(String mensajeEnviar, int numeroEmisor, int puertoDestino) {
        this.mensajeEnviar = mensajeEnviar;
        this.numeroEmisor = numeroEmisor;
        this.puertoDestino = puertoDestino;
    }

    @Override
    public void run() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress addr = InetAddress.getByName("localhost");

            // Enviar mensaje al receptor
            String mensajeCompleto = numeroEmisor + " " + mensajeEnviar;
            DatagramPacket datagrama = new DatagramPacket(mensajeCompleto.getBytes(), mensajeCompleto.length(), addr, puertoDestino);
            datagramSocket.send(datagrama);

            // Esperar la respuesta del receptor
            byte[] buffer = new byte[100];
            DatagramPacket respuestaDatagrama = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(respuestaDatagrama);

            String respuesta = new String(respuestaDatagrama.getData(), 0, respuestaDatagrama.getLength());
            System.out.println("EMISOR " + numeroEmisor + ": Respuesta del receptor: " + respuesta);

            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
