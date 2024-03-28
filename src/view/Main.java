package view;

import java.util.concurrent.Semaphore;

import controller.PontosController;
import controller.ThreadCircuito;

public class Main {

	private static int corredores = 25;
	public static PontosController controller = new PontosController(corredores);
	public static void main(String[] args) {
		int permissao = 5;
		Semaphore semaforo = new Semaphore(permissao);
		for(int id = 0; id < 25; id++) {
			Thread threadCircuito = new ThreadCircuito(id, semaforo);
			threadCircuito.start();
		}
		controller.start();
	}
}
