package test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import modelo.QuickSort;
import modelo.QuickSortMultiThreading;
 
public class Test  {

    public static void main(String args[])
    {
    	
    	int max = 10000;
    	int min = -10000;
       	 
    	int n = 7000000;
        int[] arr = new int[n];
		
        Random random = new Random();
        
		for(int i = 0; i<n;i++) {
			// Se carga el vector con valores aleatorios
			arr[i] = random.nextInt(max-min+1)+min;
		}
		
		// Se crea la copia del array
		int arrayCopiaQuick[] = Arrays.copyOf(arr, arr.length);
		
		double tiempoInicial, tiempoFinal;

		
		try (ForkJoinPool pool = new ForkJoinPool()) {//Es un try-with-resorce el cual cierra el recurso una vez termine de usarlo
				
			tiempoInicial = System.nanoTime(); // Se toma el tiempo 
				
			//ejecuta la tarea en el ForkJoinPool que pueden ejecutarse en paralelo utilizando los recursos del sistema de manera eficiente.
			pool.invoke(new QuickSortMultiThreading(0, n - 1, arr));
    
			tiempoFinal = System.nanoTime()- tiempoInicial;  //Se toma el tiempo final       
			System.out.println("Se tardo " + tiempoFinal/1000000 + "ms Concurrente"); // Se muestra cuanto tardo
		}
		
		tiempoInicial = System.nanoTime(); // Se guarda el tiempo inicial
     
    	QuickSort.quickSort(arrayCopiaQuick, 0, n-1); // Se inicia el quick sort secuencial
    	
    	tiempoFinal = System.nanoTime()- tiempoInicial;	 //Se toma el tiempo final
    	System.out.println("Se tardo " + tiempoFinal/1000000 + "ms Secuencial"); // Se muestra cuanto tardo
      
    }
}