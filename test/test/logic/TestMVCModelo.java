package test.logic;

import static org.junit.Assert.*;
import model.logic.MVCModelo;

import org.junit.Before;
import org.junit.Test;

public class TestMVCModelo
{
	private MVCModelo modelo;

	@Before
	public void setUp1()
	{
		modelo= new MVCModelo();
	}

	public void setUp2()
	{
		try
		{
			modelo.cargarArchivoCSVMonthly(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testMVCModelo()
	{
		assertTrue(modelo != null);
		assertEquals(0, modelo.darTamanoMonthly());  // Cola con 0 elementos presentes.
	}

	@Test
	public void testDarTamano()
	{
		setUp2();
		assertTrue(modelo.darTamanoMonthly() > 0);  // Cola con más de 0 elementos presentes.
		assertTrue(modelo.darTamanoWeekly() > 0);  // Pila con 0 elementos presentes.
		assertTrue(modelo.darTamanoHourly() > 0);  // Cola con 0 elementos presentes.		
	}
}