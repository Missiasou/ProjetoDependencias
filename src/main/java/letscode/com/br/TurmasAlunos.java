package letscode.com.br;

import letscode.com.br.Alunos.OrdenaPorNome;
import org.apache.commons.io.output.FileWriterWithEncoding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class TurmasAlunos {
    public static void main(String[] args){

        String inputPath = "docs/TurmaBancoDados.csv";
        String inputPath2 = "docs/TurmaJava.csv";

        String outputPath = "docs/TurmasBancoDadosSaida.txt";
        String outputPath2 = "docs/TurmaJavaSaida.txt";
        String outputPath3 = "docs/TotalAlunos.txt";


        LinkedHashSet<String> TurmaBancoDados = lerArquivo(inputPath);
        TurmaBancoDados.remove(TurmaBancoDados.stream().findFirst().get());

        List<Alunos> alunos = new ArrayList<>();

        for(String line: TurmaBancoDados){
            List<String> atrib = Arrays.stream(line.split(";")).collect(Collectors.toList());

            alunos.add(Alunos.builder()
                    .nome(atrib.get(0))
                    .turma(atrib.get(1))
                    .build()
           );
        }

        Collections.sort(alunos, new OrdenaPorNome());
        System.out.println("-----Turma Banco de Dados------");
        alunos.forEach(System.out::println);
        System.out.printf("O Total de Alunos é: %s %n", alunos.size());
        System.out.println("");

        LinkedHashSet<String> TurmaJava = lerArquivo(inputPath2);
        TurmaJava.remove(TurmaJava.stream().findFirst().get());
        List<Alunos> alunos2 = new ArrayList<>();

        for(String line: TurmaJava){
            List<String> atrib2 = Arrays.stream(line.split(";")).collect(Collectors.toList());

            alunos2.add(Alunos.builder()
                    .nome(atrib2.get(0))
                    .turma(atrib2.get(1))
                    .build()
            );
        }

        Collections.sort(alunos2, new OrdenaPorNome());
        System.out.println("-----Turma Java------");
        alunos2.forEach(System.out::println);
        System.out.printf("O Total de Alunos é: %s %n", alunos2.size());



        SortedSet<Alunos> totalAlunos = new TreeSet<>();
        totalAlunos.addAll(alunos);
        totalAlunos.addAll(alunos2);


        System.out.println("");
        System.out.println("-------Total de Alunos--------");
        totalAlunos.forEach(System.out::println);
        System.out.printf("O Total de Alunos é: %s %n", totalAlunos.size());


        escreverArquivo(alunos, outputPath);
        escreverArquivo(alunos2, outputPath2);
        escreverArquivoTodosAlunos((SortedSet<Alunos>) totalAlunos, outputPath3);

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

    private static void escreverArquivoTodosAlunos(SortedSet<Alunos> totalAluno, String outputPath3){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath3))) {
            totalAluno.forEach( item -> {
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


}

