package model.logic;

import java.io.FileReader;

import com.opencsv.CSVReader;

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
	 * Retorna el primer Nodo en la cola de datos mensuales
	 * @return el primer Nodo en la cola de datos mensuales
	 */
	public Node darPrimerNodoMonthly()
	{
		return queueMonthly.darPrimerNodo();
	}

	/**
	 * Retorna el último Nodo en la cola de datos mensuales
	 * @return el último Nodo en la cola de datos mensuales
	 */
	public Node darUltimoNodoMonthly()
	{
		return queueMonthly.darUltimoNodo();
	}

	/**
	 * Retorna el Nodo superior en la pila de datos semanales
	 * @return el Nodo superior en la pila de datos semanales
	 */
	public Node darNodoSuperiorWeekly()
	{
		return stackWeekly.darNodoSuperior();
	}	

	/**
	 * Retorna el primer Nodo en la cola de datos por hora
	 * @return el primer Nodo en la cola de datos por hora
	 */
	public Node darPrimerNodoHourly()
	{
		return queueHourly.darPrimerNodo();
	}

	/**
	 * Retorna el último Nodo en la cola de datos por hora
	 * @return el último Nodo en la cola de datos por hora
	 */
	public Node darUltimoNodoHourly()
	{
		return queueHourly.darUltimoNodo();
	}

	// -------------------------------------------------------------
	// Métodos de carga de archivos
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

		CSVReader reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-" + trimestre + "-WeeklyAggregate.csv"));

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

	/**
	 * Recorre todos los datos cargados y devuelve un arreglo de dos posiciones con las zonas con menor y mayor identificador respectivamente
	 * @return un arreglo de tamaño 2 con las zonas con menor y mayor identificador
	 */
	public Double[] zonaConMenorYMayorIdentificador()
	{
		Double[] respuesta = new Double[2];
		
		double menorIdentificador = -1;
		double mayorIdentificador = -1;		
		
		Node actual = queueMonthly.darPrimerNodo();

		while(actual.darSiguente() != null)
		{
			Double[] datos = (Double[]) actual.darDato();

			if(menorIdentificador < 0 || datos[0] < menorIdentificador)
			{
				menorIdentificador = datos[0];
			}			
			if(menorIdentificador < 0 || datos[1] < menorIdentificador)
			{
				menorIdentificador = datos[1];
			}

			if(mayorIdentificador < 0 || datos[0] > mayorIdentificador)
			{
				mayorIdentificador = datos[0];
			}			
			if(mayorIdentificador < 0 || datos[1] > mayorIdentificador)
			{
				mayorIdentificador = datos[1];
			}		
			
			actual = actual.darSiguente();			
		}

		actual = stackWeekly.darNodoSuperior();

		while(actual.darSiguente() != null)
		{
			Double[] datos = (Double[]) actual.darDato();

			if(menorIdentificador < 0 || datos[0] < menorIdentificador)
			{
				menorIdentificador = datos[0];
			}			
			if(menorIdentificador < 0 || datos[1] < menorIdentificador)
			{
				menorIdentificador = datos[1];
			}

			if(mayorIdentificador < 0 || datos[0] > mayorIdentificador)
			{
				mayorIdentificador = datos[0];
			}			
			if(mayorIdentificador < 0 || datos[1] > mayorIdentificador)
			{
				mayorIdentificador = datos[1];
			}		
			
			actual = actual.darSiguente();			
		}

		actual = queueHourly.darPrimerNodo();

		while(actual.darSiguente() != null)
		{
			Double[] datos = (Double[]) actual.darDato();

			if(menorIdentificador < 0 || datos[0] < menorIdentificador)
			{
				menorIdentificador = datos[0];
			}			
			if(menorIdentificador < 0 || datos[1] < menorIdentificador)
			{
				menorIdentificador = datos[1];
			}

			if(mayorIdentificador < 0 || datos[0] > mayorIdentificador)
			{
				mayorIdentificador = datos[0];
			}			
			if(mayorIdentificador < 0 || datos[1] > mayorIdentificador)
			{
				mayorIdentificador = datos[1];
			}		
			
			actual = actual.darSiguente();			
		}
		
		respuesta[0] = menorIdentificador;
		respuesta[1] = mayorIdentificador;
 
		return respuesta;
	}

	// -------------------------------------------------------------
	// Métodos de los requerimientos
	// -------------------------------------------------------------

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