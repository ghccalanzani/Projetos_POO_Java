package locadora;

import java.io.*;
import java.util.*;

public class LeArquivoVeiculo {
    private Scanner entrada;

    //Construtor
    public LeArquivoVeiculo(String nome) throws FileNotFoundException {
        try {
            this.entrada = new Scanner(new File(nome));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("ARQUIVO NAO ENCONTRADO");
        }
    }

    //Ler dados contidos no arquivo
    public void leArquivo(ArrayList<Veiculo> veiculo) throws ArrayIndexOutOfBoundsException,
            NumberFormatException {
        String linha;
        while (this.entrada.hasNext()) {
            linha = this.entrada.nextLine();
            veiculo.add(separaDados(linha));
        }
    }

    //Transformar linha em objeto
    private Veiculo separaDados(String linha) throws ArrayIndexOutOfBoundsException {
        String[] dados;
        int tipoVeiculo;
        String placaVeiculo, modeloVeiculo, corVeiculo, marcaVeiculo;
        int kilometragemVeiculo;
        double valorAluguelVeiculo;

        //Atributos exclusivos de carros
        String tipoCarroceriaVeiculo;
        boolean temArCondicionadoVeiculo;

        //Atributos exclusivos de motos
        String tipoMotoVeiculo;
        int cilindradasVeiculo;
        //str.useLocale(Locale.US);

        try {
            dados = linha.split(";");
            tipoVeiculo = Integer.parseInt(dados[0]);
            placaVeiculo = dados[1];
            modeloVeiculo = dados[2];
            corVeiculo = dados[3];
            marcaVeiculo = dados[4];
            kilometragemVeiculo = Integer.parseInt(dados[5]);
            valorAluguelVeiculo = Double.parseDouble(dados[6]);
            if (tipoVeiculo == 1) {
                tipoCarroceriaVeiculo = dados[7];
                temArCondicionadoVeiculo = Boolean.parseBoolean(dados[8]);
                return (new Veiculo_Carro(tipoVeiculo, placaVeiculo, modeloVeiculo, corVeiculo, marcaVeiculo, kilometragemVeiculo,
                        valorAluguelVeiculo, tipoCarroceriaVeiculo, temArCondicionadoVeiculo));
            } else {
                tipoMotoVeiculo = dados[7];
                cilindradasVeiculo = Integer.parseInt(dados[8]);
                return (new Veiculo_Moto(tipoVeiculo, placaVeiculo, modeloVeiculo, corVeiculo, marcaVeiculo, kilometragemVeiculo,
                        valorAluguelVeiculo, tipoMotoVeiculo, cilindradasVeiculo));
            }
        } catch (NoSuchElementException erro) {
            throw new NoSuchElementException("ARQUIVO DIFERENTE DO REGISTRO");
        }
    }

    //Fechar o arquivo
    public void fechaArquivo() throws IllegalStateException {
        try {
            this.entrada.close();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("ERRO AO FECHAR O ARQUIVO");
        }
    }

}
