package manageyourhouse.myh_manageyourhouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;



public class ClientTCP {

    private Socket socket;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String HostAddr = null;
    private int Port = 0;

    public ClientTCP(String _HostAddr,int _Port) throws UnknownHostException, IOException{
        HostAddr = _HostAddr;
        Port = _Port;
        socket = new Socket(HostAddr,Port);
        in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());

    }
    public void SendSetStateLight(String piece) throws IOException {
        if (socket.isConnected()) {
            out.println(piece);
            out.flush();
        }
    }
    private void ReConnectTCP() throws UnknownHostException, IOException{
        socket.close();
        socket = new Socket(HostAddr,Port);
        in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }
}