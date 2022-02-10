package letscode.com.br;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TurmasAlunos {

    public static void main(String[] args){

        String inputPath = "docs/TurmaBancoDados.csv";
        String outputPath = "docs/TurmasBancoDadosSaida.txt";


        LinkedHashSet<String> TurmaBancoDados = lerArquivo(inputPath);
        TurmaBancoDados.remove(TurmaBancoDados.stream().findFirst().get());

        List<Alunos> alunos = new ArrayList<>();

        for(String line: TurmaBancoDados){
            List<String> atrib = Arrays.stream(line.split(";")).collect(Collectors.toList());

            alunos.add(Alunos.builder()
                   .numero(Double.valueOf(atrib.get(0)))
                   .nome(atrib.get(1))
                   .turma(atrib.get(2))
                    .build()


           );

        }

        alunos.forEach(System.out::println);
        System.out.printf("O Total de Alunos é: %s", alunos.size());

        escreverArquivo(alunos, outputPath);

    }


    private static void escreverArquivo(List<Alunos> TurmaBancoDados, String outputPath){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            TurmaBancoDados.forEach( item -> {
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

    private static LinkedHashSet<String> lerArquivo(String inputPath) {
        LinkedHashSet<String> TurmaBancoDados = new LinkedHashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath)); ){
            String linha = reader.readLine();

            while( linha != null ){
                TurmaBancoDados.add(linha);
                linha = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao buscar arquivo : " + inputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TurmaBancoDados;
    }


}

