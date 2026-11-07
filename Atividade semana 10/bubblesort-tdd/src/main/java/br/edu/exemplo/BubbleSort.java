package br.edu.exemplo;

public class BubbleSort {

    private int[] numeros;

    public BubbleSort(int[] numeros) {
        this.numeros = numeros;
    }

    /**
     * Ordena o vetor em ordem crescente usando Bubble Sort.
     * A cada passada, o maior elemento "sobe" para o final do vetor,
     * por isso o limite da passada diminui a cada iteração.
     */
    public void ordenar() {
        for (int passada = 0; passada < numeros.length - 1; passada++) {
            boolean houveTroca = false;

            for (int i = 0; i < numeros.length - 1 - passada; i++) {
                if (numeros[i] > numeros[i + 1]) {
                    int auxiliar = numeros[i];
                    numeros[i] = numeros[i + 1];
                    numeros[i + 1] = auxiliar;
                    houveTroca = true;
                }
            }

            // Se nenhuma troca ocorreu, o vetor já está ordenado.
            if (!houveTroca) {
                break;
            }
        }
    }

    public int[] getNumeros() {
        return numeros;
    }
}
