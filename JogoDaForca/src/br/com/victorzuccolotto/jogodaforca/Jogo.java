package br.com.victorzuccolotto.jogodaforca;

import java.util.Scanner;

public class Jogo {

	public void jogada(Palavras plvrs, Vida vida) {
		while (true) {
			progresso(plvrs, vida);
			if(!plvrs.verificaPalavra(lerDoTeclado(plvrs)))
				vida.diminui();
			if(plvrs.verificaVitoria() || vida.verificaVida(plvrs))
				break;
		}

	}

	private void progresso(Palavras plvrs, Vida vida) {
		System.out.println(plvrs.palavraCensurada);
		System.out.println("Voce tem " + (vida.QuantidadeDeVidas) +" chances de erro");
	}

	public char lerDoTeclado(Palavras plvrs) {
		System.out.println("Caracteres usados: " + plvrs.letrasUsadas);
		Scanner scan = new Scanner(System.in);
		String sc = scan.next();
		try {
			if (sc.length() > 1) {
				throw new Exception("Digite somente um caractere");
			}
			if(plvrs.letrasUsadas.contains(sc.charAt(0)))
				throw new Exception("Esse caractere ja foi utilizado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.lerDoTeclado(plvrs);
		}
//		scan.close();
		
		return sc.charAt(0);
	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		Palavras plvrs = new Palavras();
		Vida vida = new Vida(plvrs.palavraSelecionada.length());
		jogo.jogada(plvrs,vida);
	}

}
