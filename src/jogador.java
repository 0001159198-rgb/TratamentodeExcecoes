import java.util.Scanner;
import java.util.InputMismatchException;

public class jogador {
    public static int lerInteiro(Scanner scanner) {
        int valor;
        while (true) {
            try {
                System.out.print("Digite um número: ");
                valor = scanner.nextInt();
                break; // leitura válida
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.nextLine(); // limpa o buffer
            }
        }
        return valor;
    }

    // Classe Lutador - exemplo de lutador
    public static class Lutador {
        private String nome;
        private int vida;
        private int poderDeAtaque;

        public Lutador(String nome, int vida, int poderDeAtaque) {
            this.nome = nome;
            this.vida = vida;
            this.poderDeAtaque = poderDeAtaque;
        }

        // Exibir status do lutador
        public void exibirStatus() {
            System.out.println("Nome: " + nome);
            System.out.println("Vida: " + vida);
            System.out.println("Poder de Ataque: " + poderDeAtaque);
        }

        // Método de ataque
        public void atacar(Lutador inimigo) {
            if (inimigo.vida > 0) {
                inimigo.vida -= poderDeAtaque;
                System.out.println(nome + " atacou " + inimigo.nome + " e causou " + poderDeAtaque + " de dano!");
            } else {
                System.out.println(inimigo.nome + " já está derrotado!");
            }
        }

        // Método de ataque especial
        public void especial(Lutador inimigo) {
            if (inimigo.vida > 0) {
                int danoEspecial = poderDeAtaque * 2; // ataque especial faz o dobro de dano
                inimigo.vida -= danoEspecial;
                System.out.println(nome + " usou o ataque especial e causou " + danoEspecial + " de dano!");
            } else {
                System.out.println(inimigo.nome + " já está derrotado!");
            }
        }
    }

    // MÉTODO MAIN
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dois lutadores de exemplo
        Lutador jogador = new Lutador("Jogador", 100, 50);
        Lutador inimigo = new Lutador("Inimigo", 100, 50);

        int opcao;

        while (true) { // loop infinito — você decide quando parar
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Exibir Status dos Lutadores");
            System.out.println("2 - Atacar Inimigo");
            System.out.println("3 - Usar Especial");
            System.out.println("================");

            opcao = lerInteiro(scanner); // leitura blindada

            switch (opcao) {
                case 1:
                    System.out.println("\nSTATUS DO JOGADOR:");
                    jogador.exibirStatus();
                    System.out.println("\nSTATUS DO INIMIGO:");
                    inimigo.exibirStatus();
                    break;

                case 2:
                    System.out.println("\n ATAQUE!");
                    jogador.atacar(inimigo);
                    break;

                case 3:
                    System.out.println("\n ESPECIAL!");
                    jogador.especial(inimigo);
                    break;

                default:
                    System.out.println("Opção inválida! Escolha 1, 2 ou 3.");
                    break;
            }

            // Verificar se o inimigo foi derrotado
            if (inimigo.vida <= 0) {
                System.out.println("\nO inimigo foi derrotado! Fim de jogo.");
                break;
            }
        }

        scanner.close();
    }
}