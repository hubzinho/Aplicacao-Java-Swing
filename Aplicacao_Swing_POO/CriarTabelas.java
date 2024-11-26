import java.sql.Connection;
import java.sql.Statement;

public class CriarTabelas {
    public static void inicializarBanco() {
        String tabelaAlunos = """
                CREATE TABLE IF NOT EXISTS alunos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    matricula TEXT NOT NULL UNIQUE,
                    curso TEXT,
                    data_nascimento TEXT,
                    email TEXT
                );
                """;

        String tabelaDisciplinas = """
                CREATE TABLE IF NOT EXISTS disciplinas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    codigo TEXT NOT NULL UNIQUE,
                    professor TEXT,
                    creditos INTEGER,
                    semestre TEXT
                );
                """;

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(tabelaAlunos);
            stmt.execute(tabelaDisciplinas);
            System.out.println("Tabelas criadas ou já existem.");
        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
