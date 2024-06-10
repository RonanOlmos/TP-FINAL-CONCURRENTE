package modelo;

import java.util.Random;
import java.util.concurrent.RecursiveTask;


public class QuickSortMultiThreading extends RecursiveTask<Integer> {
	
	//Esta linea de codigo es por RecursiveTask que extiende de Serializable
	private static final long serialVersionUID = 1L;
	
	//Se crean las variables para manejar el array
	int start;
	int end;
	int[] arr;

	//constructor de la clase
	public QuickSortMultiThreading(int start, int end, int[] arr) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		// Caso base
		if (start >= end)
			return null;

		// Obtiene la posicion de la particion
		int p = partition(start, end, arr);

		// Divide el array en 2 subarrays
		QuickSortMultiThreading left = new QuickSortMultiThreading(start, p - 1, arr);

		QuickSortMultiThreading right = new QuickSortMultiThreading(p + 1, end, arr);

		// Lanza una tarea para ordenar el subarray de la izquierda
		left.fork();
		
		// Se ordena el subarray derecho de forma recursiva
		right.compute();

		// Se espera a que left termine
		left.join();

		// No devolvemos nada ya que no hace falta
		return null;
	}
	
	private int partition(int start, int end, int[] arr) {

		int i = start, j = end;

		// El pivote se elije de manera aleatoria
		int pivote = new Random().nextInt(j - i) + i;

		// Se cambia el pivote con el ultimo;
		int t = arr[j];
		arr[j] = arr[pivote];
		arr[pivote] = t;
		j--;

		// Se empieza a particionar
		while (i <= j) {

			if (arr[i] <= arr[end]) {
				i++;
				continue;
			}

			if (arr[j] >= arr[end]) {
				j--;
				continue;
			}

			t = arr[j];
			arr[j] = arr[i];
			arr[i] = t;
			j--;
			i++;
		}

		// Se cambia el pivote a su posicion correcta
		t = arr[j + 1];
		arr[j + 1] = arr[end];
		arr[end] = t;
		return j + 1;
	}
	

}