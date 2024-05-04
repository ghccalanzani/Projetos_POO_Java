package locadora;

import java.io.*;
import java.util.*;

public class App {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        //Lista de Clientes
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

        //Lista de Veiculos
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

        //Lista de Alugueis
        ArrayList<Aluguel> listaAlugueis = new ArrayList<Aluguel>();

        //Ler arquivo com clientes
        leClientes(listaClientes); //(OBS: Necessário existir arquivo "clientes.txt" no diretório raiz)

        //Ler arquivo com veículos
        leVeiculos(listaVeiculos); //(OBS: Necessário existir arquivo "veiculos.txt" no diretório raiz)

        //Menu de opcoes
        int opcao1;
        do {
            System.out.println("""
                    # # # # # # # # # # # # # # # # # # # # # # # #
                    # O que você deseja fazer?                    #
                    # ---------------Visualizar-----------------  #
                    # 1- Clientes | 2- Veículos | 3- Alugueis     #
                    # ---------------Cadastrar-----------------   #
                    # 4- Clientes | 5- Veículos | 6- Alugueis     #
                    # ----------------Outros-------------------   #
                    # 7- Encerrar aluguel                         #
                    # 8- Descontos e aumentos                     #
                    # 9- Dashboard do sistema                     #
                    # 0- Sair                                     #
                    # # # # # # # # # # # # # # # # # # # # # # # #""");
            opcao1 = teclado.nextInt();
            switch (opcao1) {
                case 1:
                    if (!menu1(listaClientes)) //Visualizar clientes
                        System.out.println("Não há clientes cadastrados!");
                    break;
                case 2:
                    if (!menu2(listaVeiculos)) //Visualizar veiculos
                        System.out.println("Não há veiculos cadastrados!");
                    break;
                case 3:
                    if (!menu3(listaAlugueis)) //Visualizar alugueis
                        System.out.println("Não há alugueis cadastrados!");
                    break;
                case 4:
                    if (!menu4(listaClientes)) //Cadastrar clientes
                        System.out.println("Não foi possivel cadastrar o cliente!");
                    break;
                case 5:
                    if (!menu5(listaVeiculos)) //Cadastrar veiculos
                        System.out.println("Não foi possivel cadastrar veiculo!");
                    break;
                case 6:
                    if (menu6(listaAlugueis, listaClientes, listaVeiculos)) //Cadastrar alugueis
                        System.out.println("Aluguel cadastrado com sucesso!");
                    break;
                case 7:
                    if (menu7(listaAlugueis)) //Encerrar aluguel
                        System.out.println("Aluguel encerrado com sucesso!");
                    break;
                case 8:
                    if (!menu8(listaVeiculos)) //Descontos e aumentos
                        System.out.println("Não foi possível aplicar desconto/aumento!");
                    break;
                case 9:
                    if (!menu9(listaAlugueis, listaClientes, listaVeiculos)) //Dashboard do sistema
                        System.out.println("Sistema vazio! Não há dado a ser exibido!");
                    break;
                case 0:
                    gravaClientes(listaClientes); //Grava arquivo de clientes
                    gravaVeiculos(listaVeiculos); //Grava arquivo de veículos
                    System.out.println("Sistema encerrado!"); //Sair
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao1 != 0);
    }

    //Le arquivo com clientes ---------------------------------------------------------------------------------------
    public static void leClientes(ArrayList<Cliente> clientes){
        LeArquivoCliente entrada = null;
        String arquivo;

        try{
            arquivo = "clientes.txt";
            entrada = new LeArquivoCliente(arquivo);
            entrada.leArquivo(clientes);
            entrada.fechaArquivo();
        }
        catch (FileNotFoundException erro){
            System.out.println("Arquivo não encontrado!");
            System.exit(1);
        }
        catch (ArrayIndexOutOfBoundsException erro){
            System.out.println("Índice fora dos limites");
            entrada.fechaArquivo();
            System.exit(1);
        }
        catch (NumberFormatException erro){
            System.out.println("Formato incorreto!");
            entrada.fechaArquivo();
            System.exit(1);
        }
        if (clientes!=null){
            for (Cliente c: clientes)
                if(c instanceof Cliente_Pessoa_Fisica){
                    System.out.println("Cliente de CPF " + ((Cliente_Pessoa_Fisica) c).getCpf() + " adicionado!");
                }else{
                    System.out.println("Cliente de CNPJ " + ((Cliente_Pessoa_Juridica) c).getCnpj() + " adicionado!");
                }
        }
    }

