import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AzraFile {

	//metoda za otvaranje file-ova

	public List<String> procitajFile(String name) throws IOException {

		String dir = System.getProperty("user.dir");
		String fileName = dir + "/pjesme/" + name + ".txt";
		File file = new File(fileName);
		Path path = file.toPath();
		List<String> lines = Files.readAllLines(path);
		return lines;
	}

}
