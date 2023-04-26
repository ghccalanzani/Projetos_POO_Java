package locadora;

import java.io.*;
import java.util.*;

public class LeArquivoCliente {
    private Scanner entrada;

    //Construtor
    public LeArquivoCliente(String nome) throws FileNotFoundException {
        try {
            this.entrada = new Scanner(new File(nome));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("ARQUIVO NAO ENCONTRADO");
        }
    }

    //Ler dados contidos no arquivo
    public void leArquivo(ArrayList<Cliente> cliente) throws ArrayIndexOutOfBoundsException,
            NumberFormatException {
        String linha;
        while (this.entrada.hasNext()) {
            linha = this.entrada.nextLine();
            cliente.add(separaDados(linha));
        }
    }

    //Transformar linha em objeto
    private Cliente separaDados(String linha) throws ArrayIndexOutOfBoundsException {
        String[] dados;
        int tipoCliente;
        long telefoneCliente;

        //Atributos exclusivos de clientes PF
        String nomeCliente;
        long cpfCliente;

        //Atributos exclusivos de clientes PJ
        String nomeFantasiaCliente, razaoSocialCliente;
        long cnpjCliente;

        try {
            dados = linha.split(";");
            if(dados.length == 4){
                tipoCliente = Integer.parseInt(dados[0]);
                nomeCliente = dados[1];
                cpfCliente = Long.parseLong(dados[2]);
                telefoneCliente = Long.parseLong(dados[3]);
                return (new Cliente_Pessoa_Fisica(tipoCliente, nomeCliente, cpfCliente, telefoneCliente));
            }else{
                tipoCliente = Integer.parseInt(dados[0]);
                nomeFantasiaCliente = dados[1];
                razaoSocialCliente = dados[2];
                cnpjCliente = Long.parseLong(dados[3]);
                telefoneCliente = Long.parseLong(dados[4]);
                return (new Cliente_Pessoa_Juridica(tipoCliente, nomeFantasiaCliente, razaoSocialCliente, cnpjCliente, telefoneCliente));
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
