package model.data_structures;

public interface IStack <T>
{
	/**
	 * Añade un elemento a la pila
	 * @param item elemento a añadir
	 */
	public void push(T item);

	/**
	 * Saca el ultimo elemento añadido a la pila
	 * @return el ultimo elemento añadido a la pila
	 */
	public T pop();
	
	/**
	 * Retorna el número de elementos de la pila
	 * @return el número de elementos de la pila
	 */
	public int darNumeroElementos();
	
	/**
	 * Retorna el elemento que está en la parte de arriba de la pila
	 * @return el último elemento agregado a la pila
	 */
	public T darElementoSuperior();
}