    //Le arquivo com veiculos ---------------------------------------------------------------------------------------
    public static void leVeiculos(ArrayList<Veiculo> veiculos){
        LeArquivoVeiculo entrada = null;
        String arquivo;

        try{
            arquivo = "veiculos.txt";
            entrada = new LeArquivoVeiculo(arquivo);
            entrada.leArquivo(veiculos);
            entrada.fechaArquivo();
        }
        catch (FileNotFoundException erro){
            System.out.println("Arquivo não encontrado!");
            System.exit(1);
        }
        catch (ArrayIndexOutOfBoundsException erro){
            System.out.println("Índice fora dos limites");
            entrada.fechaArquivo();
            System.exit(1);
        }
        catch (NumberFormatException erro){
            System.out.println("Formato incorreto!");
            entrada.fechaArquivo();
            System.exit(1);
        }
        if (veiculos!=null){
            for (Veiculo v: veiculos)
                System.out.println("Veiculo de placa " + v.getPlaca() + " adicionado!");
        }
    }


    //Grava arquivo com clientes ---------------------------------------------------------------------------------------
    public static void gravaClientes (ArrayList<Cliente> clientes){
        try{
            GravaArquivo saida = new GravaArquivo ("clientes.txt");
            for (Cliente c: clientes)
                saida.gravaArquivo(c.toStringArquivo()+"\n");
            saida.fechaArquivo();
        } catch (IOException erro){
            System.out.println("ERRO!");
        }
    }

    //Grava arquivo com veiculos ---------------------------------------------------------------------------------------
    public static void gravaVeiculos (ArrayList<Veiculo> veiculos){
        try{
            GravaArquivo saida = new GravaArquivo ("veiculos.txt");
            for (Veiculo v: veiculos)
                saida.gravaArquivo(v.toStringArquivo()+"\n");
            saida.fechaArquivo();
        } catch (IOException erro){
            System.out.println("ERRO!");
        }
    }

