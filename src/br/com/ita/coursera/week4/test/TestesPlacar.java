package br.com.ita.coursera.week4.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ita.coursera.week4.src.Placar;
import br.com.ita.coursera.week4.src.pontos.Curtida;
import br.com.ita.coursera.week4.src.pontos.Estrela;
import br.com.ita.coursera.week4.src.pontos.Pontos;

public class TestesPlacar {

	@Test
	public void testeRegistrarPontosUsuario() {
		MockArmazenamento mockArmazenamento = new MockArmazenamento();
		Placar placar = new Placar(mockArmazenamento);

		Estrela pontosEstrela = new Estrela(28, "");
		String retorno = placar.registrarPontos("guerra", pontosEstrela);

		assertEquals("guerra recebeu 28 Estrela", retorno);
	}

	@Test
	public void testeRecuperaPontosDeUsuario() {
		MockArmazenamento mockArmazenamento = new MockArmazenamento();
		Placar placar = new Placar(mockArmazenamento);

		Estrela pontosEstrela = new Estrela(15, "");
		Curtida pontosCurtida = new Curtida(25, "");

		placar.registrarPontos("guerra", pontosEstrela);
		placar.registrarPontos("guerra", pontosCurtida);

		String mensagemRetorno = placar.obterPontosDo("guerra");

		assertEquals("15 Estrela;25 Curtidas;", mensagemRetorno);
	}

	@Test
	public void testeRankingCurtidas() {
		MockArmazenamento mockArmazenamento = new MockArmazenamento();
		Placar placar = new Placar(mockArmazenamento);

		placar.registrarPontos("guerra", new Curtida(10, ""));
		placar.registrarPontos("paz", new Curtida(70, ""));
		placar.registrarPontos("amor", new Curtida(100, ""));
		placar.registrarPontos("uniao", new Curtida(50, ""));

		String mensagemRetorno = placar.obterRankingDoTipo(Pontos.CURTIDA);

		assertEquals("amor 100;paz 70;uniao 50;guerra 10;", mensagemRetorno);
	}

}
