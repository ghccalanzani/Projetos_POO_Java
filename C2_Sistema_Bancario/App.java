import java.util.*;

public class App {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        //Lista de clientes
        ArrayList<Conta> listaClientes = new ArrayList<Conta>();
        int opcao;
        do {
            System.out.println("\nO que você deseja fazer?");
            System.out.println("""
                    1- Cadastrar conta
                    2- Ver saldo
                    3- Sacar
                    4- Depositar
                    5- Transferir
                    6- Sair""");
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarConta(listaClientes);
                    break;
                case 2:
                    saldoCliente(listaClientes);
                    break;
                case 3:
                    sacar(listaClientes);
                    break;
                case 4:
                    depositar(listaClientes);
                    break;
                case 5:
                    transferir(listaClientes);
                    break;
                case 6:
                    System.out.println("Programa encerrado!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }

        } while (opcao != 6);
    }

    public static boolean verificarNumContaDisponivel(ArrayList<Conta> listaClientes, int novoNumero) {
        boolean disponivel = true;
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getNumero() == novoNumero) {
                disponivel = false;
                break;
            }
        }
        return disponivel;
    }

    public static void cadastrarConta(ArrayList<Conta> listaClientes) {
        System.out.println("Qual tipo de conta será criada?\n1- Normal\n2- Especial");
        int tipoConta = teclado.nextInt();
        if (tipoConta != 1 && tipoConta != 2) {
            System.out.println("Opção incorreta!");
            return;
        }
        System.out.println("Qual o nome do cliente?");
        String novoNome = teclado.next();
        System.out.println("Qual o CPF do cliente?");
        String novoCpf = teclado.next();
        System.out.println("Qual o valor na conta?");
        double novoValor = teclado.nextDouble();
        System.out.println("Qual o número da conta?");
        int novoNumero = teclado.nextInt();
        if (tipoConta == 1) {
            if (verificarNumContaDisponivel(listaClientes, novoNumero)) {
                listaClientes.add(new Conta(novoNome, novoCpf, novoNumero, novoValor));
                System.out.println("Cliente cadastrado!");
            } else {
                System.out.println("Número de conta já existe! Não foi possível cadastrar o cliente.");
            }
        } else {
            if (verificarNumContaDisponivel(listaClientes, novoNumero)) {
                System.out.println("Qual o limite da conta?");
                double novoLimite = teclado.nextDouble();
                listaClientes.add(new ContaEspecial(novoNome, novoCpf, novoNumero, novoValor, novoLimite));
                System.out.println("Cliente cadastrado!");
            } else {
                System.out.println("Número de conta já existe! Insira um número diferente.");
            }
        }

    }

    public static void saldoCliente(ArrayList<Conta> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Não existem clientes cadastrados!");
            return;
        }
        System.out.println("Qual o número da conta? (SALDO)");
        int aux = teclado.nextInt();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getNumero() == aux) {
                System.out.println("O saldo do cliente " + listaClientes.get(i).getNome() + " é:");
                System.out.println(listaClientes.get(i).saldo());
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    public static void sacar(ArrayList<Conta> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Não existem clientes cadastrados!");
            return;
        }
        System.out.println("Qual o número da conta? (SAQUE)");
        int aux = teclado.nextInt();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getNumero() == aux) {
                System.out.println("Qual o valor do saque?");
                double valorSaque = teclado.nextDouble();
                if (listaClientes.get(i).saque(valorSaque)) {
                    System.out.println("Saque realizado!");
                    return;
                } else {
                    System.out.println("Saldo insuficiente! Saque não realizado!");
                    return;
                }
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    public static void depositar(ArrayList<Conta> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Não existem clientes cadastrados!");
            return;
        }
        System.out.println("Qual o número da conta? (DEPÓSITO)");
        int aux = teclado.nextInt();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getNumero() == aux) {
                System.out.println("Qual o valor do depósito?");
                double valorDeposito = teclado.nextDouble();
                listaClientes.get(i).deposito(valorDeposito);
                System.out.println("Depósito realizado!");
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    public static void transferir(ArrayList<Conta> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Não existem clientes cadastrados!");
            return;
        }
        boolean existeOrigem = false, existeDestino = false;
        int posicaoOrigem = -1, posicaoDestino = -1;

        System.out.println("Qual o número da conta de origem? (TRANSFERÊNCIA)");
        int numContaOrigem = teclado.nextInt();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getNumero() == numContaOrigem) {
                existeOrigem = true;
                posicaoOrigem = i;
                break;
            }
        }
        if(!existeOrigem){
            System.out.println("Conta origem não existe!");
            return;
        }
        System.out.println("Qual o número da conta de destino? (TRANSFERÊNCIA)");
        int numContaDestino = teclado.nextInt();
        for (int j = 0; j < listaClientes.size(); j++) {
            if (listaClientes.get(j).getNumero() == numContaDestino) {
                existeDestino = true;
                posicaoDestino = j;
                break;
            }
        }
        if(!existeDestino){
            System.out.println("Conta destino não existe!");
            return;
        }
        System.out.println("Qual o valor da transferência?");
        double valorTransf = teclado.nextDouble();
        if(listaClientes.get(posicaoOrigem).transferencia(valorTransf, listaClientes.get(posicaoDestino))){
            System.out.println("Transferência realizada!");
        }else{
            System.out.println("Saldo insuficiente! Transferência não realizada!");
        }
    }
}
