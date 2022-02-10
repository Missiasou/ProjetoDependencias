package letscode.com.br;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TurmaNIO2 {

    public static void main(String[] args) {

        Path path1 = Paths.get("docs/TurmaBancoDados.csv");
        System.out.println(Files.exists(path1));

    }

}
