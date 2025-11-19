public class Lutador {

    protected String nome;
    protected int vida;
    protected int energia;
    protected boolean defendendo;

    public Lutador(String nome, int vida, int energia) {
        this.nome = nome;
        this.vida = vida;
        this.energia = energia;
        this.defendendo = false;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getEnergia() { return energia; }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
    }

    // ATAQUE ESPECIAL
    public void especial(Lutador alvo) {
        int custo = 20;
        int dano = 35;

        if (this.vida <= 0) {
            System.out.println(this.nome + " não pode usar especial porque está derrotado!");
            return;
        }

        if (alvo.getVida() <= 0) {
            System.out.println("O alvo " + alvo.getNome() + " já está derrotado!");
            return;
        }

        if (this.energia >= custo) {
            this.energia -= custo;

            if (alvo.defendendo) {
                dano /= 2;
            }

            alvo.receberDano(dano);
            alvo.defendendo = false;

            System.out.println(this.nome + " usou o ESPECIAL! Causou " + dano + " de dano.");
        } else {
            System.out.println(this.nome + ": Energia insuficiente para usar o golpe especial.");
        }
    }

    // ATAQUE NORMAL
    public void atacar(Lutador alvo) {

        if (this.vida <= 0) {
            System.out.println(this.nome + " não pode atacar porque está derrotado!");
            return;
        }

        if (alvo.getVida() <= 0) {
            System.out.println("O alvo " + alvo.getNome() + " já está derrotado!");
            return;
        }

        int dano = 10;

        if (alvo.defendendo) {
            dano /= 2;
        }

        alvo.receberDano(dano);
        alvo.defendendo = false;

        System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano!");
    }

    // ATIVAR DEFESA
    public void setDefesa(boolean defesa) {
        this.defendendo = defesa;
        if (defesa)
            System.out.println(this.nome + " está defendendo!");
        else
            System.out.println(this.nome + " parou de defender.");
    }

    public void exibirStatus() {
        System.out.println("===== STATUS DO LUTADOR =====");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Energia: " + energia);
        System.out.println("Defendendo: " + defendendo);
        System.out.println("=============================");
    }
}