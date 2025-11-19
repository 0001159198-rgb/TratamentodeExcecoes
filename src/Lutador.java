public class Lutador {


    // ===== ATRIBUTOS =====
    protected String nome;
    protected int vida;
    protected int energia;

    // ===== CONSTRUTOR =====
    public Lutador(String nome, int vida, int energia) {
        this.nome = nome;
        this.vida = vida;
        this.energia = energia;
    }

    // ===== GETTERS =====
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getEnergia() {
        return energia;
    }

    // ===== MÉTODOS DE COMBATE =====

    // Controle de dano recebido (impede vida negativa)
    public void receberDano(int dano) {
        this.vida -= dano;


        if (this.vida < 0) {
            this.vida = 0; // impede vida negativa
        }
    }

    // Golpe especial com verificação de energia
    public void especial(Lutador alvo) {
        int custo = 20;
        int dano = 35;

        // Verifica se quem usa o especial está vivo
        if (this.vida <= 0) {
            System.out.println(this.nome + " não pode usar especial porque está derrotado!");
            return;
        }

        // Verifica se o alvo está vivo
        if (alvo.getVida() <= 0) {
            System.out.println("O alvo " + alvo.getNome() + " já está derrotado!");
            return;
        }

        // Verifica energia suficiente
        if (this.energia >= custo) {
            this.energia -= custo;
            alvo.receberDano(dano);
            System.out.println(this.nome + " usou o ESPECIAL! Causou " + dano + " de dano.");
        } else {
            System.out.println(this.nome + ": Energia insuficiente para usar o golpe especial.");
        }
    }

    // Ataque básico com validação de vida
    public void atacar(Lutador alvo) {


        // Verifica se o atacante está vivo
        if (this.vida <= 0) {
            System.out.println(this.nome + " não pode atacar porque está derrotado!");
            return;
        }

        // Verifica se o alvo está vivo
        if (alvo.getVida() <= 0) {
            System.out.println("O alvo " + alvo.getNome() + " já está derrotado!");
            return;
        }

        int dano = 10;
        alvo.receberDano(dano);
        System.out.println(this.nome + " atacou e causou " + dano + " de dano!");
    }

    // ===== MOSTRAR STATUS =====
    public void exibirStatus() {
        System.out.println("===== STATUS DO LUTADOR =====");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Energia: " + energia);
        System.out.println("=============================");
    }
}


