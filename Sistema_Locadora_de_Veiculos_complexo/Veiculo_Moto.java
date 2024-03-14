package locadora;

public class Veiculo_Moto extends Veiculo{

    private String tipoMoto;
    private int cilindradas;

    //Construtor -------------------------------------------------------


    public Veiculo_Moto(int tipoVeiculo, String placa, String modelo, String cor, String marca, int kilometragem, double valorAluguelVeiculo, String tipoMoto, int cilindradas) {
        super(tipoVeiculo, placa, modelo, cor, marca, kilometragem, valorAluguelVeiculo);
        this.tipoMoto = tipoMoto;
        this.cilindradas = cilindradas;
    }

    //Getters -------------------------------------------------------
    public String getTipoMoto() {
        return tipoMoto;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    //Setters -------------------------------------------------------
    public void setTipoMoto(String tipoMoto) {
        this.tipoMoto = tipoMoto;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    //ToString -------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() +
                "\nTipo de Moto = " + tipoMoto +
                "\nCilindradas = " + cilindradas +
                "\n---------";
    }

    //String para uso em Arquivo
    public String toStringArquivo(){
        String str = super.toStringArquivo() + ";" +
                    this.getTipoMoto() + ";" +
                    this.getCilindradas();
        return str;
    }
}
