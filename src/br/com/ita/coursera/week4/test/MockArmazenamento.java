package br.com.ita.coursera.week4.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.spi.TimeZoneNameProvider;

import br.com.ita.coursera.week4.src.IArmazenamento;
import br.com.ita.coursera.week4.src.pontos.Pontos;

public class MockArmazenamento implements IArmazenamento {

	Map<String, Map<Integer, Pontos>> registroDePontos;

	public MockArmazenamento() {
		registroDePontos = new HashMap<String, Map<Integer, Pontos>>();
	}

	@Override
	public void registrarPontos(String usuario, Pontos novoPonto) {
		if (registroDePontos.containsKey(usuario)) {
			if (registroDePontos.get(usuario).containsKey(novoPonto.getCodigoPonto())) {
				Pontos pontoCorrente = registroDePontos.get(usuario).get(novoPonto.getCodigoPonto());
				pontoCorrente.setQuantidade(pontoCorrente.getQuantidade() + novoPonto.getQuantidade());
				return;
			}
		} else {
			registroDePontos.put(usuario, new HashMap<Integer, Pontos>());
		}
		registroDePontos.get(usuario).put(novoPonto.getCodigoPonto(), novoPonto);
	}

	@Override
	public Pontos obterPontosDoUsuarioPorTipo(String participante, int tipoPonto) {

		if (registroDePontos.containsKey(participante)) {
			if (registroDePontos.get(participante).containsKey(tipoPonto)) {
				return registroDePontos.get(participante).get(tipoPonto);
			}
		}
		return null;
	}

	@Override
	public List<String> obterUsuariosComPonto() {
		List<String> usuarios = new ArrayList<String>();
		for (Entry<String, Map<Integer, Pontos>> entry : registroDePontos.entrySet()) {
			usuarios.add(entry.getKey());
		}
		return usuarios;
	}

	@Override
	public List<String> obterTiposDePontosRegistrados() {
		return null;
	}
}
