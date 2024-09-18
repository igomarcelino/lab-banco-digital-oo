import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Banco {

	private final static String NOME = "Banco DIO";
	private List<Conta> contaList;
	private List<Cliente> clienteList;
	private Conta conta;

	public List<Cliente> getCliente() {
		return clienteList;
	}



	public Conta getConta() {
		return conta;
	}

	public Banco() {
		contaList = new ArrayList<>();
	}

	public String getNome() {
		return NOME;
	}


	public List<Conta> getContaList() {
		return contaList;
	}


	public void cadastrarCliente(Cliente cliente){
		this.clienteList = new ArrayList<>();
		clienteList.add(cliente);
	}


	public void abrirConta(Cliente cliente, Conta conta){
		this.conta = conta;
		this.conta.abriConta(cliente);
		contaList.add(conta);
		System.out.println("Clientes: " + clienteList);
		System.out.println("Contas: " + contaList);
		conta.imprimirInfosComuns();

	}

	public boolean autenticar(String cpf, String autenticacao){
		System.out.println(clienteList);
		String senhaCliente = clienteList.stream().filter(c -> c.getCpf().equalsIgnoreCase(cpf)).map(Cliente::getAutenticacao).collect(Collectors.joining());
		return senhaCliente.equalsIgnoreCase(autenticacao);
	}







	@Override
	public String toString() {
		return "Banco{" +
				"contas=" + contaList +
				", cliente=" + clienteList +
				", conta=" + conta +
				'}';
	}
}
