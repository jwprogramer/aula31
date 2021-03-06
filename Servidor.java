import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
	try {
		Scanner leDoFluxo = new Scanner(fluxo.getInputStream());
		while(leDoFluxo.hasNextLine()) {
			System.out.println(leDoFluxo.nextLine());
			PrintStream escrevenofluxo = new PrintStream(fluxo.getOutputStream());
			Scanner teclado = new Scanner(System.in);
			InputStream is = System.in;
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			if(br.ready()) {
				escrevenofluxo.println(br.readLine());                    
			}
			escrevenofluxo = null; 
		}
		leDoFluxo.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	private Socket fluxo;

	public void seConecta(){
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("se preparando...");
			fluxo = server.accept();
			System.out.println(fluxo.getInetAddress().getHostAddress()+" conectado!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Thread servidor = new Servidor();
		((Servidor) servidor).seConecta();
		servidor.start();
	}
}