package br.com.ita.coursera.week4.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ita.coursera.week4.src.ArmazenamentoArquivo;
import br.com.ita.coursera.week4.src.Placar;
import br.com.ita.coursera.week4.src.pontos.Estrela;

public class TestesDeIntegracao {

	@Test
	public void testeRegistrarPontosUsuario() {
		ArmazenamentoArquivo arquivo = new ArmazenamentoArquivo();
		Placar placar = new Placar(arquivo);

		Estrela pontosEstrela = new Estrela(28, "");
		String retorno = placar.registrarPontos("guerra", pontosEstrela);

		assertEquals("guerra recebeu 28 Estrela", retorno);
	}

	
}
