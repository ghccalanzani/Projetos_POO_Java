package locadora;

public abstract class Cliente {
    //Tipo de cliente. É possível que o cliente seja uma pessoa fisica ou jurídica

    private static final int Pessoa_Fisica = 1;
    private static final int Pessoa_Juridica = 2;

    private int tipoCliente;

    public Cliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    //Getters -------------------------------------------------------
    public int getTipoCliente() {
        return tipoCliente;
    }

    //Retorno do tipo de cliente como string -----------------------------------------------------
    public String getTipoClienteString() {
        switch (this.tipoCliente) {
            case Pessoa_Fisica:
                return "Pessoa Física";
            case Pessoa_Juridica:
                return "Pessoa Jurídica";
        }
        return "";
    }


    //Setters (Não há, pois não é possível alterar o tipo de cliente) ------------------------------------------

    //ToString -------------------------------------------------------
    @Override
    public String toString() {
        return "Tipo de Cliente = " + this.getTipoClienteString();
    }

    //String para uso em Arquivo
    public String toStringArquivo() {
        return this.getTipoCliente() + "";
    }
}
