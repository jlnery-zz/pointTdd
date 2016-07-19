package br.com.ita.coursera.week4.src;

import java.util.List;

import br.com.ita.coursera.week4.src.pontos.Pontos;

public interface IArmazenamento {

	void registrarPontos(String usuario, Pontos pontos);

	Pontos obterPontosDoUsuarioPorTipo(String participante, int tipoPonto);

	List<String> obterUsuariosComPonto();
	
	List<String> obterTiposDePontosRegistrados();
}
