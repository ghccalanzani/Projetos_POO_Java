public class ContaEspecial extends Conta{

    private double limite;

    public ContaEspecial(String nome, String cpf, int numero, double valor_na_conta, double limite) {
        super(nome, cpf, numero, valor_na_conta);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public double saldo(){
        return getValor_na_conta() + limite;
    }

}
