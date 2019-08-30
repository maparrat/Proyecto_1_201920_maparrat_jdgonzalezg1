package model.data_structures;

public interface IStack <T>
{
	/**
	 * A�ade un elemento a la pila
	 * @param item elemento a a�adir
	 */
	public void push(T item);

	/**
	 * Saca el ultimo elemento a�adido a la pila
	 * @return el ultimo elemento a�adido a la pila
	 */
	public T pop();
	
	/**
	 * Retorna el n�mero de elementos de la pila
	 * @return el n�mero de elementos de la pila
	 */
	public int darNumeroElementos();
}