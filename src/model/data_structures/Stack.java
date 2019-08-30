package model.data_structures;

public class Stack<T> implements IStack<T>
{
	private T[] elementos;
	private int tamanio;
	private int numeroElementos;
	private T top;

	public Stack()
	{
		tamanio = 1;
		elementos = (T[]) new Object[tamanio];
		numeroElementos = 0;
	}

	public void push(T item)
	{
		if(numeroElementos == tamanio)
		{
			tamanio = tamanio*2;	

			T[] copia = elementos;
			elementos = (T[]) new Object[tamanio];
			for(int i = 0; i < numeroElementos; i++)
			{
				elementos[i] = copia[i];
			} 
		}

		elementos[numeroElementos] = item;
		top = item;
		numeroElementos++;
	}

	public T pop() 
	{
		T respuesta = top;

		elementos[numeroElementos-1] = null;
		if(numeroElementos>1)
		{
			top = elementos[numeroElementos-2];
		}
		else
		{
			top = null;
		}
		
		numeroElementos --;

		if(numeroElementos <= (tamanio/4))
		{
			tamanio = tamanio/2;	

			T[] copia = elementos;
			elementos = (T[]) new Object[tamanio];
			for(int i = 0; i < numeroElementos; i++)
			{
				elementos[i] = copia[i];
			} 
		}

		return respuesta;		
	}

	public int darNumeroElementos()
	{
		return numeroElementos;
	}
}