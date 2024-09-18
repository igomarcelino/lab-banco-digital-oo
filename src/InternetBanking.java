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
    public void telaPrincipal(){
        cadastrarCliente();
        if(loginInternetBanking()){
            System.out.println("Bem vindo");
            menu();
        }else {
            System.out.println("Cliente nao localizado, voce pode abrir uma conta");
        }
    }

    // realiza o cadastro do cliente
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
        cliente.setAutenticacao(sc.next());
        bancoDio.cadastrarCliente(cliente);
        bancoDio.abrirConta(cliente,conta);
    }

    // chama o menu de cadastro para as opcoes de saque, deposito, PIX
    protected void menu(){
        System.out.println("======== Banco DIO ========");
        System.out.println("Menu de opcoes");
        int opcoes ;
        do {
            System.out.println("\n[ 1 ] Depositar \n[ 2 ] sacar \n[ 3 ] PIX \n[ 4 ] SAIR\nInforme a opcao: ");
            opcoes= sc.nextInt();
            switch (opcoes){
                case 1 :
                    System.out.print("Valor do deposito R$: ");
                    conta.depositar(sc.nextDouble());
                    System.out.println("Deposito realizado com sucesso!\nSaldo atual R$:" + conta.getSaldo());
                    break;
                case 2 :
                    System.out.print("Valor do saque R$: " );
                    double valorSaque = sc.nextDouble();
                    if (conta.getSaldo() > valorSaque){
                        conta.sacar(valorSaque);
                        System.out.println("Saque realizado com sucesso!\nSaldo atual R$:" + conta.getSaldo());
                    }else {
                        System.out.println("Saldo insuficiente!");
                    }
                    break;
                case 3 :
                    menuPix();
                    break;
                case 4 :
                    System.out.println("Saindo ...");
                    break;
            }

        }while (opcoes != 4);
        System.out.println();
    }

    // menu PIX com as funcoes de pix , cadastro de chave e transferencia
    public void menuPix(){
        System.out.println("==== Opcoes PIX ====");
        System.out.println("[ 1 ] transferir\n[ 2 ] cadastrar chave\n[ 3 ] Voltar ao menu ");
        int opcoes = sc.nextInt();
        switch (opcoes){
            case 1:
                System.out.println("==== Transferencia PIX ====");
                System.out.print("Valor R$: ");
                double valorTransferencia = sc.nextDouble();
                System.out.print("Informe a chave PIX : ");
                String chaveDestino = sc.next();
                conta.transferir(valorTransferencia,chaveDestino);
                break;
            case 2:
                System.out.println("==== Cadastrar chave PIX ====");
                System.out.print("Informe o cpf: ");
                String verificaCPF = sc.next();
                System.out.print("\nInforme a autenticacao: ");
                String autenticacao = sc.next();
//                if (bancoDio.verificaContas(verificaCPF)){
//                    System.out.print("Informe a chave: ");
//                    String chave = sc.next();
//                    conta.adicionarChavePix(chave);
//                    System.out.println("Chave adicionada com sucesso! ");
//                    System.out.println(conta);
//                }else {
//                    System.out.println("Conta inexistente! Favor realize o cadastro");
//                }
                break;
            case 3:
                System.out.println("Voltando ao menu");
                break;
        }
    }

    public boolean loginInternetBanking(){
        System.out.println("==== Tela de login do banco dio ====");
        System.out.print("Informe o cpf: ");
        String cpf = sc.next();
        System.out.print("\nInforme a autenticacao: ");
        String autenticacao = sc.next();
        return bancoDio.autenticar(cpf,autenticacao);
    }
}
