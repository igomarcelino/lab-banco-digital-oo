import java.util.Scanner;

public class InternetBanking {
    private Banco bancoDio;
    private Cliente cliente;
    private Conta conta;
    Scanner sc;



    public InternetBanking() {
        bancoDio = new Banco();
        sc = new Scanner(System.in);

    }

    protected  void cadastrarCliente(){
        cliente = new Cliente();
        System.out.println("Bem vindo ao banco dio, vamos realizar o seu cadastro e abrir a sua conta.");
        System.out.print("Informe o seu nome: ");
        cliente.setNome(sc.next());
        System.out.print("Informe o seu telefone: ");
        cliente.setTelefone(sc.next());
        System.out.print("Agora informe o seu cpf: ");
        cliente.setCpf(sc.next());
        System.out.println("Selecione o tipo de conta [ 1 ] corrente [ 2 ] poupanca: ");
        int tipoConta = sc.nextInt();
        conta = tipoConta == 1 ? new ContaCorrente(cliente) : new ContaPoupanca(cliente) ;
        System.out.print("Cadastre uma senha de autenticacao: ");
        conta.cadastrarAutenticacao(sc.next());
        bancoDio.adicionarConta(conta);
        sc.close();
    }

    protected void menu(){
        System.out.println("======== Banco DIO ========");
        System.out.println();
    }


}
