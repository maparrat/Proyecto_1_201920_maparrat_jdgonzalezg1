package model.logic;

import java.io.FileReader;

import com.opencsv.CSVReader;

import model.data_structures.INode;
import model.data_structures.Node;
import model.data_structures.Queue;
import model.data_structures.Stack;

/**
 * Definicion del modelo del mundo
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private Stack stackWeekly;

	private Queue queueMonthly;
	
	private Queue queueHourly;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		stackWeekly = new Stack();
		queueMonthly = new Queue();
		
		queueHourly = new Queue();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoMonthly()
	{
		return queueMonthly.darNumeroElementos();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoWeekly()
	{
		return stackWeekly.darNumeroElementos();
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoHourly()
	{
		return queueHourly.darNumeroElementos();
	}
	
	/**
	 * Metodo que carga el archivo CSV
	 */
	public int cargarArchivoCSVMonthly(int trimestre) throws Exception
	{
		boolean primeraLectura = true;

		CSVReader reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-" + trimestre + "-All-MonthlyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				Double[] dato = {Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])}; 
				
				queueMonthly.enqueue(dato);
			}
			primeraLectura = false;
		}
		reader.close();
		return queueMonthly.darNumeroElementos();
	}	
	
	/**
	 * Metodo que carga el archivo CSV
	 */
	public int cargarArchivoCSVWeekly(int trimestre) throws Exception
	{
		boolean primeraLectura = true;

		CSVReader reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-" + trimestre + "-All-WeeklyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				Double[] dato = {Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])}; 
				stackWeekly.push(dato);
			}
			primeraLectura = false;
		}
		reader.close();
		return stackWeekly.darNumeroElementos();
	}		
	
	/**
	 * Metodo que carga el archivo CSV
	 */
	public int cargarArchivoCSVHourly(int trimestre) throws Exception
	{
		boolean primeraLectura = true;

		CSVReader reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-" + trimestre + "-All-HourlyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				Double[] dato = {Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])}; 
				
				queueHourly.enqueue(dato);
			}
			primeraLectura = false;
		}
		reader.close();
		return queueHourly.darNumeroElementos();
	}	

	/**
	 * Retorna el primer dato guardado
	 * @return el primer dato guardado
	 */
	public Double[] darPrimerDato()
	{
		return (Double[]) queueMonthly.darPrimerDato();
	}

	/**
	 * Retorna el último dato guardado
	 * @return el último dato guardado
	 */
	public Double[] darUltimoDato()
	{
		return (Double[]) queueMonthly.darUltimoDato();
	}

	/**
	 * Retorna una cola con el cluster más grande
	 * @param horaInicial la hora desde la que se va a hacer la búsqueda
	 * @return la cola con el cluster más grande
	 */
	public Queue darClusterMasGrande(int horaInicial)
	{
		Queue respuesta = new Queue();
		Queue actual = new Queue();
		double horaActual = horaInicial;

		while(queueMonthly.darNumeroElementos()>0)
		{
			Double[] datoActual = (Double[]) queueMonthly.dequeue();

			if(datoActual[2] >= horaActual)
			{
				actual.enqueue(datoActual);
				horaActual = datoActual[2];
			}
			else if(actual.darNumeroElementos() > respuesta.darNumeroElementos())
			{
				while(respuesta.darNumeroElementos()>0)
				{
					respuesta.dequeue();
				}

				while(actual.darNumeroElementos()>0)
				{
					respuesta.enqueue(actual.dequeue());
				}
				horaActual = horaInicial;
				
				if(datoActual[2] >= horaActual)
				{
					actual.enqueue(datoActual);
					horaActual = datoActual[2];
				}
			}
			else
			{
				while(actual.darNumeroElementos()>0)
				{
					actual.dequeue();
				}
				horaActual = horaInicial;
				
				if(datoActual[2] >= horaActual)
				{
					actual.enqueue(datoActual);
					horaActual = datoActual[2];
				}
			}	
		}

		return respuesta;
	}

	/**
	 * Retorna una cola con los últimos N viajes según la hora especificada
	 * @param numViajes N, número de viajes a buscar
	 * @param hora Hora a buscar
	 * @return una cola con los últimos viajes según la hora dada
	 */
	public Queue darUltimosViajesSegunHora(int numViajes, int hora)
	{
		Queue respuesta = new Queue();
		
		while(respuesta.darNumeroElementos() < numViajes && stackWeekly.darNumeroElementos() > 0)
		{
			Double[] datos = (Double[]) stackWeekly.pop();
			
			if(datos[2] == hora)
			{
				respuesta.enqueue(datos);
			}			
		}		
		return respuesta;
	}
}