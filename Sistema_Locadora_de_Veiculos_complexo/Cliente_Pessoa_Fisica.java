package locadora;

public class Cliente_Pessoa_Fisica extends Cliente {

    private String nome;
    private long cpf, telefone;

    //Construtor -------------------------------------------------------
    public Cliente_Pessoa_Fisica(int tipoCliente, String nome, long cpf, long telefone) {
        super(tipoCliente);
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    //Getters -------------------------------------------------------
    public String getNome() {
        return nome;
    }

    public long getCpf() {
        return cpf;
    }

    public long getTelefone() {
        return telefone;
    }

    //Setters -------------------------------------------------------
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    //ToString -------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() +
                "\nNome = " + nome +
                "\nCPF = " + cpf +
                "\nTelefone = " + telefone +
                "\n---------";
    }

    //String para uso em Arquivo
    public String toStringArquivo() {
        String str = super.toStringArquivo() + ";" +
                    this.getNome() + ";" +
                    this.getCpf() + ";" +
                    this.getTelefone();
        return str;
    }
}
