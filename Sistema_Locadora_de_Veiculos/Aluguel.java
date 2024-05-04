package locadora;

public class Aluguel {

    private static final int Aluguel_Ativo = 1;
    private static final int Aluguel_Inativo = 2;
    private int codigoAluguel;
    private int statusAluguel;
    private Cliente clienteAluguel;
    private Veiculo veiculoAluguel;
    private int quantDiasAluguel;
    private double valorTotalAluguel;
    private static int geradorCodigoAluguel = 1;

    //Construtor -------------------------------------------------------
    public Aluguel(Cliente clienteAluguel, Veiculo veiculoAluguel, int quantDiasAluguel) {
        this.statusAluguel = Aluguel_Ativo;
        this.clienteAluguel = clienteAluguel;
        this.veiculoAluguel = veiculoAluguel;
        this.quantDiasAluguel = quantDiasAluguel;
        this.valorTotalAluguel = (quantDiasAluguel * veiculoAluguel.getValorAluguelVeiculo());
        this.codigoAluguel = geradorCodigoAluguel;
        proximoCodigoAluguel();
    }

    //Getters -------------------------------------------------------
    public Cliente getClienteAluguel() {
        return clienteAluguel;
    }

    public Veiculo getVeiculoAluguel() {
        return veiculoAluguel;
    }

    public int getQuantDiasAluguel() {
        return quantDiasAluguel;
    }

    public double getValorTotalAluguel() {
        return valorTotalAluguel;
    }

    public int getCodigoAluguel() {
        return codigoAluguel;
    }

    public int getStatusAluguel() {
        return statusAluguel;
    }

    //Setters -------------------------------------------------------
    public void setClienteAluguel(Cliente clienteAluguel) {
        this.clienteAluguel = clienteAluguel;
    }

    public void setVeiculoAluguel(Veiculo veiculoAluguel) {
        this.veiculoAluguel = veiculoAluguel;
    }

    public void setQuantDiasAluguel(int quantDiasAluguel) {
        this.quantDiasAluguel = quantDiasAluguel;
    }

    public void setValorTotalAluguel(double valorTotalAluguel) {
        this.valorTotalAluguel = valorTotalAluguel;
    }

    public void setStatusAluguel(int statusAluguel) {
        this.statusAluguel = statusAluguel;
    }

    //Aumentar contagem para código do aluguel -----------------------------------------------------
    public static void proximoCodigoAluguel(){
        geradorCodigoAluguel++;
    }

    //Retornar status do aluguel como string
    public String getStatusAluguelString() {
        switch (this.statusAluguel) {
            case Aluguel_Ativo:
                return "Ativo";
            case Aluguel_Inativo:
                return "Inativo";
        }
        return "";
    }

    //ToString -------------------------------------------------------
    @Override
    public String toString() {
        if(clienteAluguel instanceof Cliente_Pessoa_Fisica){
            return "Tipo de cliente = " + clienteAluguel.getTipoClienteString() +
                    "\nCliente = " + ((Cliente_Pessoa_Fisica) clienteAluguel).getNome() +
                    "\nCPF = " + ((Cliente_Pessoa_Fisica) clienteAluguel).getCpf() +
                    "\nTipo de Veiculo = " + veiculoAluguel.getTipoVeiculoString() +
                    "\nVeiculo = " + veiculoAluguel.getModelo() +
                    "\nQuant. dias do aluguel = " + quantDiasAluguel +
                    "\nValor Total do aluguel = " + (quantDiasAluguel * veiculoAluguel.getValorAluguelVeiculo()) +
                    "\nCodigo do aluguel = " + getCodigoAluguel() +
                    "\nStatus do aluguel = " + getStatusAluguelString() +
                    "\n---------";
        }else{
            return "Tipo de cliente = " + clienteAluguel.getTipoClienteString() +
                    "\nCliente = " + ((Cliente_Pessoa_Juridica) clienteAluguel).getRazaoSocial() +
                    "\nCNPJ = " + ((Cliente_Pessoa_Juridica) clienteAluguel).getCnpj() +
                    "\nTipo de Veiculo = " + veiculoAluguel.getTipoVeiculoString() +
                    "\nVeiculo = " + veiculoAluguel.getModelo() +
                    "\nQuant. dias do aluguel = " + quantDiasAluguel +
                    "\nValor Total do aluguel = " + (quantDiasAluguel * veiculoAluguel.getValorAluguelVeiculo()) +
                    "\nCodigo do aluguel = " + getCodigoAluguel() +
                    "\nStatus do aluguel = " + getStatusAluguelString() +
                    "\n---------";
        }
    }

}
