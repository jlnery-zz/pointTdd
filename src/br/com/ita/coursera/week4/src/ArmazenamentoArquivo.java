package br.com.ita.coursera.week4.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import br.com.ita.coursera.week4.src.pontos.Pontos;

public class ArmazenamentoArquivo implements IArmazenamento {

	PrintWriter writer;

	public ArmazenamentoArquivo() {
		try {
			writer = new PrintWriter("ranking.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void registrarPontos(String usuario, Pontos ponto) {
		String line = ponto.getParticipante() + ";" + ponto.getCodigoPonto() + ";" + ponto.getQuantidade() + "\n";
		try {
			Files.write(Paths.get("ranking.txt"), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	@Override
	public Pontos obterPontosDoUsuarioPorTipo(String participante, int tipoPonto) {
		BufferedReader br = null;

		List<Pontos> pontosParaSomar = new ArrayList<Pontos>();
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("ranking.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] pontuacao = sCurrentLine.split(";");
				Pontos ponto = Pontos.novaPontuacao(Integer.parseInt(pontuacao[2]), Integer.parseInt(pontuacao[1]),
						pontuacao[0]);
				if (ponto.getCodigoPonto() == tipoPonto && ponto.getParticipante().equals(participante)) {
					pontosParaSomar.add(ponto);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if (pontosParaSomar.size() > 0) {
			Integer total = 0;
			
			for (Pontos p : pontosParaSomar) {
				total += p.getQuantidade();
			}
			pontosParaSomar.get(0).setQuantidade(total);
			return pontosParaSomar.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<String> obterUsuariosComPonto() {
		ArrayList<String> participantes = new ArrayList<String>();
		BufferedReader br = null;

		List<Pontos> pontosParaSomar = new ArrayList<Pontos>();
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("ranking.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] pontuacao = sCurrentLine.split(";");
				if(!participantes.contains(pontuacao[0]))
				{
					participantes.add(pontuacao[0]);	
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(participantes.toString());
		return participantes;
	}

	@Override
	public List<String> obterTiposDePontosRegistrados() {
		ArrayList<String> participantes = new ArrayList<String>();
		BufferedReader br = null;

		List<Pontos> pontosParaSomar = new ArrayList<Pontos>();
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("ranking.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] pontuacao = sCurrentLine.split(";");
				String tipoPonto = Pontos.codigoPontoParaString(Integer.parseInt(pontuacao[1]));
				if(!participantes.contains(tipoPonto))
				{
					participantes.add(tipoPonto);	
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(participantes.toString());
		return participantes;
	}
}
