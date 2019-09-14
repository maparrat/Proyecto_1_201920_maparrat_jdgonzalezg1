package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.Node;
import model.data_structures.Queue;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){

			view.printMenu();

			String in;
			in = lector.next();

			int option;

			try
			{
				option = Integer.parseInt(in);
			}
			catch(NumberFormatException e)
			{
				option = 0;
			}

			switch(option)
			{
			case 1:
				int numeroTrimestre;
				try
				{
					System.out.println("--------- \nCargar archivo \nDar numero del trimestre: ");
					numeroTrimestre = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					option = 0;
					break;
				}

				if(numeroTrimestre > 0 && numeroTrimestre <= 4)
				{
					try
					{
						modelo.cargarArchivoCSVMonthly(numeroTrimestre);
						modelo.cargarArchivoCSVWeekly(numeroTrimestre);
						modelo.cargarArchivoCSVHourly(numeroTrimestre);

						System.out.println("Archivo cargado");

						System.out.println("Total de viajes en el archivo de meses: " + modelo.darTamanoMonthly());
						System.out.println("Total de viajes en el archivo de semanas: " + modelo.darTamanoWeekly());
						System.out.println("Total de viajes en el archivo de horas: " + modelo.darTamanoHourly());

						Double[] zonas = modelo.zonaConMenorYMayorIdentificador();

						System.out.println("La zona con menor identificador en todos los archivos del trimestre es: " + zonas[0]);
						System.out.println("La zona con mayor identificador en todos los archivos del trimestre es: " + zonas[1] + "\n---------");
					}
					catch (Exception e)
					{
						System.out.println("Se ha producido un error al cargar el archivo\n---------");
					}
				}
				else
				{
					System.out.println("Ingrese un valor válido (de 1 a 4)\n---------");	
				}
				break;

			case 2:
				//(1A)
				int zonaOrigen1A;
				int zonaDestino1A;
				int mes1A;
				try
				{
					System.out.println("--------- \nDar Id zona de origen: ");
					zonaOrigen1A = lector.nextInt();

					if(zonaOrigen1A < 0)
					{
						System.out.println("Debe ingresar un valor válido.\n---------");
						break;
					}						

					System.out.println("--------- \nDar Id zona de destino: ");
					zonaDestino1A = lector.nextInt();

					if(zonaDestino1A < 0)
					{
						System.out.println("Debe ingresar un valor válido.\n---------");
						break;
					}

					System.out.println("--------- \nDar numero del mes: ");
					mes1A = lector.nextInt();

					if(mes1A < 1 || mes1A > 12)
					{
						System.out.println("Debe ingresar un valor válido.\n---------");
						break;
					}
				}
				catch(InputMismatchException e)
				{
					option = 0;
					break;
				}

				Queue<Node> respuesta = modelo.consultarTiempoPromedioYDesviacionEstandarMes(zonaOrigen1A, zonaDestino1A, mes1A);

				if(respuesta.darNumeroElementos() == 0)
				{
					System.out.println("No hay viajes registrados con la información dada.\n---------");
					break;
				}
				else
				{
					Node actual = respuesta.darPrimerNodo();
					int i = 1;
					while(actual != null)
					{
						double[] datosActual = (double[]) actual.darDato();
						System.out.println("Datos del viaje " + i + ":");
						System.out.println("Tiempo promedio de viaje: " + datosActual[0]);
						System.out.println("Desviación estandar: " + datosActual[1] + "\n---------");
						
						actual = actual.darSiguente();
						i++;
					}
					
					System.out.println("Total de viajes: " + i);

				}

			case 3: 
				//(2A)

			case 4:
				//(3A)

			case 5:
				//(1B)

			case 6:
				//(2B)

			case 7:
				//(3B)

			case 8:
				//(1C)

			case 9:
				//(2C)

			case 10:
				//(3C)

			case 11: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}