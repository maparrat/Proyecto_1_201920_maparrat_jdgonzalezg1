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
				double[] dato = {Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])}; 

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
				double[] dato = {Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])}; 
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
				double[] dato = {Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])}; 

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
	public double[] zonaConMenorYMayorIdentificador()
	{
		double[] respuesta = new double[2];

		double menorIdentificador = -1;
		double mayorIdentificador = -1;		

		Node actual = queueMonthly.darPrimerNodo();

		while(actual.darSiguente() != null)
		{
			double[] datos = (double[]) actual.darDato();

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
			double[] datos = (double[]) actual.darDato();

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
			double[] datos = (double[]) actual.darDato();

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

	//(1A)
	public Queue consultarTiempoPromedioYDesviacionEstandarMes(int pZonaOrigen, int pZonaDestino, int pMes)
	{
		Queue viajes = new Queue();

		Node actual = queueMonthly.darPrimerNodo();

		while(actual != null)
		{
			double[] datosActual = (double[]) actual.darDato();

			if(datosActual[0] == pZonaOrigen && datosActual[1] == pZonaDestino && datosActual[2] == pMes)
			{
				viajes.enqueue(datosActual);
			}

			actual = actual.darSiguente();
		}
		return viajes;
	}

	//(2A)
	public Queue mejoresPromediosMes(int N, int mes)
	{
		return null; 
	}

	//(3A)
	public Queue darViajesOrdenadosEnUnRangoDeZonasMes(int zona, int pZonaMenor, int pZonaMayor, int pMes)
	{
		Queue viajes = new Queue();

		Node actual = queueMonthly.darPrimerNodo();

		while(actual != null)
		{
			double[] datosActual = (double[]) actual.darDato();

			if(datosActual[2] == pMes)
			{
				if(datosActual[0] == zona && (datosActual[1] >= pZonaMenor && datosActual [1] <= pZonaMayor))
				{
					viajes.enqueue(datosActual);
				}
				else if(datosActual[1] == zona && (datosActual[0] >= pZonaMenor && datosActual [0] <= pZonaMayor))
				{
					viajes.enqueue(datosActual);
				}
			}

			actual = actual.darSiguente();
		}

		Node[] temp = new Node[viajes.darNumeroElementos()];

		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = viajes.darPrimerNodo();
			viajes.dequeue();
		}

		//Ordenamiento por inserción
		for (int i = 0; i < temp.length; i++)
		{
			boolean enPos = false;
			for (int j = i; j > 0 && !enPos; j--)
			{
				double[] datos1 = (double[]) temp[j].darDato();
				double[] datos2 = (double[]) temp[j-1].darDato();

				double key1 = datos1[0]!=zona?datos1[0]:datos1[1];
				double key2 = datos2[0]!=zona?datos2[0]:datos2[1];

				if(key1 < key2)
				{
					Node copia = temp[j-1];
					temp[j-1] = temp[j];
					temp[j] = copia;
				}
				else
				{
					enPos = true;
				}
			}
		}

		for (int i = 0; i < temp.length; i++)
		{
			viajes.enqueue(temp[i].darDato());
		}

		return viajes;		 
	}

	//(1B)
	public Stack consultarTiempoPromedioYDesviacionEstandarDia(int pZonaOrigen, int pZonaDestino, int pDia)
	{
		Stack viajes = new Stack();

		Node actual = stackWeekly.darNodoSuperior();

		while(actual != null)
		{
			double[] datosActual = (double[]) actual.darDato();

			if(datosActual[0] == pZonaOrigen && datosActual[1] == pZonaDestino && datosActual[2] == pDia)
			{
				viajes.push(datosActual);
			}

			actual = actual.darSiguente();
		}
		return viajes;
	}

	//(2B)
	public Stack mejoresPromediosDia(int  n, int pdia )
	{
		return null; 
	}

	//(3B)
	public Queue darViajesOrdenadosEnUnRangoDeZonasDia(int zona, int pZonaMenor, int pZonaMayor, int pDia)
	{
		Queue viajes = new Queue();

		Node actual = stackWeekly.darNodoSuperior();

		while(actual != null)
		{
			double[] datosActual = (double[]) actual.darDato();

			if(datosActual[2] == pDia)
			{
				if(datosActual[0] == zona && (datosActual[1] >= pZonaMenor && datosActual [1] <= pZonaMayor))
				{
					viajes.enqueue(datosActual);
				}
				else if(datosActual[1] == zona && (datosActual[0] >= pZonaMenor && datosActual [0] <= pZonaMayor))
				{
					viajes.enqueue(datosActual);
				}
			}

			actual = actual.darSiguente();
		}

		Node[] temp = new Node[viajes.darNumeroElementos()];

		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = viajes.darPrimerNodo();
			viajes.dequeue();
		}

		//Ordenamiento por inserción
		for (int i = 0; i < temp.length; i++)
		{
			boolean enPos = false;
			for (int j = i; j > 0 && !enPos; j--)
			{
				double[] datos1 = (double[]) temp[j].darDato();
				double[] datos2 = (double[]) temp[j-1].darDato();

				double key1 = datos1[0]!=zona?datos1[0]:datos1[1];
				double key2 = datos2[0]!=zona?datos2[0]:datos2[1];

				if(key1 < key2)
				{
					Node copia = temp[j-1];
					temp[j-1] = temp[j];
					temp[j] = copia;
				}
				else
				{
					enPos = true;
				}
			}
		}

		for (int i = 0; i < temp.length; i++)
		{
			viajes.enqueue(temp[i].darDato());
		}

		return viajes;	
	}

	//(1C)
	public Queue consultarTiempoPromedioYDesviacionEstandarFranjaHoraria(int pZonaOrigen, int pZonaDestino, int horaInicio, int horaFin)
	{
		Queue viajes = new Queue();

		Node actual = queueHourly.darPrimerNodo();

		while(actual != null)
		{
			double[] datosActual = (double[]) actual.darDato();

			if(datosActual[0] == pZonaOrigen && datosActual[1] == pZonaDestino && datosActual[2] >= horaInicio && datosActual[2] <= horaFin)
			{
				viajes.enqueue(datosActual);
			}

			actual = actual.darSiguente();
		}
		return viajes;		
	}

	//(2C)
	public Queue mejoresPromediosHora(int horaInicio, int horaFin)
	{
		return null;
	}

	//(3C)
	public void generarTabla(String zonaA, String zonaB)
	{

	}

	// -------------------------------------------------------------
	// Métodos auxiliares
	// -------------------------------------------------------------

	public String[] generarMensajesComparacion(int pZona, Queue<double[]> pViajesX)
	{
		String[] mensajes = new String[pViajesX.darNumeroElementos()];

		Node viajeXActual = pViajesX.darPrimerNodo();

		for (int i = 0; i < mensajes.length; i++)
		{
			double[] datosActual = (double[]) viajeXActual.darDato();

			double tiempoPromedio1 = buscarTiempoPromedioDeViajeEntreZonas(pZona, (int)datosActual[1], pViajesX);
			double tiempoPromedio2 = buscarTiempoPromedioDeViajeEntreZonas((int)datosActual[1], pZona, pViajesX);

			if(tiempoPromedio1 == -1 && tiempoPromedio2 == -1)
			{			
				double tiempoPromedio1aux = buscarTiempoPromedioDeViajeEntreZonas(pZona, (int)datosActual[0], pViajesX);
				double tiempoPromedio2aux = buscarTiempoPromedioDeViajeEntreZonas((int)datosActual[0], pZona, pViajesX);

				if(tiempoPromedio1aux != -1 || tiempoPromedio2aux != -1)
				{
					if(tiempoPromedio1aux == -1)
					{
						mensajes[i] = "No hay viajes de " + pZona + " a " + (int)datosActual[0] + " vs " + tiempoPromedio2aux + " de " + (int)datosActual[0] + " a " + pZona;
					}
					else if(tiempoPromedio2aux == -1)
					{
						mensajes[i] = tiempoPromedio1aux + " de " + pZona + " a " + (int)datosActual[0] + " vs No hay viajes de " + (int)datosActual[0] + " a " + pZona;
					}
					else
					{
						mensajes[i] = tiempoPromedio1aux + " de " + pZona + " a " + (int)datosActual[0] + " vs " + tiempoPromedio2aux + " de " + (int)datosActual[0] + " a " + pZona;
					}
				}
			}
			else if(pZona != datosActual[1])
			{
				if(tiempoPromedio1 == -1)
				{
					mensajes[i] = "No hay viajes de " + pZona + " a " + (int)datosActual[1] + " vs " + tiempoPromedio2 + " de " + (int)datosActual[1] + " a " + pZona;
				}
				else if(tiempoPromedio2 == -1)
				{
					mensajes[i] = tiempoPromedio1 + " de " + pZona + " a " + (int)datosActual[1] + " vs No hay viajes de " + (int)datosActual[1] + " a " + pZona;
				}
				else
				{
					mensajes[i] = tiempoPromedio1 + " de " + pZona + " a " + (int)datosActual[1] + " vs " + tiempoPromedio2 + " de " + (int)datosActual[1] + " a " + pZona;
				}
			}
			else
			{
				if(tiempoPromedio1 == -1)
				{
					mensajes[i] = "No hay viajes de " + pZona + " a " + (int)datosActual[0] + " vs " + tiempoPromedio2 + " de " + (int)datosActual[0] + " a " + pZona;
				}
				else if(tiempoPromedio2 == -1)
				{
					mensajes[i] = tiempoPromedio1 + " de " + pZona + " a " + (int)datosActual[0] + " vs No hay viajes de " + (int)datosActual[0] + " a " + pZona;
				}
				else
				{
					mensajes[i] = tiempoPromedio1 + " de " + pZona + " a " + (int)datosActual[0] + " vs " + tiempoPromedio2 + " de " + (int)datosActual[0] + " a " + pZona;
				}
			}

			viajeXActual = viajeXActual.darSiguente();
		}

		return mensajes;
	}

	public double buscarTiempoPromedioDeViajeEntreZonas(int pZonaOrigen, int pZonaDestino, Queue<double[]> pViajesX)
	{
		double respuesta = -1;

		Node viajeXActual = pViajesX.darPrimerNodo();

		while(respuesta == -1 && viajeXActual != null)
		{
			double[] viajeActual = (double[]) viajeXActual.darDato();

			if(viajeActual[0] == pZonaOrigen && viajeActual[1] == pZonaDestino)
			{
				respuesta = viajeActual[3];
			}

			viajeXActual = viajeXActual.darSiguente();
		}

		return respuesta;
	}
}