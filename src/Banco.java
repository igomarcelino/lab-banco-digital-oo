import java.util.*;
import java.util.stream.Collectors;

public class Banco {

	private final static String CLIENTESEMCADASTRO = "semCadastroDio";
	private final static String NOME = "Banco DIO";
	private Set<Conta> contaSet;
	private List<Cliente> clienteList;
	private Conta conta;


	public Conta getConta() {
		return conta;
	}

	public Banco() {
		this.contaSet = new HashSet<>();
		this.clienteList = new ArrayList<>();
	}

	public String getNome() {
		return NOME;
	}


	public Set<Conta> getContaSet() {
		return contaSet;
	}


	public void cadastrarCliente(Cliente cliente){

		clienteList.add(cliente);
	}

	/**
	 * Esse metodo realiza a abertura da conta, o mesmo ja recebe um cliente e um tipo de conta
	 * Poupanca ou Corrente
	 * */
	public void abrirConta(Cliente cliente, Conta conta){
		this.conta = conta;
		this.conta.abriConta(cliente);
		contaSet.add(conta);
		System.out.println("Clientes: " + clienteList);
		System.out.println("Contas: " + contaSet);
		conta.imprimirInfosComuns();

	}

	/**
	 * Esse metodo realiza a autenticacao , para ver se o cliente ja possui uma conta
	 * */
	public Conta autenticar(String cpf, String autenticacao){
		Conta conta = new ContaPoupanca(new Cliente());
		System.out.println(contaSet);
		String senhaCliente = contaSet.stream().
				filter(contas-> contas.getCliente().getCpf().equalsIgnoreCase(cpf)).
				map(c-> c.getCliente().getAutenticacao()).collect(Collectors.joining());
		if (senhaCliente.equalsIgnoreCase(autenticacao)){
			conta = contaSet.stream().filter(contas->contas.getCliente().getCpf().equalsIgnoreCase(cpf)).findAny().get();
		}else {
			Cliente cliente = new Cliente(CLIENTESEMCADASTRO);
			conta.abriConta(cliente);

			System.out.println(conta.getCliente());
		}
		return conta;
	}

	// metodo responsavel para achar a chave pix que a conta pertence
	public void transferir(String chavePix, double valor){
		Conta contaDestino = contaSet.stream().filter(chave->chave.getChavePix().equalsIgnoreCase(chavePix)).
				findAny().
				get();
		if (contaDestino != null){
			conta.sacar(valor);
			contaDestino.depositar(valor);
			System.out.println("=============================");
			System.out.println("	Comprovante PIX			");
			System.out.println("Favorecido: " + contaDestino.getCliente().getNome());
			System.out.println("Valor : " + valor);
			System.out.println("=============================");
		}
		else{
			System.out.println("Chave nao localizada");
		}
	}

	/**
	 * Esse metodo atualiza a conta apos encerrar as atividades no internet bankink
	 * */
	public void atualizarConta(Conta conta){
		this.contaSet.add(conta);
		System.out.println(contaSet);
	}

	@Override
	public String toString() {
		return "Banco{" +
				"contas=" + contaSet +
				", cliente=" + clienteList +
				", conta=" + conta +
				'}';
	}




	public void popularContas(){
		Cliente cliente1 = new Cliente("Aline","11111111111","1182982776","1111");
		Cliente cliente2 = new Cliente("Joao","22222222222","1123232232","1212");
		Cliente cliente3 = new Cliente("Luiza","33333333333","1382919191","1313");
		Cliente cliente4 = new Cliente("Roberto","44444444444","1819191911","1414");
		Cliente cliente5 = new Cliente("Aquaman","55555555555","3819991199","1515");
		Conta conta1 = new ContaPoupanca(cliente1);
		Conta conta2 = new ContaCorrente(cliente2);
		Conta conta3 = new ContaCorrente(cliente3);
		Conta conta4 = new ContaPoupanca(cliente4);
		Conta conta5 = new ContaCorrente(cliente5);
		conta1.adicionarChavePix(cliente1.getCpf());
		conta2.adicionarChavePix(cliente2.getCpf());
		conta3.adicionarChavePix(cliente3.getCpf());
		conta4.adicionarChavePix(cliente4.getCpf());
		conta5.adicionarChavePix(cliente5.getCpf());
		abrirConta(cliente1,conta1);
		abrirConta(cliente2,conta2);
		abrirConta(cliente3,conta3);
		abrirConta(cliente4,conta4);
		abrirConta(cliente5,conta5);
		clienteList.add(cliente1);
		clienteList.add(cliente2);
		clienteList.add(cliente3);
		clienteList.add(cliente4);
		clienteList.add(cliente5);
		contaSet.add(conta1);
		contaSet.add(conta2);
		contaSet.add(conta3);
		contaSet.add(conta4);
		contaSet.add(conta5);
	}
}
