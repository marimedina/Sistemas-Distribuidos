package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mari
 */
public class Cliente {
    public static void main(String[] args) {

        //puerto del servidor
        final int portServidor = 5000;
        //buffer donde se almacenara los mensajes
        byte[] buffer = new byte[1024];

        try {
            
            InetAddress addressServidor = InetAddress.getByName("localhost");

            //Creo el socket de UDP
            DatagramSocket socketUDP = new DatagramSocket();

            String mensaje = "¡Hola, soy el cliente!";

            //Convierto el mensaje a bytes
            buffer = mensaje.getBytes();

            //Creo un datagrama
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, addressServidor, portServidor);

            //Lo envio con send
            System.out.println("Envío el datagrama ...");
            System.out.println("");
            socketUDP.send(pregunta);

            //Preparo la respuesta
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            //Recibo la respuesta
            socketUDP.receive(peticion);
            System.out.println("Recibo contestacion desde servidor:");

            //Muestro los dato
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);

            //cierro el socket
            socketUDP.close();

        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
