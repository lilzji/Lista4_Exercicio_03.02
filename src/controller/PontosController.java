package controller;

import model.Pontos;

public class PontosController extends Thread {

	private int qtd;
	private int size;
	private int index;
	private Pontos[] vetor_player;

	public PontosController(int qtd) {
		super();
		this.size = qtd;
		this.qtd = qtd;
		this.index = 0;
		this.vetor_player = new Pontos[qtd];
	}

	public void run() {

		int position = 1;

		while (qtd > 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int reduce = 1;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size - reduce; x++) {
				if (vetor_player[x].getPontos() < vetor_player[x + 1].getPontos()) {
					Pontos temp = vetor_player[x];
					vetor_player[x] = vetor_player[x + 1];
					vetor_player[x + 1] = temp;
				}
			}
			reduce++;
		}
		for (Pontos player : vetor_player) {
			System.out.println("Corredor " + (player.getId() + 1) + " ficou em " + position + " lugar com um total de " + player.getPontos() + " pontos");
			position++;
		}

	}

	public void setPontos(Pontos ponto) {
		vetor_player[index] = ponto;
		index++;
		qtd--;
	}

}
