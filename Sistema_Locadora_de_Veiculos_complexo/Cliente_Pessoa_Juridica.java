package locadora;

public class Cliente_Pessoa_Juridica extends Cliente {

    private String nomeFantasia, razaoSocial;
    private long cnpj, telefone;

    //Construtor -------------------------------------------------------
    public Cliente_Pessoa_Juridica(int tipoCliente, String nomeFantasia, String razaoSocial, long cnpj, long telefone) {
        super(tipoCliente);
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    //Getters -------------------------------------------------------
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public long getCnpj() {
        return cnpj;
    }

    public long getTelefone() {
        return telefone;
    }

    //Setters -------------------------------------------------------
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    //ToString -------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() +
                "\nNome Fantasia = " + nomeFantasia +
                "\nRazao Social = " + razaoSocial +
                "\nCNPJ = " + cnpj +
                "\nTelefone = " + telefone +
                "\n---------";
    }

    //String para uso em Arquivo
    public String toStringArquivo() {
        String str = super.toStringArquivo() + ";" +
                    this.getNomeFantasia() + ";" +
                    this.getRazaoSocial() + ";" +
                    this.getCnpj() + ";" +
                    this.getTelefone();
        return str;
    }
}
