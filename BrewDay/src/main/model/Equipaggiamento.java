package model;

public class Equipaggiamento {
	private int idEquipaggiamento;
	private String nome;
	private float capacita;
	
	public Equipaggiamento(String nome, float capacita) {
		this.idEquipaggiamento = -1;
		this.nome = nome;
		this.capacita = capacita;
	}
	
	public int getIdEquipaggiamento() {
		return idEquipaggiamento;
	}
	
	public void setIdEquipaggiamento(int idEquipaggiamento) {
		this.idEquipaggiamento = idEquipaggiamento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getCapacita() {
		return capacita;
	}
	
	public void setCapacita(float capacita) {
		this.capacita = capacita;
	}
}
