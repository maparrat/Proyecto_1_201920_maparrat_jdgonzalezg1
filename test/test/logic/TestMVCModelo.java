package test.logic;

import static org.junit.Assert.*;

import java.io.FileReader;

import model.data_structures.Node;
import model.logic.MVCModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.opencsv.CSVReader;

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

	public void setUp3()
	{
		try
		{
			modelo.cargarArchivoCSVWeekly(3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setUp4()
	{
		try
		{
			modelo.cargarArchivoCSVHourly(4);
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
		assertEquals(0, modelo.darTamanoMonthly());
	}

	@Test
	public void TestDarTamanoMonthly()
	{
		setUp2();
		assertTrue(modelo.darTamanoMonthly() > 1000000);
	}	

	@Test
	public void TestDarTamanoWeekly()
	{
		setUp3();
		assertTrue(modelo.darTamanoWeekly() > 1000000);
	}

	//@Test
	public void TestDarTamanoHourly()
	{
		setUp4();
		assertTrue(modelo.darTamanoHourly() > 1000000);	
	}

	@Test
	public void TestDarPrimerNodoMonthly()
	{
		assertTrue(modelo.darPrimerNodoMonthly() == null);
		setUp2();
		assertTrue(modelo.darPrimerNodoMonthly() != null);
	}

	@Test
	public void TestDarUltimoNodoMonthly()
	{
		assertTrue(modelo.darUltimoNodoMonthly() == null);
		setUp2();
		assertTrue(modelo.darUltimoNodoMonthly() != null);
	}

	@Test
	public void TestDarNodoSuperiorWeekly()
	{
		assertTrue(modelo.darNodoSuperiorWeekly() == null);
		setUp3();
		assertTrue(modelo.darNodoSuperiorWeekly() != null);
	}	

	//@Test
	public void TestDarPrimerNodoHourly()
	{
		assertTrue(modelo.darPrimerNodoHourly() == null);
		setUp4();
		assertTrue(modelo.darPrimerNodoHourly() != null);
	}

	//@Test
	public void TestDarUltimoNodoHourly()
	{
		assertTrue(modelo.darUltimoNodoHourly() == null);
		setUp4();
		assertTrue(modelo.darUltimoNodoHourly() != null);
	}

	//@Test
	public void TestZonaConMenorYMayorIdentificador()
	{
		setUp2();
		setUp3();
		setUp4();
		
		Double[] respuesta = modelo.zonaConMenorYMayorIdentificador();
		
		System.out.println(respuesta[0] + " - " + respuesta[1]);
		
		assertTrue(respuesta[0] != null);
		assertTrue(respuesta[1] != null);		
	}
}