package letscode.com.br;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TurmasAlunos2 {

    public static void main(String[] args) {
        String inputPath2 = "docs/TurmaJava.csv";
        String outputPath2 = "docs/TurmaJavaSaida.txt";


        LinkedHashSet<String> TurmaJava = lerArquivo2(inputPath2);
        TurmaJava.remove(TurmaJava.stream().findFirst().get());

        List<Alunos> alunos2 = new ArrayList<>();

        for(String line: TurmaJava){
            List<String> atrib = Arrays.stream(line.split(";")).collect(Collectors.toList());

            alunos2.add(Alunos.builder()
                    .numero(Double.valueOf(atrib.get(0)))
                    .nome(atrib.get(1))
                    .turma(atrib.get(2))
                    .build()
            );

        }

        alunos2.forEach(System.out::println);

        escreverArquivo2(alunos2, outputPath2);

    }


    private static void escreverArquivo2(List<Alunos> TurmaJava, String outputPath2){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath2))) {
            TurmaJava.forEach( item -> {
                        try {
                            writer.append(item + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static LinkedHashSet<String> lerArquivo2(String inputPath2) {
        LinkedHashSet<String> TurmaBancoDados = new LinkedHashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath2)); ){
            String linha = reader.readLine();

            while( linha != null ){
                TurmaBancoDados.add(linha);
                linha = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao buscar arquivo : " + inputPath2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TurmaBancoDados;
    }


}

