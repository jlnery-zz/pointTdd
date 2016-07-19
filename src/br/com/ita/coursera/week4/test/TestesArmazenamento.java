package br.com.ita.coursera.week4.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.ita.coursera.week4.src.ArmazenamentoArquivo;
import br.com.ita.coursera.week4.src.pontos.Curtida;
import br.com.ita.coursera.week4.src.pontos.Estrela;
import br.com.ita.coursera.week4.src.pontos.Pontos;

public class TestesArmazenamento {

	@Test
	public void testeRegistrarPontosUsuario() {
		ArmazenamentoArquivo armazenamentoArquivo = new ArmazenamentoArquivo();
		
		Estrela pontosEstrela = new Estrela(28, "guerra");
		
		armazenamentoArquivo.registrarPontos("guerra", pontosEstrela);
		
	}
	
	@Test
	public void testeRegistrarPontosDoisUsuario() {
		ArmazenamentoArquivo armazenamentoArquivo = new ArmazenamentoArquivo();

		armazenamentoArquivo.registrarPontos("guerra", new Estrela(28, "guerra"));
		armazenamentoArquivo.registrarPontos("paz", new Estrela(28, "paz"));
		armazenamentoArquivo.registrarPontos("paz", new Estrela(22, "paz"));
		armazenamentoArquivo.registrarPontos("fela", new Estrela(10, "fela"));
	}
	
	@Test
	public void testePontosPorUsuario() {
		ArmazenamentoArquivo armazenamentoArquivo = new ArmazenamentoArquivo();

		armazenamentoArquivo.registrarPontos("guerra", new Estrela(28, "guerra"));
		armazenamentoArquivo.registrarPontos("guerra", new Estrela(2, "guerra"));
		
		Pontos p = armazenamentoArquivo.obterPontosDoUsuarioPorTipo("guerra", Pontos.ESTRELA);
		
		assertEquals(30, p.getQuantidade());
	
	}
	
	@Test
	public void testeUsuasiosComPonto() {
		ArmazenamentoArquivo armazenamentoArquivo = new ArmazenamentoArquivo();

		armazenamentoArquivo.registrarPontos("guerra", new Estrela(28, "guerra"));
		armazenamentoArquivo.registrarPontos("paz", new Estrela(2, "paz"));
		armazenamentoArquivo.registrarPontos("guerra", new Estrela(4, "guerra"));
		
		List<String> participantes = armazenamentoArquivo.obterUsuariosComPonto();
		assertEquals("[guerra, paz]", participantes.toString());
	}
	
	@Test
	public void testeUsuasiosTiposPontosRegistrados() {
		ArmazenamentoArquivo armazenamentoArquivo = new ArmazenamentoArquivo();

		armazenamentoArquivo.registrarPontos("guerra", new Estrela(28, "guerra"));
		armazenamentoArquivo.registrarPontos("paz", new Estrela(2, "paz"));
		armazenamentoArquivo.registrarPontos("guerra", new Estrela(4, "guerra"));
		armazenamentoArquivo.registrarPontos("guerra", new Curtida(10, "guerra"));
		
		List<String> participantes = armazenamentoArquivo.obterTiposDePontosRegistrados();
		assertEquals("[Estrela, Curtidas]", participantes.toString());
	}

}
