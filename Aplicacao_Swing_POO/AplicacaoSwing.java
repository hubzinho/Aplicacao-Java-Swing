import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AplicacaoSwing {
    public static void main(String[] args) {
        CriarTabelas.inicializarBanco();
        new MenuPrincipal();
    }
}


class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título estilizado
        JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30)); // Fonte grande
        add(titulo, BorderLayout.NORTH);

        // Subtítulo
        JLabel subtitulo = new JLabel("Gerenciador de Alunos e Disciplinas", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.ITALIC, 18)); // Fonte menor e itálico
        add(subtitulo, BorderLayout.CENTER);

        // Painel para os botões
        JButton btnAlunos = new JButton("Alunos");
        JButton btnDisciplinas = new JButton("Disciplinas");

        btnAlunos.addActionListener(e -> {
            dispose();
            new TelaAlunos();
        });

        btnDisciplinas.addActionListener(e -> {
            dispose();
            new TelaDisciplinas();
        });

        JPanel botoesPanel = new JPanel(new FlowLayout());
        botoesPanel.add(btnAlunos);
        botoesPanel.add(btnDisciplinas);
        add(botoesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class TelaAlunos extends JFrame {
    public TelaAlunos() {
        setTitle("Cadastro de Alunos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título estilizado
        JLabel titulo = new JLabel("Cadastro de Alunos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        add(titulo, BorderLayout.NORTH);

        // Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Matrícula:"));
        JTextField matriculaField = new JTextField();
        formPanel.add(matriculaField);

        formPanel.add(new JLabel("Curso:"));
        JTextField cursoField = new JTextField();
        formPanel.add(cursoField);

        formPanel.add(new JLabel("Data de Nascimento:"));
        JTextField dataNascimentoField = new JTextField();
        formPanel.add(dataNascimentoField);

        formPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        formPanel.add(emailField);

        add(formPanel, BorderLayout.CENTER);

        // Botões
        JPanel botoesPanel = new JPanel(new FlowLayout());
        JButton salvarBtn = new JButton("Salvar");
        JButton voltarBtn = new JButton("Voltar");

        salvarBtn.addActionListener(e -> {

            String sql = "INSERT INTO alunos(nome, matricula, curso, data_nascimento, email) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = ConexaoBD.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nomeField.getText());
                pstmt.setString(2, matriculaField.getText());
                pstmt.setString(3, cursoField.getText());
                pstmt.setString(4, dataNascimentoField.getText());
                pstmt.setString(5, emailField.getText());
                pstmt.executeUpdate();
        
                JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar aluno: " + ex.getMessage());
            }
        

        });
        voltarBtn.addActionListener(e -> {
            dispose();
            new MenuPrincipal();
        });

        botoesPanel.add(salvarBtn);
        botoesPanel.add(voltarBtn);
        add(botoesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class TelaDisciplinas extends JFrame {
    public TelaDisciplinas() {
        setTitle("Cadastro de Disciplinas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título estilizado
        JLabel titulo = new JLabel("Cadastro de Disciplinas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        add(titulo, BorderLayout.NORTH);

        // Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.add(new JLabel("Nome da Disciplina:"));
        JTextField nomeDisciplinaField = new JTextField();
        formPanel.add(nomeDisciplinaField);

        formPanel.add(new JLabel("Código:"));
        JTextField codigoField = new JTextField();
        formPanel.add(codigoField);

        formPanel.add(new JLabel("Professor:"));
        JTextField professorField = new JTextField();
        formPanel.add(professorField);

        formPanel.add(new JLabel("Créditos:"));
        JTextField creditosField = new JTextField();
        formPanel.add(creditosField);

        formPanel.add(new JLabel("Semestre:"));
        JTextField semestreField = new JTextField();
        formPanel.add(semestreField);

        add(formPanel, BorderLayout.CENTER);

        // Botões
        JPanel botoesPanel = new JPanel(new FlowLayout());
        JButton salvarBtn = new JButton("Salvar");
        JButton voltarBtn = new JButton("Voltar");

        salvarBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Disciplina salva com sucesso!"));
        voltarBtn.addActionListener(e -> {
            dispose();
            new MenuPrincipal();
        });

        botoesPanel.add(salvarBtn);
        botoesPanel.add(voltarBtn);
        add(botoesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}