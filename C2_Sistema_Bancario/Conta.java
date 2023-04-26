public class Conta implements Transacao{

    private String nome;
    private String cpf;
    private int numero;
    private double valor_na_conta;

    public Conta(String nome, String cpf, int numero, double valor_na_conta) {
        this.nome = nome;
        this.cpf = cpf;
        this.numero = numero;
        this.valor_na_conta = valor_na_conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getValor_na_conta() {
        return valor_na_conta;
    }

    public void setValor_na_conta(double valor_na_conta) {
        this.valor_na_conta = valor_na_conta;
    }

    @Override
    public void deposito(double valor) {
        valor_na_conta += valor;
    }

    @Override
    public boolean saque(double valor) {
        if (this.saldo() >= valor)    {
            valor_na_conta -= valor;
            return true;
        }   else    {
            return false;
        }
    }

    @Override
    public double saldo() {
        return valor_na_conta;
    }

    @Override
    public boolean transferencia(double valor, Conta outraConta) {
        if(this.saldo() >= valor) {
            outraConta.valor_na_conta += valor;
            valor_na_conta -= valor;
            return true;
        }   else    {
            return false;
        }
    }
}
