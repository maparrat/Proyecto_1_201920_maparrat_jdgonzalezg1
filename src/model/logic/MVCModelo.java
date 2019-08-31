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
	private Queue queueMonthly;
	
	private Queue queueHourly;

	private Stack stackWeekly;
	
	/**
	 * Constructor del modelo del mundo
	 */
	public MVCModelo()
	{
		queueMonthly = new Queue();
		queueHourly = new Queue();
		stackWeekly = new Stack();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en la cola de datos mensuales
	 * @return numero de elementos presentes en la cola de datos mensuales
	 */
	public int darTamanoMonthly()
	{
		return queueMonthly.darNumeroElementos();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en la pila de datos semanales
	 * @return numero de elementos presentes en la pila de datos semanales
	 */
	public int darTamanoWeekly()
	{
		return stackWeekly.darNumeroElementos();
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en la cola de datos por hora
	 * @return numero de elementos presentes en la cola de datos por hora
	 */
	public int darTamanoHourly()
	{
		return queueHourly.darNumeroElementos();
	}

	/**
	 * Retorna el primer dato guardado en la cola de datos mensuales
	 * @return el primer dato guardado en la cola de datos mensuales
	 */
	public Double[] darPrimerDatoMonthly()
	{
		return (Double[]) queueMonthly.darPrimerDato();
	}

	/**
	 * Retorna el último dato guardado en la cola de datos mensuales
	 * @return el último dato guardado en la cola de datos mensuales
	 */
	public Double[] darUltimoDatoMonthly()
	{
		return (Double[]) queueMonthly.darUltimoDato();
	}
	
	/**
	 * Retorna el primer dato guardado en la pila de datos semanales
	 * @return el primer dato guardado en la pila de datos semanales
	 */
	public Double[] darElementoSuperiorWeekly()
	{
		return (Double[]) queueMonthly.darPrimerDato();
	}	
	
	/**
	 * Retorna el primer dato guardado en la cola de datos por hora
	 * @return el primer dato guardado en la cola de datos por hora
	 */
	public Double[] darPrimerDatoHourly()
	{
		return (Double[]) queueHourly.darPrimerDato();
	}

	/**
	 * Retorna el último dato guardado en la cola de datos por hora
	 * @return el último dato guardado en la cola de datos por hora
	 */
	public Double[] darUltimoDatoHourly()
	{
		return (Double[]) queueHourly.darUltimoDato();
	}
	
    // -------------------------------------------------------------
    // Métodos de los requerimientos
    // -------------------------------------------------------------
	
	/**
	 * Metodo que carga el archivo CSV de datos mensuales
	 */
	public void cargarArchivoCSVMonthly(int trimestre) throws Exception
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
	}	
	
	/**
	 * Metodo que carga el archivo CSV de datos semanales
	 */
	public void cargarArchivoCSVWeekly(int trimestre) throws Exception
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
	}		
	
	/**
	 * Metodo que carga el archivo CSV de datos por hora
	 */
	public void cargarArchivoCSVHourly(int trimestre) throws Exception
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
	}
	
	public int zonaConMenorIdentificador()
	{
		int respuesta = -1;
		
		
		
		
		
		
		
		
		
		
	}
	
	public int zonaConMayorIdentificador()
	{
		int respuesta = -1;
		
		
		
		
		
		
		
		
		
		
	}	
	//Metodos proyecto
	public double consultarTiempoPromedioMes(String pzona, int pmes)
	{
		return 0;
	}
	public Queue mejoresPromediosMes(int  n, int mes )
	{
		return null; 
	}
	public String compararTiemposPromedioMes(int mes, String zonaMenor, String zonaMayor, String zonaX)
	{
		return null; 
	}
	public double consultarTiempoPromedioDia(String pzona, int pdia)
	{
		return 0;
	}
	public Queue mejoresPromediosDia(int  n, int pdia )
	{
		return null; 
	}
	public String compararTiemposPromedioDia(int dia, String zonaMenor, String zonaMayor, String zonaX)
	{
		return null; 
	}
	public Queue viejesHoras(int horaInicio, int horaFin)
	{
		return null;
	}
	public void generarTabla(String zonaA, String zonaB)
	{
		
	}

	
}