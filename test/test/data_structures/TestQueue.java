package test.data_structures;


import static org.junit.Assert.assertEquals;



import org.junit.Test;

import model.data_structures.Queue;
import model.data_structures.Stack;

public class TestQueue 
{
	private Queue queue;

	public void setUp1() 
	{
		queue = new Queue();
		queue.enqueue("Hola");
	}
	
	public void setUp2() 
	{
		int x = 1;
		int y = 2;
		queue= new Queue();
		queue.enqueue(x);
		queue.enqueue(y);
	}
	
	@Test
	public void testDarNumeroElementos()
	{
		setUp1();
		assertEquals(1, queue.darNumeroElementos());
	}
	
	@Test
	public void testDequeue()
	{
		setUp2();
		
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.dequeue());
	}
}