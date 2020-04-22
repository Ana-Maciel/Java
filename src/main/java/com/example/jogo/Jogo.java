package com.example.jogo;

import javax.persistence.*;

@Entity
@Table(name = "jogos")
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "descricao")
	private String descricao;

	public Jogo() {

	}

	public Jogo(String title, String descricao) {
		this.title = title;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", title=" + title + ", desc=" + descricao + "]";
	}
}