package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
    
/**
 *
 * @author mari
 */
public class Servidor {
    public static void main(String[] args) {

        final int port = 5000;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Servidor iniciado");
            
            //Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(port);

            
            while (true) {
                
                //Preparo la respuesta
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                
                //Recibo el datagrama
                socketUDP.receive(peticion);
                System.out.println("");
                
                //Muestro el mensaje
                String mensaje = new String(peticion.getData());
                System.out.println("Recibo peticion del cliente:");
                System.out.println(mensaje);
                System.out.println("");

                //Obtengo puerto y direccion de origen
                int portCliente = peticion.getPort();
                InetAddress address = peticion.getAddress();

                mensaje = "Hola, soy el servidor";
                buffer = mensaje.getBytes();

                //creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, portCliente);

                //Envio la información
                System.out.println("Constesto al cliente...");
                System.out.println("------------------------");
                socketUDP.send(respuesta);
                
            }

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
