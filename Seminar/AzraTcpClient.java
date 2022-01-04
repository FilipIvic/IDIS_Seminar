import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class AzraTcpClient // ime glavne klase je TcpClient
{
	public static void main(String args[]) throws IOException 
	{  	
		
		Socket s = new Socket("kamis-stud01.fesb.hr", 50076);
    
		//System.out.println("Klijent se spojio na port 50076!");
		System.out.println("\n");

		InputStream input = s.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    
		OutputStream output = s.getOutputStream(); 
		DataOutputStream writer = new DataOutputStream(output);

		try
		{
			if (args.length > 0) 
			{
				//deklariramo string izabranaPjesma u koju zapisujemo naziv unesene pjesme
				String izabranaPjesma = "";
			
				//parsiramo unos ( ukoliko korisnik unese vise razmaka npr.)
				for (String sp : args) {
					izabranaPjesma += (sp + " "); 
				}			
			
				//deklariramo string ovoSaljem u kojem razmake zamjenjujemo razmake s crticama
				//na takav nacin su zapisana imena datoteka iz kojih vadimo rijeci pjesama
				String ovoSaljem = izabranaPjesma.trim().replaceAll(" ", "-");;
				System.out.println("Sada saljem: " + ovoSaljem + "\n");

				//saljemo serveru string ovoSaljem
				writer.writeChars(ovoSaljem + "\n");

				//citamo odgovor servera
				String line = reader.readLine();

				System.out.println("Lyrics:\n");

				//ispisujemo odgovor servera, odnosno ispisujemo rijeci pjesme
				String[] pjesma = line.split("#");
				for (String sp : pjesma)
				{
					System.out.println(sp);
				}
			}else{
				System.out.println("### POKRETANJE ###\n");
				System.out.println("Pokrenite java AzraTcpServer.java prvo\n");
				System.out.println("Unesite ime pjesme iz IZBORNIKA kao argument\n");
				System.out.println("npr. java AzraTcpClient Uzas je moja furka\n");
				System.out.println("### POKRETANJE ###\n\n");

				System.out.println("***IZBORNIK***\n");
				System.out.println("Voljela me nije nijedna\n");
				System.out.println("Uradi nesto\n");
				System.out.println("Usne vrele visnje\n");
				System.out.println("Uzas je moja furka\n");
				System.out.println("Balkan\n");
				System.out.println("Proljece u decembru\n");
				System.out.println("***KRAJ***\n");

			}

		}
		catch (Exception e)

	  	{
			// uhvati neku nepoznatu iznimku (i nemoj srusiti program)
			String err = "Dogodila se iznimka: " + e.getMessage();
			System.out.println("ERROR!");
	  	}
	

		// na kraju zatvori konekciju i izadji
		reader.close();
		writer.close();
		input.close();
		output.close();
		s.close();
  	}
}