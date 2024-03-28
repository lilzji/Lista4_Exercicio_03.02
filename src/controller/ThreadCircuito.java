package controller;

import java.util.concurrent.Semaphore;

import model.Pontos;
import view.Main;

public class ThreadCircuito extends Thread {

	static int pontosCorrida = 250;
	static int pontosCiclismo = 250;
	static int posicaoCorrida = 0;
	static int posicaoCiclismo = 0;
	private int distMaximaCorrida = 100;
	private int distMaximaCiclismo = 200;
	private int id;
	private int pontosAtleta;
	Semaphore semaforo;
	private Pontos ponto = new Pontos();

	public void run() {
		corrida();
		//podio();
	}

	public ThreadCircuito(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	private void corrida() {
		int distPercorrida = 0;
		while (distMaximaCorrida > 0) {
			distPercorrida += (int) ((Math.random() * 6) + 20);
			distMaximaCorrida -= distPercorrida;
			try {
				sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized (semaforo) {
	        posicaoCorrida++;
	        pontosAtleta += pontosCorrida;
			pontosCorrida -= 10;
			System.out.println(
					"Atleta " + (id + 1) + " chegou em " + posicaoCorrida + " e acumulou " + pontosAtleta + " pontos");
	    }
		semaforo();
	}

	private void semaforo() {
		// SECAO CRITICA
		try {
			semaforo.acquire();
			tirosAoAlvo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			// FIM DA SECAO CRITICA
		}
	}

	private void tirosAoAlvo() {
		int pontosTiro = 0;
		for (int i = 0; i < 3; i++) {
			pontosTiro += (int) (Math.random() * 11);
			try {
				sleep((int) ((Math.random() * 2501) + 500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pontosAtleta += pontosTiro;
		System.out.println("Atelta " + (id + 1) + " fez " + pontosTiro + " pontos nos tiros e esta com um total de "
				+ pontosAtleta + " pontos");
		ciclismo();
	}

	private void ciclismo() {
		int distPercorrida = 0;
		while (distMaximaCiclismo > 0) {
			distPercorrida += (int) ((Math.random() * 11) + 30);
			distMaximaCiclismo -= distPercorrida;
			try {
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		posicaoCiclismo++;
		pontosAtleta += pontosCiclismo;
		pontosCiclismo -= 10;
		System.out.println(
				"Atleta " + (id + 1) + " chegou em " + posicaoCiclismo + " e acumulou " + pontosAtleta + " pontos");
		enviarDados(pontosAtleta);
	}
	private void enviarDados(int pontosAtleta) {
		ponto.setId(id);
		ponto.setPontos(pontosAtleta);
		Main.controller.setPontos(ponto);
	}
}
