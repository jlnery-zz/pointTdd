package br.com.ita.coursera.week4.src.pontos;

import java.util.Arrays;
import java.util.List;

public abstract class Pontos {

	public static final int ESTRELA = 1;
	public static final int MOEDA = 2;
	public static final int TOPICO = 3;
	public static final int COMENTARIO = 4;
	public static final int CURTIDA = 5;

	private int quantidade;
	private int codigoPonto;
	private String participante;
	
	public Pontos(int quantidade, int codigoPonto, String participante) {
		this.setQuantidade(quantidade);
		this.setCodigoPonto(codigoPonto);
		this.setParticipante(participante);
	}

	public static String codigoPontoParaString(int tipoPonto) {
		if (tipoPonto == ESTRELA)
			return "Estrela";
		if (tipoPonto == MOEDA)
			return "Moeda";
		if (tipoPonto == TOPICO)
			return "Tópico";
		if (tipoPonto == COMENTARIO)
			return "Comentário";
		if (tipoPonto == CURTIDA)
			return "Curtidas";
		throw new RuntimeException("Tipo de Pontuação não existe : " + tipoPonto);
	}

	public static Pontos novaPontuacao(int quantidade, int tipoPonto, String participante) {
		if (tipoPonto == ESTRELA)
			return new Estrela(quantidade, participante);
		if (tipoPonto == MOEDA)
			return new Moeda(quantidade, participante);
		if (tipoPonto == TOPICO)
			return new Topico(quantidade, participante);
		if (tipoPonto == COMENTARIO)
			return new Comentario(quantidade, participante);
		if (tipoPonto == CURTIDA)
			return new Curtida(quantidade, participante);
		throw new RuntimeException("Tipo de Pontuação não existe : " + tipoPonto);
	}

	public static List<Integer> obterTiposDePontos() {
		return Arrays.asList(ESTRELA, MOEDA, COMENTARIO, CURTIDA);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getCodigoPonto() {
		return codigoPonto;
	}

	public void setCodigoPonto(int codigoPonto) {
		this.codigoPonto = codigoPonto;
	}

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

}
