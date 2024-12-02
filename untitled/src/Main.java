import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }

    static class LoginScreen extends JFrame {
        public LoginScreen() {
            setTitle("Login");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Login do Ifood", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setOpaque(true);
            title.setBackground(new Color(0, 102, 204));
            title.setForeground(Color.WHITE);
            title.setPreferredSize(new Dimension(400, 50));
            add(title, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

            JLabel userLabel = new JLabel("Usuário:");
            JTextField userField = new JTextField();

            JLabel passLabel = new JLabel("Senha:");
            JPasswordField passField = new JPasswordField();

            centerPanel.add(userLabel);
            centerPanel.add(userField);
            centerPanel.add(passLabel);
            centerPanel.add(passField);

            add(centerPanel, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel();
            JButton loginButton = new JButton("Entrar");
            loginButton.setBackground(new Color(0, 102, 204));
            loginButton.setForeground(Color.WHITE);
            loginButton.setFont(new Font("Arial", Font.BOLD, 16));

            bottomPanel.add(loginButton);
            add(bottomPanel, BorderLayout.SOUTH);

            loginButton.addActionListener(e -> {
                String user = userField.getText();
                String pass = new String(passField.getPassword());

                if ("admin".equals(user) && "1234".equals(pass)) {
                    JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new SistemaScreen().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    static class SistemaScreen extends JFrame {
        private Carrinho carrinho;
        private List<Restaurante> restaurantes;

        public SistemaScreen() {
            carrinho = new Carrinho();
            restaurantes = carregarRestaurantes();

            setTitle("Sistema Ifood");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Escolha um Restaurante", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setOpaque(true);
            title.setBackground(new Color(0, 102, 204));
            title.setForeground(Color.WHITE);
            title.setPreferredSize(new Dimension(600, 50));
            add(title, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new GridLayout(restaurantes.size(), 1, 10, 10));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

            for (Restaurante restaurante : restaurantes) {
                JButton restauranteButton = new JButton(restaurante.getNome());
                restauranteButton.setFont(new Font("Arial", Font.BOLD, 14));
                restauranteButton.addActionListener(e -> abrirMenuRestaurante(restaurante));
                centerPanel.add(restauranteButton);
            }

            add(centerPanel, BorderLayout.CENTER);
        }

        private List<Restaurante> carregarRestaurantes() {
            List<Restaurante> lista = new ArrayList<>();
            Endereco endereco = new Endereco(1, "Rua A", 123, "Centro", "Cidade", "Estado", "12345-678", "");
            lista.add(new Restaurante(1, "Fast Food", endereco, "111111111", "Fast Food", "10:00 - 22:00", true));
            lista.add(new Restaurante(2, "Pizzaria", endereco, "222222222", "Pizzaria", "11:00 - 23:00", true));
            lista.add(new Restaurante(3, "Comida Brasileira", endereco, "333333333", "Comida Brasileira", "9:00 - 21:00", true));
            return lista;
        }

        private void abrirMenuRestaurante(Restaurante restaurante) {
            JFrame menuFrame = new JFrame("Menu - " + restaurante.getNome());
            menuFrame.setSize(600, 500);
            menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            menuFrame.setLocationRelativeTo(null);
            menuFrame.setLayout(new BorderLayout());

            JLabel title = new JLabel("Escolha seus itens", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setOpaque(true);
            title.setBackground(new Color(0, 102, 204));
            title.setForeground(Color.WHITE);
            title.setPreferredSize(new Dimension(600, 50));
            menuFrame.add(title, BorderLayout.NORTH);

            List<Produto> produtos = carregarProdutos(restaurante);
            JPanel centerPanel = new JPanel(new GridLayout(produtos.size(), 1, 10, 10));

            for (Produto produto : produtos) {
                JButton produtoButton = new JButton(produto.getNome() + " - R$" + produto.getPreco());
                produtoButton.setFont(new Font("Arial", Font.PLAIN, 14));
                produtoButton.addActionListener(e -> {
                    carrinho.adicionarProduto(produto, 1);
                    JOptionPane.showMessageDialog(menuFrame, produto.getNome() + " adicionado ao carrinho!");
                });
                centerPanel.add(produtoButton);
            }

            menuFrame.add(centerPanel, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
            JButton finalizarButton = new JButton("Finalizar Pedido");

            finalizarButton.addActionListener(e -> abrirResumoPedido(menuFrame));

            bottomPanel.add(finalizarButton);
            menuFrame.add(bottomPanel, BorderLayout.SOUTH);
            menuFrame.setVisible(true);
        }

        private List<Produto> carregarProdutos(Restaurante restaurante) {
            List<Produto> produtos = new ArrayList<>();
            if (restaurante.getId() == 1) { // Fast Food
                produtos.add(new Produto(1, "Hamburguer", "Hamburguer com bacon", 25.0, null, restaurante));
                produtos.add(new Produto(2, "Batata Frita", "Porção de batata frita", 12.0, null, restaurante));
                produtos.add(new Produto(3, "Refrigerante", "Refrigerante lata", 5.0, null, restaurante));
            } else if (restaurante.getId() == 2) { // Pizzaria
                produtos.add(new Produto(4, "Pizza de Calabresa", "Pizza grande de calabresa", 35.0, null, restaurante));
                produtos.add(new Produto(5, "Pizza de Quatro Queijos", "Pizza grande de quatro queijos", 40.0, null, restaurante));
                produtos.add(new Produto(6, "Suco Natural", "Copo de suco natural", 8.0, null, restaurante));
            } else if (restaurante.getId() == 3) { // Comida Brasileira
                produtos.add(new Produto(7, "Feijoada", "Feijoada completa", 30.0, null, restaurante));
                produtos.add(new Produto(8, "Arroz e Feijão", "Prato simples de arroz, feijão e carne", 18.0, null, restaurante));
                produtos.add(new Produto(9, "Guaraná", "Guaraná de 1 litro", 10.0, null, restaurante));
            }
            return produtos;
        }

        private void abrirResumoPedido(JFrame menuFrame) {
            menuFrame.dispose();
            JFrame resumoFrame = new JFrame("Resumo do Pedido");
            resumoFrame.setSize(400, 300);
            resumoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            resumoFrame.setLocationRelativeTo(null);
            resumoFrame.setLayout(new BorderLayout());

            JTextArea resumoTextArea = new JTextArea();
            resumoTextArea.setEditable(false);
            resumoTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

            StringBuilder sb = new StringBuilder();
            sb.append("Resumo do Pedido:\n");
            for (PedidoProduto item : carrinho.getItens()) {
                sb.append("- ").append(item.getProduto().getNome())
                        .append(" (Qtd: ").append(item.getQuantidade()).append(")\n");
            }
            sb.append("\nTotal: R$").append(carrinho.calcularTotal());
            resumoTextArea.setText(sb.toString());

            resumoFrame.add(new JScrollPane(resumoTextArea), BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
            JButton enderecoButton = new JButton("Inserir Endereço");
            JButton cancelarButton = new JButton("Cancelar Pedido");

            enderecoButton.addActionListener(e -> abrirTelaEntrega(resumoFrame));
            cancelarButton.addActionListener(e -> resumoFrame.dispose());

            bottomPanel.add(enderecoButton);
            bottomPanel.add(cancelarButton);
            resumoFrame.add(bottomPanel, BorderLayout.SOUTH);
            resumoFrame.setVisible(true);
        }

        private void abrirTelaEntrega(JFrame resumoFrame) {
            resumoFrame.dispose();
            JFrame entregaFrame = new JFrame("Dados de Entrega");
            entregaFrame.setSize(400, 300);
            entregaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            entregaFrame.setLocationRelativeTo(null);
            entregaFrame.setLayout(new BorderLayout());

            JLabel title = new JLabel("Insira os Dados de Entrega", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 16));
            entregaFrame.add(title, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel cepLabel = new JLabel("CEP:");
            JTextField cepField = new JTextField();

            JLabel enderecoLabel = new JLabel("Endereço:");
            JTextField enderecoField = new JTextField();

            JLabel numeroLabel = new JLabel("Número:");
            JTextField numeroField = new JTextField();

            JLabel complementoLabel = new JLabel("Complemento:");
            JTextField complementoField = new JTextField();

            centerPanel.add(cepLabel);
            centerPanel.add(cepField);
            centerPanel.add(enderecoLabel);
            centerPanel.add(enderecoField);
            centerPanel.add(numeroLabel);
            centerPanel.add(numeroField);
            centerPanel.add(complementoLabel);
            centerPanel.add(complementoField);

            entregaFrame.add(centerPanel, BorderLayout.CENTER);

            JButton continuarButton = new JButton("Continuar para Pagamento");
            continuarButton.addActionListener(e -> {
                String cep = cepField.getText();
                String endereco = enderecoField.getText();
                String numero = numeroField.getText();
                String complemento = complementoField.getText();

                if (cep.isEmpty() || endereco.isEmpty() || numero.isEmpty()) {
                    JOptionPane.showMessageDialog(entregaFrame, "Por favor, preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                abrirOpcoesPagamento(entregaFrame, cep, endereco, numero, complemento);
            });

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(continuarButton);
            entregaFrame.add(bottomPanel, BorderLayout.SOUTH);
            entregaFrame.setVisible(true);
        }



        private void abrirOpcoesPagamento(JFrame entregaFrame, String cep, String endereco, String numero, String complemento) {
            entregaFrame.dispose();
            JFrame pagamentoFrame = new JFrame("Opções de Pagamento");
            pagamentoFrame.setSize(400, 300);
            pagamentoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pagamentoFrame.setLocationRelativeTo(null);
            pagamentoFrame.setLayout(new BorderLayout());

            JLabel title = new JLabel("Escolha a Forma de Pagamento", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 16));
            pagamentoFrame.add(title, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
            JRadioButton cartaoCredito = new JRadioButton("Cartão de Crédito");
            JRadioButton cartaoDebito = new JRadioButton("Cartão de Débito");
            JRadioButton dinheiro = new JRadioButton("Dinheiro");

            ButtonGroup group = new ButtonGroup();
            group.add(cartaoCredito);
            group.add(cartaoDebito);
            group.add(dinheiro);

            centerPanel.add(cartaoCredito);
            centerPanel.add(cartaoDebito);
            centerPanel.add(dinheiro);

            pagamentoFrame.add(centerPanel, BorderLayout.CENTER);

            JButton confirmarButton = new JButton("Finalizar Pedido");
            confirmarButton.addActionListener(e -> {
                String pagamentoSelecionado;
                if (cartaoCredito.isSelected()) {
                    pagamentoSelecionado = "Cartão de Crédito";
                } else if (cartaoDebito.isSelected()) {
                    pagamentoSelecionado = "Cartão de Débito";
                } else if (dinheiro.isSelected()) {
                    pagamentoSelecionado = "Dinheiro";
                } else {
                    JOptionPane.showMessageDialog(pagamentoFrame, "Selecione uma forma de pagamento!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                salvarPedidoComEndereco(pagamentoSelecionado, cep, endereco, numero, complemento);
                JOptionPane.showMessageDialog(pagamentoFrame, "Pedido finalizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                pagamentoFrame.dispose();
            });

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(confirmarButton);
            pagamentoFrame.add(bottomPnel, BorderLayout.SOUTH);
            pagamentoFrame.setVisible(true);
        }


        private void salvarPedidoComEndereco(String pagamentoSelecionado, String cep, String endereco, String numero, String complemento) {
            Path caminhoArquivo = Path.of("pedidos.txt");
            StringBuilder conteudoPedido = new StringBuilder();

            conteudoPedido.append("Pedido Realizado:\n");
            for (PedidoProduto item : carrinho.getItens()) {
                conteudoPedido.append("- ").append(item.getProduto().getNome())
                        .append(" (Qtd: ").append(item.getQuantidade()).append(")\n");
            }
            conteudoPedido.append("Total: R$").append(carrinho.calcularTotal()).append("\n");
            conteudoPedido.append("Forma de Pagamento: ").append(pagamentoSelecionado).append("\n");
            conteudoPedido.append("Endereço de Entrega:\n")
                    .append("CEP: ").append(cep).append("\n")
                    .append("Endereço: ").append(endereco).append(", ").append(numero).append("\n")
                    .append("Complemento: ").append(complemento).append("\n");
            conteudoPedido.append("========================================\n");

            try {
                if (!Files.exists(caminhoArquivo)) {
                    Files.createFile(caminhoArquivo);
                }
                Files.writeString(caminhoArquivo, conteudoPedido.toString(), StandardOpenOption.APPEND);
                System.out.println("Pedido salvo em pedidos.txt");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar o pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
