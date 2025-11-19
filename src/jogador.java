import java.util.Scanner;
import java.util.InputMismatchException;

public class jogador {

    public static int lerInteiro(Scanner scanner) {
        int valor;
        while (true) {
            try {
                System.out.print("Digite um número: ");
                valor = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.nextLine();
            }
        }
        return valor;
    }

    // Classe Lutador
    public static class Lutador {
        private String nome;
        private int vida;
        private int poderDeAtaque;
        private boolean defesa;

        public Lutador(String nome, int vida, int poderDeAtaque) {
            this.nome = nome;
            this.vida = vida;
            this.poderDeAtaque = poderDeAtaque;
            this.defesa = false;
        }

        public String getNome() { return nome; }
        public int getVida() { return vida; }

        public void exibirStatus() {
            System.out.println("Nome: " + nome);
            System.out.println("Vida: " + vida);
            System.out.println("Poder de Ataque: " + poderDeAtaque);
        }

        public void atacar(Lutador inimigo) {
            if (vida <= 0) {
                System.out.println(nome + " não pode atacar, está derrotado!");
                return;
            }

            if (inimigo.vida > 0) {

                int dano = poderDeAtaque;

                // REDUZIR DANO SE O INIMIGO ESTÁ DEFENDENDO
                if (inimigo.defesa) {
                    dano /= 2;
                    System.out.println(inimigo.nome + " DEFENDEU! Dano reduzido pela metade!");
                }

                inimigo.vida -= dano;
                if (inimigo.vida < 0) inimigo.vida = 0;

                System.out.println(nome + " atacou " + inimigo.nome +
                        " e causou " + dano + " de dano!");

                // Defesa só dura um turno
                inimigo.defesa = false;

            } else {
                System.out.println(inimigo.nome + " já está derrotado!");
            }
        }

        public void especial(Lutador inimigo) {
            if (vida <= 0) {
                System.out.println(nome + " não pode usar especial, está derrotado!");
                return;
            }

            if (inimigo.vida > 0) {
                int danoEspecial = poderDeAtaque * 2;

                if (inimigo.defesa) {
                    danoEspecial /= 2;
                    System.out.println(inimigo.nome + " DEFENDEU o especial! Dano reduzido!");
                }

                inimigo.vida -= danoEspecial;

                if (inimigo.vida < 0) inimigo.vida = 0;

                System.out.println(nome + " usou ESPECIAL e causou " + danoEspecial + " de dano!");

                inimigo.defesa = false;

            } else {
                System.out.println(inimigo.nome + " já está derrotado!");
            }
        }

        // DEFENDER FUNCIONAL
        public void defendendo() {
            this.defesa = true;
            System.out.println(nome + " está se defendendo! (dano será reduzido na próxima ação)");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Lutador jogador = new Lutador("Herói", 100, 20);
        Lutador inimigo = new Lutador("Goblin", 60, 10);

        int opcao;

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Exibir Status");
            System.out.println("2 - Atacar");
            System.out.println("3 - Especial");
            System.out.println("4 - Defender");
            System.out.println("================");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    System.out.println("\nSTATUS DO JOGADOR:");
                    jogador.exibirStatus();
                    System.out.println("\nSTATUS DO INIMIGO:");
                    inimigo.exibirStatus();
                    break;

                case 2:
                    System.out.println("\nATAQUE DO JOGADOR");
                    jogador.atacar(inimigo);
                    break;

                case 3:
                    System.out.println("\nESPECIAL DO JOGADOR");
                    jogador.especial(inimigo);
                    break;

                case 4:
                    System.out.println("\nDEFESA DO JOGADOR");
                    jogador.defendendo();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            // Verificar se o inimigo morreu
            if (inimigo.getVida() <= 0) {
                System.out.println("\nO inimigo foi derrotado! Você venceu!");
                break;
            }

            // VEZ DO INIMIGO
            System.out.println("\n---- VEZ DO INIMIGO ----");
            inimigo.atacar(jogador);

            if (jogador.getVida() <= 0) {
                System.out.println("\nVocê foi derrotado! Fim de jogo.");
                break;
            }
        }

        scanner.close();
    }
}