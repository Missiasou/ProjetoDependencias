package letscode.com.br;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;


@Builder
@Setter
@Getter
@ToString

public class Alunos implements Comparable<Alunos>{
    private String nome;
    private String turma;

    @Override
    public int compareTo(Alunos o) {
        return this.getNome().compareTo(o.nome);
    }


    public static class OrdenaPorNome implements Comparator<Alunos> {

        @Override
        public int compare(Alunos o1, Alunos o2) {
            return o1.getNome().compareTo(o2.getNome());
        }
    }



}
