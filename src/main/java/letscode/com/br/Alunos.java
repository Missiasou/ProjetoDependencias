package letscode.com.br;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Builder
@Setter
@Getter
@ToString

public class Alunos {
    private Double numero;
    private String nome;
    private String turma;

}
