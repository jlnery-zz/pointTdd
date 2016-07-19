package br.com.ita.coursera.week4.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.ita.coursera.week4.src.pontos.Pontos;

public class Placar {

	IArmazenamento armazenamento;

	public Placar(IArmazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public String registrarPontos(String usuario, Pontos pontos) {
		armazenamento.registrarPontos(usuario, pontos);
		String mensagem = montarMensagemDeRegistroDePontos(usuario, pontos);
		System.out.println(mensagem);
		return mensagem;

	}

	private String montarMensagemDeRegistroDePontos(String usuario, Pontos pontos) {
		String mensagem = usuario + " recebeu " + pontos.getQuantidade() + " "
				+ Pontos.codigoPontoParaString(pontos.getCodigoPonto());
		return mensagem;
	}

	public String obterPontosDo(String participante) {
		List<Pontos> pontosDoUsuario = new ArrayList<Pontos>();
		for (Integer codigoTipo : Pontos.obterTiposDePontos()) {
			Pontos ponto = armazenamento.obterPontosDoUsuarioPorTipo(participante, codigoTipo);
			if (ponto != null) {
				pontosDoUsuario.add(ponto);
			}
		}
		String mensagem = montarMensagemDePontosDeUsuario(pontosDoUsuario);
		System.out.println(mensagem);
		return mensagem;
	}

	private String montarMensagemDePontosDeUsuario(List<Pontos> pontosDoUsuario) {
		String mensagem = "";
		for (Pontos ponto : pontosDoUsuario) {
			mensagem += ponto.getQuantidade() + " " + Pontos.codigoPontoParaString(ponto.getCodigoPonto());
			mensagem += ";";
		}
		return mensagem;
	}

	public List<String> obterUsuariosComPonto() {
		return armazenamento.obterUsuariosComPonto();
	}

	public String obterRankingDoTipo(int codigoPonto) {
		List<String> usuariosComPonto = armazenamento.obterUsuariosComPonto();
		Map<String, Pontos> ranking = new HashMap<String, Pontos>();
		for (String usuario : usuariosComPonto) {
			Pontos ponto = armazenamento.obterPontosDoUsuarioPorTipo(usuario, codigoPonto);
			if (ponto != null) {
				ranking.put(usuario, ponto);
			}
		}
		ranking = ordenarPorQuantidade(ranking);

		String mensagem = montarMensagemDeRanking(ranking);
		System.out.println(mensagem);
		return mensagem;
	}

	private String montarMensagemDeRanking(Map<String, Pontos> ranking) {
		String mensagem = "";
		for (Map.Entry<String, Pontos> entry : ranking.entrySet()) {
			mensagem += entry.getKey() + " " + entry.getValue().getQuantidade();
			mensagem += ";";
		}
		return mensagem;
	}

	private static Map<String, Pontos> ordenarPorQuantidade(Map<String, Pontos> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Pontos>> list = new LinkedList<Map.Entry<String, Pontos>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Pontos>>() {
			public int compare(Map.Entry<String, Pontos> o1, Map.Entry<String, Pontos> o2) {
				return new Integer(o2.getValue().getQuantidade()).compareTo(o1.getValue().getQuantidade());
			}
		});

		// Convert sorted map back to a Map
		Map<String, Pontos> sortedMap = new LinkedHashMap<String, Pontos>();
		for (Iterator<Map.Entry<String, Pontos>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Pontos> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
