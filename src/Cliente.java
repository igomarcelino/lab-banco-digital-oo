
public class Cliente {

	private String nome;
	private String cpf;
	private String telefone;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cliente cliente)) return false;

		return getCpf() != null ? getCpf().equals(cliente.getCpf()) : cliente.getCpf() == null;
	}

	@Override
	public int hashCode() {
		return getCpf() != null ? getCpf().hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"nome='" + nome + '\'' +
				", cpf='" + cpf + '\'' +
				", telefone='" + telefone + '\'' +
				'}';
	}

}
