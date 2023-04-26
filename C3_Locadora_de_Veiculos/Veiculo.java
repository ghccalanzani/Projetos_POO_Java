package locadora;

public abstract class Veiculo {

    private static final int Veiculo_Carro = 1;
    private static final int Veiculo_Moto = 2;

    private int tipoVeiculo;
    private String placa, modelo, cor, marca;
    private int kilometragem;
    private double valorAluguelVeiculo;

    //Construtor -------------------------------------------------------
    public Veiculo(int tipoVeiculo, String placa, String modelo, String cor, String marca, int kilometragem, double valorAluguelVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.marca = marca;
        this.kilometragem = kilometragem;
        this.valorAluguelVeiculo = valorAluguelVeiculo;
    }

    //Getters -------------------------------------------------------
    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public String getMarca() {
        return marca;
    }

    public int getKilometragem() {
        return kilometragem;
    }

    public double getValorAluguelVeiculo() {
        return valorAluguelVeiculo;
    }


    //Setters (Não é possível alterar tipo de veículo) ---------------------------------------------
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setKilometragem(int kilometragem) {
        this.kilometragem = kilometragem;
    }

    public void setValorAluguelVeiculo(double valorAluguelVeiculo) {
        this.valorAluguelVeiculo = valorAluguelVeiculo;
    }

    //Retorno do tipo de veículo como string -----------------------------------------------------
    public String getTipoVeiculoString() {
        switch (this.tipoVeiculo) {
            case Veiculo_Carro:
                return "Carro";
            case Veiculo_Moto:
                return "Moto";
        }
        return "";
    }

    //ToString -------------------------------------------------------
    @Override
    public String toString() {
        return "Tipo de Veiculo = " + getTipoVeiculoString() +
                "\nPlaca = " + placa +
                "\nModelo = " + modelo +
                "\nCor = " + cor +
                "\nMarca = " + marca +
                "\nKilometragem = " + kilometragem +
                "\nValor do aluguel = " + valorAluguelVeiculo;
    }

    //String para uso em Arquivo
    public String toStringArquivo(){
        return this.getTipoVeiculo() + ";" +
                this.getPlaca() + ";" +
                this.getModelo() + ";" +
                this.getCor() + ";" +
                this.getMarca() + ";" +
                this.getKilometragem() + ";" +
                this.getValorAluguelVeiculo();
    }
}
