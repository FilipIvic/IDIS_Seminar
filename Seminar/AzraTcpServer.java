import java.net.*;
import java.io.*;
import java.lang.String;
import java.util.List;
import java.util.Scanner;
import java.lang.StringBuffer;

public class AzraTcpServer 
{
	public static void main(String args[]) throws IOException 
	{
		ServerSocket s = new ServerSocket();
		s.bind(new InetSocketAddress ("kamis-stud01.fesb.hr", 50076));

		//System.out.println("Soket kreiran na portu 50076!");

		OutputStream output = null;
		DataOutputStream writer = null;
		InputStream input = null;
		BufferedReader reader = null;

		while(true){
			try 
			{
				System.out.println("Cekam na novu konekciju...");
				
				Socket socket = s.accept(); 
				
				System.out.println("Primljena konekcija...");


				output = socket.getOutputStream();
				writer = new DataOutputStream(output);
				input = socket.getInputStream();
				reader = new BufferedReader(new InputStreamReader(input));	
				
				//citamo poruku servera i spremamo ju u string line
				String line = reader.readLine();
				System.out.println("Primljeno: " + line);
				
				//string filename je naziv datoteke koju otvaramo
				String filename = line.replaceAll("\0", "");

				//deklariramo objekt af klase AzraFile unutar kojeg se nalazi metoda ta otvaranje fileova
				AzraFile af = new AzraFile();
				List<String> stihovi = af.procitajFile(filename);

				//deklariramo objekt sb klase StringBuffer unutar kojeg spremamo rijeci pjesama
				StringBuffer sb = new StringBuffer();
				for (String stih : stihovi) {
					sb.append(stih);
					sb.append(" #");
				}
				
				//saljemo cijeli StringBuffer sb klijentu
				writer.writeChars(sb.toString() + "\n");

				socket.close();
			
			} 
			catch (Exception e) 
			{
				String err = "Dogodila se iznimka: " + e.getMessage();
				System.out.println("ERROR!" + err);

			}
			reader.close();
			writer.close();
			output.close();
			input.close();
		}
	}
}