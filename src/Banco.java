import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nome;
	private List<Conta> contas;
	private TipoTransferencia tipoTransferencia;

	public Banco() {
		contas = new ArrayList<>();
	}

	public TipoTransferencia getTipoTransferencia() {
		return tipoTransferencia;
	}

	public void setTipoTransferencia(TipoTransferencia tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public void adicionarConta(Conta conta){
		this.contas.add(conta);
		System.out.println(contas);
	}



}
