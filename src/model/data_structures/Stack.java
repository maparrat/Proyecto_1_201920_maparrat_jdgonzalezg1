package model.data_structures;

public class Stack<T> implements IStack<T>
{
	private int numeroElementos;
	private Node top;

	public Stack()
	{
		numeroElementos = 0;
	}

	public void push(T item)
	{
		if(top == null)
		{
			top = new Node();
			top.asignarDato(item);			
		}
		else
		{
			Node nuevo = new Node();
			nuevo.asignarDato(item);
			nuevo.asignarSiguiente(top);
			top = nuevo;
		}
		numeroElementos++;
	}

	public T pop() 
	{
		T respuesta = (T) top.darDato();

		top = top.darSiguente();
		numeroElementos --;

		return respuesta;
	}

	public int darNumeroElementos()
	{
		return numeroElementos;
	}

	public Node darNodoSuperior()
	{
		return top;
	}
}