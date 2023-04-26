package locadora;

public class Veiculo_Carro extends Veiculo{
    private String tipoCarroceria;
    private boolean arCondicionado;

    //Construtor -------------------------------------------------------


    public Veiculo_Carro(int tipoVeiculo, String placa, String modelo, String cor, String marca, int kilometragem, double valorAluguel, String tipoCarroceria, boolean arCondicionado) {
        super(tipoVeiculo, placa, modelo, cor, marca, kilometragem, valorAluguel);
        this.tipoCarroceria = tipoCarroceria;
        this.arCondicionado = arCondicionado;
    }

    //Getters -------------------------------------------------------
    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    //Setters -------------------------------------------------------
    public void setTipoCarroceria(String tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    //ToString de Ar Condicionado para Sim ou Não ---------------------------------------------
    public String toStringAr(){
        if (this.arCondicionado) {
            return "Sim";
        }else{
            return "Não";
        }
    }

    //ToString -------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() +
                "\nTipo de Carroceria = " + tipoCarroceria +
                "\nPossui Ar Condicionado = " + toStringAr() +
                "\n---------";
    }

    //String para uso em Arquivo
    public String toStringArquivo(){
        String str =  super.toStringArquivo() + ";" +
                    this.getTipoCarroceria() + ";" +
                    this.isArCondicionado();
        return str;
    }
}