    //Método para verificar existencia da PLACA ---------------------------------------------------------------------------------------
    public static boolean verificarPlacaJaExiste(ArrayList<Veiculo> veiculos, String novaPlaca) {
        //Variavel auxiliar para existência da placa
        boolean jaExiste = false;
        //Verifica se base de veículos está vazia
        if (veiculos.isEmpty())
            return false;
        //Valida existência da placa
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().compareTo(novaPlaca) == 0) {
                jaExiste = true;
                break;
            }
        }
        return jaExiste;
    }

    //Método para verificar existencia do CPF ---------------------------------------------------------------------------------------
    public static boolean verificarCpfJaExiste(ArrayList<Cliente> clientes, long novoCpf) {
        //Variavel auxiliar para existência do cpf
        boolean jaExiste = false;
        //Verifica se base de clientes está vazia
        if (clientes.isEmpty())
            return false;
        //Valida existência da cpf
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof Cliente_Pessoa_Fisica) {
                if (((Cliente_Pessoa_Fisica) clientes.get(i)).getCpf() == novoCpf) {
                    jaExiste = true;
                    break;
                }
            }
        }
        return jaExiste;
    }

    //Método para verificar existencia do CNPF ---------------------------------------------------------------------------------------
    public static boolean verificarCnpjJaExiste(ArrayList<Cliente> clientes, long novoCnpj) {
        //Variavel auxiliar para existência do cpf
        boolean jaExiste = false;
        //Verifica se base de clientes está vazia
        if (clientes.isEmpty())
            return false;
        //Valida existência do cnpj
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof Cliente_Pessoa_Juridica) {
                if (((Cliente_Pessoa_Juridica) clientes.get(i)).getCnpj() == novoCnpj) {
                    jaExiste = true;
                    break;
                }
            }
        }
        return jaExiste;
    }


    //Busca de cliente por CPF ---------------------------------------------------------------------------------------
    public static Cliente pesquisaClientePorCpf(ArrayList<Cliente> clientes, long cpfBusca) {
        //Verifica se base de clientes está vazia
        if (clientes.isEmpty())
            return null;
        //Retorna cliente (PF)
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof Cliente_Pessoa_Fisica) {
                if (((Cliente_Pessoa_Fisica) clientes.get(i)).getCpf() == cpfBusca) {
                    return clientes.get(i);
                }
            }
        }
        return null;
    }

    //Busca de cliente por CNPJ ---------------------------------------------------------------------------------------
    public static Cliente pesquisaClientePorCnpj(ArrayList<Cliente> clientes, long cnpjBusca) {
        //Verifica se base de clientes está vazia
        if (clientes.isEmpty())
            return null;
        //Retorna cliente (PJ)
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) instanceof Cliente_Pessoa_Juridica) {
                if (((Cliente_Pessoa_Juridica) clientes.get(i)).getCnpj() == cnpjBusca) {
                    return clientes.get(i);
                }
            }
        }
        return null;
    }

    //Busca de veiculo por placa ---------------------------------------------------------------------------------------
    public static Veiculo pesquisaVeiculoPorPlaca(ArrayList<Veiculo> veiculos, String placaBusca) {
        //Verifica se base de veículos está vazia
        if (veiculos.isEmpty())
            return null;
        //Retorna veículo
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().compareTo(placaBusca) == 0) {
                return veiculos.get(i);
            }
        }
        return null;
    }

    //Ver lista de clientes ---------------------------------------------------------------------------------------
    public static boolean menu1(ArrayList<Cliente> clientes) {
        //Verifica se base de clientes está vazia
        if (clientes.isEmpty())
            return false;
        //Exibe clientes
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).toString());
        }
        return true;
    }

    //Ver lista de veiculos ---------------------------------------------------------------------------------------
    public static boolean menu2(ArrayList<Veiculo> veiculos) {
        //Verifica se base de veículos está vazia
        if (veiculos.isEmpty())
            return false;
        //Exibe veículos
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println(veiculos.get(i).toString());
        }
        return true;
    }

    //Ver lista de Alugueis ---------------------------------------------------------------------------------------
    public static boolean menu3(ArrayList<Aluguel> alugueis) {
        //Verifica se base de aluguéis está vazia
        if (alugueis.isEmpty())
            return false;
        //Exibe aluguéis
        for (int i = 0; i < alugueis.size(); i++) {
            System.out.println(alugueis.get(i).toString());
        }
        return true;
    }

    //Cadastrar cliente ---------------------------------------------------------------------------------------
    public static boolean menu4(ArrayList<Cliente> clientes) {
        //Variavel auxiliar para try-catch
        boolean sucesso = false;
        System.out.println("Insira o tipo de cliente que deseja cadastrar:\n1- Pessoa Fisica\n2- Pessoa Juridica");
        int novoClienteTipo = 0;
        //Valida TIPO como INT
        while (!sucesso) {
            try {
                novoClienteTipo = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira um TIPO válido!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        //Cliente Pessoa Física
        if (novoClienteTipo == 1) {
            System.out.println("Insira o NOME do cliente:");
            String novoClienteNome = teclado.next().toUpperCase();
            System.out.println("Insira o CPF do cliente:");
            long novoClienteCpf = 0;
            //Valida CPF como LONG
            while (!sucesso) {
                try {
                    novoClienteCpf = Long.parseLong(teclado.next());
                    sucesso = true;
                } catch (Exception e) {
                    System.out.println("Insira um CPF válido!");
                    teclado.reset();
                }
            }
            sucesso = false; //Reseta variavel auxiliar para Exceptions
            //Chama método para verificar existencia de CPF
            if (verificarCpfJaExiste(clientes, novoClienteCpf))
                return false;
            System.out.println("Insira o TELEFONE do cliente:");
            long novoClienteTelefone = 0;
            //Valida telefone como LONG
            while (!sucesso) {
                try {
                    novoClienteTelefone = Long.parseLong(teclado.next());
                    sucesso = true;
                } catch (Exception e) {
                    System.out.println("Insira um TELEFONE válido!");
                    teclado.reset();
                }
            }
            sucesso = false; //Reseta variavel auxiliar para Exceptions
            //Cria cliente PF
            clientes.add(new Cliente_Pessoa_Fisica(novoClienteTipo, novoClienteNome, novoClienteCpf, novoClienteTelefone));
            System.out.println("Cliente cadastrado com sucesso!");
            return true;
        }
        //Cliente Pessoa Jurídica
        if (novoClienteTipo == 2) {
            System.out.println("Insira o NOME FANTASIA do cliente:");
            String novoClienteNomeFantasia = teclado.next().toUpperCase();
            System.out.println("Insira a RAZAO SOCIAL do cliente:");
            String novoClienteRazaoSocial = teclado.next().toUpperCase();
            System.out.println("Insira o CNPJ do cliente:");
            long novoClienteCnpj = 0;
            //Valida CNPJ como LONG
            while (!sucesso) {
                try {
                    novoClienteCnpj = Long.parseLong(teclado.next());
                    sucesso = true;
                } catch (Exception e) {
                    System.out.println("Insira um CNPJ válido!");
                    teclado.reset();
                }
            }
            sucesso = false; //Reseta variavel auxiliar para Exceptions
            //Chama método para verificar existencia de CNPJ
            if (verificarCnpjJaExiste(clientes, novoClienteCnpj))
                return false;
            System.out.println("Insira o TELEFONE do cliente:");
            long novoClienteTelefone = 0;
            //Valida telefone como LONG
            while (!sucesso) {
                try {
                    novoClienteTelefone = Long.parseLong(teclado.next());
                    sucesso = true;
                } catch (Exception e) {
                    System.out.println("Insira um TELEFONE válido!");
                    teclado.reset();
                }
            }
            sucesso = false; //Reseta variavel auxiliar para Exceptions
            //Cria cliente PJ
            clientes.add(new Cliente_Pessoa_Juridica(novoClienteTipo, novoClienteNomeFantasia, novoClienteRazaoSocial, novoClienteCnpj, novoClienteTelefone));
            System.out.println("Cliente cadastrado com sucesso!");
            return true;
        }
        return false;
    }

    //Cadastrar veiculo ---------------------------------------------------------------------------------------
    public static boolean menu5(ArrayList<Veiculo> veiculos) {
        //Variavel auxiliar para try-catch
        boolean sucesso = false;
        System.out.println("Insira o tipo de veículo que deseja cadastrar:\n1- Carro\n2- Moto");
        int novoVeiculoTipo = 0;
        //Valida TIPO como INT
        while (!sucesso) {
            try {
                novoVeiculoTipo = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira um TIPO válido!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        System.out.println("Insira a PLACA do veículo:");
        String novoVeiculoPlaca = teclado.next().toUpperCase();
        //Chama método para verificar existencia da PLACA
        if (verificarPlacaJaExiste(veiculos, novoVeiculoPlaca))
            return false;
        System.out.println("Insira o MODELO do veículo:");
        String novoVeiculoModelo = teclado.next().toUpperCase();
        System.out.println("Insira a COR do veículo:");
        String novoVeiculoCor = teclado.next().toUpperCase();
        System.out.println("Insira a MARCA do veículo:");
        String novoVeiculoMarca = teclado.next().toUpperCase();
        System.out.println("Insira a KILOMETRAGEM do veículo:");
        int novoVeiculoKm = 0;
        //Valida KILOMETRAGEM como INT
        while (!sucesso) {
            try {
                novoVeiculoKm = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira uma Kilometragem válida!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        System.out.println("Insira o VALOR DO ALUGUEL do veículo:");
        double novoVeiculoValorAluguel = 0;
        //Valida VALOR DO ALUGUEL como DOUBLE
        while (!sucesso) {
            try {
                novoVeiculoValorAluguel = Double.parseDouble(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira um VALOR DO ALUGUEL válido!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        //Cadastrar Carro
        if (novoVeiculoTipo == 1) {
            System.out.println("Insira o TIPO DE CARROCERIA do carro:");
            String novoVeiculoCarroceria = teclado.next().toUpperCase();
            System.out.println("O carro possui AR CONDICIONADO (true ou false):");
            boolean novoVeiculoArCondicionado = teclado.nextBoolean();
            //Cria veículo carro
            veiculos.add(new Veiculo_Carro(novoVeiculoTipo, novoVeiculoPlaca, novoVeiculoModelo, novoVeiculoCor, novoVeiculoMarca, novoVeiculoKm, novoVeiculoValorAluguel, novoVeiculoCarroceria, novoVeiculoArCondicionado));
            System.out.println("Veiculo cadastrado com sucesso!");
            return true;
        }
        //Cadastrar Moto
        if (novoVeiculoTipo == 2) {
            System.out.println("Insira o TIPO da moto:");
            String novoVeiculoTipoMoto = teclado.next().toUpperCase();
            System.out.println("Insira quantas CILINDRADAS tem a moto:");
            int novoVeiculoCilindradas = 0;
            //Valida CILINDRADAS como INT
            while (!sucesso) {
                try {
                    novoVeiculoCilindradas = Integer.parseInt(teclado.next());
                    sucesso = true;
                } catch (Exception e) {
                    System.out.println("Insira um valor de CILINDRADAS válido!");
                    teclado.reset();
                }
            }
            sucesso = false; //Reseta variavel auxiliar para Exceptions
            //Cria veículo moto
            veiculos.add(new Veiculo_Moto(novoVeiculoTipo, novoVeiculoPlaca, novoVeiculoModelo, novoVeiculoCor, novoVeiculoMarca, novoVeiculoKm, novoVeiculoValorAluguel, novoVeiculoTipoMoto, novoVeiculoCilindradas));
            System.out.println("Veiculo cadastrado com sucesso!");
            return true;
        }
        return false;
    }

    //Cadastrar aluguel ---------------------------------------------------------------------------------------
    public static boolean menu6(ArrayList<Aluguel> alugueis, ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos) {
        //Variavel auxiliar para try-catch
        boolean sucesso = false;
        Cliente novoAluguelCliente = null;
        Veiculo novoAluguelVeiculo = null;
        System.out.println("O cliente é: \n1- Pessoa Fisica\n2- Pessoa Juridica");
        int auxTipoCliente = 0;
        //Valida TIPO CLIENTE como INT
        while (!sucesso) {
            try {
                auxTipoCliente = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira um TIPO válido!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        if (auxTipoCliente == 1) {
            System.out.println("Qual o CPF do cliente?");
            long novoAluguelCpf = 0;
            //Valida CPF como LONG
            while (!sucesso) {
                try {
                    novoAluguelCpf = Long.parseLong(teclado.next());
                    sucesso = true;
                } catch (Exception e) {
                    System.out.println("Insira um CPF válido!");
                    teclado.reset();
                }
            }
            sucesso = false; //Reseta variavel auxiliar para Exceptions
            //Busca CPF na base de dados
            novoAluguelCliente = pesquisaClientePorCpf(clientes, novoAluguelCpf);
            if (novoAluguelCliente == null) {
                System.out.println("Cliente não encontrado!");
                return false;
            }
        }
        if (auxTipoCliente == 2) {
            System.out.println("Qual o CNPJ do cliente?");
            long novoAluguelCnpj = 0;
            //Valida CNPJ como LONG
            while (!sucesso) {
                try {
                    novoAluguelCnpj = Long.parseLong(teclado.next());
                    sucesso = true;
                } catch (Exception e) {
                    System.out.println("Insira um CNPJ válido!");
                    teclado.reset();
                }
            }
            sucesso = false; //Reseta variavel auxiliar para Exceptions
            //Busca CNPJ na base de dados
            novoAluguelCliente = pesquisaClientePorCnpj(clientes, novoAluguelCnpj);
            if (novoAluguelCliente == null) {
                System.out.println("Cliente não encontrado!");
                return false;
            }
        }
        if (auxTipoCliente != 1 && auxTipoCliente != 2) {
            System.out.println("Tipo de cliente inválido!");
            return false;
        }
        System.out.println("Qual a PLACA do veículo a ser alugado?");
        String novoAluguelPlaca = teclado.next().toUpperCase();
        //Busca PLACA na base de dados
        novoAluguelVeiculo = pesquisaVeiculoPorPlaca(veiculos, novoAluguelPlaca);
        if (novoAluguelVeiculo == null) {
            System.out.println("Veiculo não encontrado!");
            return false;
        }
        System.out.println("Insira a QUANTIDADE DE DIAS do aluguel:");
        int novoAluguelQuantDias = 0;
        //Valida QUANT DIAS como INT
        while (!sucesso) {
            try {
                novoAluguelQuantDias = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira uma QUANTIDADE DE DIAS válida!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        //Cria aluguel
        alugueis.add(new Aluguel(novoAluguelCliente, novoAluguelVeiculo, novoAluguelQuantDias));
        //System.out.println("Aluguel cadastrado com sucesso!"); ----- Essa função tem um println no menu para casos de sucesso
        return true;
    }

    //Encerrar aluguel ---------------------------------------------------------------------------------------
    public static boolean menu7(ArrayList<Aluguel> alugueis) {
        //Variavel auxiliar para try-catch
        boolean sucesso = false;
        //Verifica se base de aluguéis está vazia
        if (alugueis.isEmpty()) {
            System.out.println("Não há alugueis no sistema");
            return false;
        }
        System.out.println("Insira o código do aluguel a ser encerrado:");
        int codigoAluguelEncerramento = 0;
        while (!sucesso) {
            try {
                codigoAluguelEncerramento = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira um CODIGO válido!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        //Encerra aluguel (Altera status para INATIVO)
        for (int i = 0; i < alugueis.size(); i++) {
            if (alugueis.get(i).getCodigoAluguel() == codigoAluguelEncerramento) {
                if (alugueis.get(i).getStatusAluguel() == 2) {
                    System.out.println("Aluguel já está inativo!");
                    return false;
                }
                if (alugueis.get(i).getStatusAluguel() == 1) {
                    alugueis.get(i).setStatusAluguel(2);
                    return true;
                }
            }
        }
        System.out.println("Aluguel não encontrado!");
        return false;
    }

    //Desconto e aumentos ---------------------------------------------------------------------------------------
    public static boolean menu8(ArrayList<Veiculo> veiculos) {
        //Variavel auxiliar para try-catch
        boolean sucesso = false;
        //Verifica se base de veículos está vazia
        if (veiculos.isEmpty()) {
            System.out.println("Não há veículos cadastrados!");
            return false;
        }
        System.out.println("Será realizado um:\n1- Desconto\n2- Aumento");
        int descontoOuAumento = 0;
        while (!sucesso) {
            try {
                descontoOuAumento = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira uma opção válida!");
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        //Verifica se opção inserida é válida
        if (descontoOuAumento != 1 && descontoOuAumento != 2) {
            System.out.println("Opção inválida!");
            return false;
        }
        System.out.println("Deseja aplicar desconto/aumento para:\n1- Carros\n2- Motos\n3- Todos os veículos");
        int tipoVeiculoDescontoEAumento = 0;
        while (!sucesso) {
            try {
                tipoVeiculoDescontoEAumento = Integer.parseInt(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira uma OPÇÃO válida!");
                teclado.reset();
            }
        }
        //Verifica se opção inserida é válida
        if (tipoVeiculoDescontoEAumento != 1 && tipoVeiculoDescontoEAumento != 2 && tipoVeiculoDescontoEAumento != 3) {
            System.out.println("Opção inválida!");
            return false;
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        System.out.println("Qual o porcentagem (%) do desconto/aumento a ser aplicada?");
        double porcentagem = 0;
        while (!sucesso) {
            try {
                porcentagem = Double.parseDouble(teclado.next());
                sucesso = true;
            } catch (Exception e) {
                System.out.println("Insira uma PORCENTAGEM válida!");
                teclado.reset();
            }
        }
        sucesso = false; //Reseta variavel auxiliar para Exceptions
        double porcentagemNovoValor = 0;
        //Aplica desconto ou aumento a variavel que será multiplicada pelo valor do aluguel do veículo
        if (descontoOuAumento == 1) {
            porcentagemNovoValor = 1 - (porcentagem / 100); //Desconto
        }
        if (descontoOuAumento == 2) {
            porcentagemNovoValor = 1 + (porcentagem / 100); //Aumento
        }
        //Aplica desconto/aumento para carros
        if (tipoVeiculoDescontoEAumento == 1) {
            for (int i = 0; i < veiculos.size(); i++) {
                if (veiculos.get(i) instanceof Veiculo_Carro) {
                    veiculos.get(i).setValorAluguelVeiculo(porcentagemNovoValor * veiculos.get(i).getValorAluguelVeiculo());
                }
            }
            System.out.println("Desconto/Aumento aplicado para carros!");
            return true;
        }
        //Aplica desconto/aumento para motos
        if (tipoVeiculoDescontoEAumento == 2) {
            for (int i = 0; i < veiculos.size(); i++) {
                if (veiculos.get(i) instanceof Veiculo_Moto) {
                    veiculos.get(i).setValorAluguelVeiculo(porcentagemNovoValor * veiculos.get(i).getValorAluguelVeiculo());
                }
            }
            System.out.println("Desconto/Aumento aplicado para motos!");
            return true;
        }
        //Aplica desconto/aumento para todos veículos
        if (tipoVeiculoDescontoEAumento == 3) {
            for (int i = 0; i < veiculos.size(); i++) {
                veiculos.get(i).setValorAluguelVeiculo(porcentagemNovoValor * veiculos.get(i).getValorAluguelVeiculo());
            }
            System.out.println("Desconto/Aumento aplicado para todos os veículos!");
            return true;
        }
        return false;
    }

    //Dashboard com informações relevantes do sistema -------------------------------------------------------------------
    public static boolean menu9(ArrayList<Aluguel> alugueis, ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos) {
        //Verifica se sistema está vazio
        if (alugueis.isEmpty() && clientes.isEmpty() && veiculos.isEmpty())
            return false;
        int quantClientesPF = 0, quantClientesPJ = 0, quantClientes = clientes.size();
        int quantVeiculosCarro = 0, quantVeiculosMoto = 0, quantVeiculos = veiculos.size();
        int quantAlugueis = alugueis.size(), quantAlugueisAtivos = 0;
        double valorTotalAlugueisAtivos = 0;
        //Soma quantidade de clientes (PF e PJ)
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getTipoCliente() == 1)
                quantClientesPF++;
            if (clientes.get(i).getTipoCliente() == 2)
                quantClientesPJ++;
        }
        //Soma quantidade de veículos (Carros e Motos)
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getTipoVeiculo() == 1)
                quantVeiculosCarro++;
            if (veiculos.get(i).getTipoVeiculo() == 2)
                quantVeiculosMoto++;
        }
        //Soma alugueis ativos e valor dos alugueis ativos
        for (int i = 0; i < alugueis.size(); i++) {
            if (alugueis.get(i).getStatusAluguel() == 1) {
                quantAlugueisAtivos++;
                valorTotalAlugueisAtivos = valorTotalAlugueisAtivos + alugueis.get(i).getValorTotalAluguel();
            }
        }
        System.out.println("----- Clientes -----");
        System.out.println("Quant. total de clientes = " + quantClientes);
        System.out.println("Quant. clientes Pessoa Fisica = " + quantClientesPF);
        System.out.println("Quant. clientes Pessoa Juridica = " + quantClientesPJ);
        System.out.println("----- Veiculos -----");
        System.out.println("Quant. total de veiculos = " + quantVeiculos);
        System.out.println("Quant. carros = " + quantVeiculosCarro);
        System.out.println("Quant. motos = " + quantVeiculosMoto);
        System.out.println("----- Alugueis -----");
        System.out.println("Quant. de alugueis = " + quantAlugueis);
        System.out.println("Quant. de alugueis (ATIVOS) = " + quantAlugueisAtivos);
        System.out.println("Valor total dos alugueis (ATIVOS) = " + valorTotalAlugueisAtivos);
        return true;
    }

}
