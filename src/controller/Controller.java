package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.Node;
import model.data_structures.Queue;
import model.data_structures.Stack;
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
						//modelo.cargarArchivoCSVHourly(numeroTrimestre);

						System.out.println("Archivo cargado");

						System.out.println("Total de viajes en el archivo de meses: " + modelo.darTamanoMonthly());
						System.out.println("Total de viajes en el archivo de semanas: " + modelo.darTamanoWeekly());
						System.out.println("Total de viajes en el archivo de horas: " + modelo.darTamanoHourly());

						double[] zonas = modelo.zonaConMenorYMayorIdentificador();

						System.out.println("La zona con menor identificador en todos los archivos del trimestre es: " + zonas[0]);
						System.out.println("La zona con mayor identificador en todos los archivos del trimestre es: " + zonas[1] + "\n---------");
					}
					catch (Exception e)
					{
						System.out.println("Se ha producido un error al cargar los archivos\n---------");
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

					System.out.println("--------- \nDar Id zona de destino: ");
					zonaDestino1A = lector.nextInt();

					System.out.println("--------- \nDar numero del mes: ");
					mes1A = lector.nextInt();

					if(mes1A < 1 || mes1A > 12)
					{
						System.out.println("Debe ingresar un valor válido (entre 1 y 12).\n---------");
						break;
					}
				}
				catch(InputMismatchException e)
				{
					option = 0;
					break;
				}

				Queue<double[]> respuesta1A = modelo.consultarTiempoPromedioYDesviacionEstandarMes(zonaOrigen1A, zonaDestino1A, mes1A);

				if(respuesta1A.darNumeroElementos() == 0)
				{
					System.out.println("No hay viajes registrados con la información dada.\n---------");
					break;
				}
				else
				{
					int i = 1;

					System.out.println("---------\nTotal de viajes: " + respuesta1A.darNumeroElementos() + "\n---------");

					while(respuesta1A.darNumeroElementos() > 0)
					{
						double[] datosActual = respuesta1A.dequeue();

						System.out.println("Datos del viaje " + i + ":");
						System.out.println("Tiempo promedio de viaje: " + datosActual[3]);
						System.out.println("Desviación estandar: " + datosActual[4] + "\n---------");

						i++;
					}
				}

				break;

			case 3: 
				//(2A)

			case 4:
				//(3A)

				int zona3A;
				int zonaMenor3A;
				int zonaMayor3A;
				int mes3A;
				try
				{
					System.out.println("--------- \nDar Id zona a comparar: ");
					zona3A = lector.nextInt();					

					System.out.println("--------- \nDar Id zona menor del rango X: ");
					zonaMenor3A = lector.nextInt();

					System.out.println("--------- \nDar Id zona mayor del rango X: ");
					zonaMayor3A = lector.nextInt();

					System.out.println("--------- \nDar numero del mes: ");
					mes3A = lector.nextInt();

					if(mes3A < 1 || mes3A > 12)
					{
						System.out.println("Debe ingresar un valor válido (entre 1 y 12).\n---------");
						break;
					}
				}
				catch(InputMismatchException e)
				{
					option = 0;
					break;
				}

				Queue<double[]> viajesX3A = modelo.darViajesOrdenadosEnUnRangoDeZonasMes(zona3A, zonaMenor3A, zonaMayor3A, mes3A);
				
				if(viajesX3A.darNumeroElementos() == 0)
				{
					System.out.println("No hay viajes registrados en el rango de zonas dado.\n---------");
					break;
				}
				else
				{
					String[] mensajes = modelo.generarMensajesComparacion(zona3A, viajesX3A);

					for (int i = 0; i < mensajes.length; i++)
					{
						if(i == 0 || !mensajes[i].equals(mensajes[i-1]))
							System.out.println(mensajes[i]);
					}
				}

				System.out.println("---------");

				break;

			case 5:
				//(1B)
				int zonaOrigen1B;
				int zonaDestino1B;
				int dia1B;
				try
				{
					System.out.println("--------- \nDar Id zona de origen: ");
					zonaOrigen1B = lector.nextInt();					

					System.out.println("--------- \nDar Id zona de destino: ");
					zonaDestino1B = lector.nextInt();

					System.out.println("--------- \nDar numero del día de la semana: ");
					dia1B = lector.nextInt();

					if(dia1B < 1 || dia1B > 7)
					{
						System.out.println("Debe ingresar un valor válido (entre 1 y 7).\n---------");
						break;
					}
				}
				catch(InputMismatchException e)
				{
					option = 0;
					break;
				}

				Stack<double[]> respuesta1B = modelo.consultarTiempoPromedioYDesviacionEstandarDia(zonaOrigen1B, zonaDestino1B, dia1B);

				if(respuesta1B.darNumeroElementos() == 0)
				{
					System.out.println("No hay viajes registrados con la información dada.\n---------");
					break;
				}
				else
				{
					int i = 1;

					System.out.println("---------\nTotal de viajes: " + respuesta1B.darNumeroElementos() + "\n---------");

					while(respuesta1B.darNumeroElementos() > 0)
					{
						double[] datosActual = respuesta1B.pop();

						System.out.println("Datos del viaje " + i + ":");
						System.out.println("Tiempo promedio de viaje: " + datosActual[3]);
						System.out.println("Desviación estandar: " + datosActual[4] + "\n---------");

						i++;
					}
				}

				break;

			case 6:
				//(2B)

			case 7:
				//(3B)

				int zona3B;
				int zonaMenor3B;
				int zonaMayor3B;
				int dia3B;
				try
				{
					System.out.println("--------- \nDar Id zona a comparar: ");
					zona3B = lector.nextInt();					

					System.out.println("--------- \nDar Id zona menor del rango X: ");
					zonaMenor3B = lector.nextInt();

					System.out.println("--------- \nDar Id zona mayor del rango X: ");
					zonaMayor3B = lector.nextInt();

					System.out.println("--------- \nDar numero del día de la semana: ");
					dia3B = lector.nextInt();

					if(dia3B < 1 || dia3B > 7)
					{
						System.out.println("Debe ingresar un valor válido (entre 1 y 7).\n---------");
						break;
					}
				}
				catch(InputMismatchException e)
				{
					option = 0;
					break;
				}

				Queue<double[]> viajesX3B = modelo.darViajesOrdenadosEnUnRangoDeZonasDia(zona3B, zonaMenor3B, zonaMayor3B, dia3B);

				if(viajesX3B.darNumeroElementos() == 0)
				{
					System.out.println("No hay viajes registrados en el rango de zonas dado.\n---------");
					break;
				}
				else
				{
					String[] mensajes = modelo.generarMensajesComparacion(zona3B, viajesX3B);

					for (int i = 0; i < mensajes.length; i++)
					{
						if(i == 0 || !mensajes[i].equals(mensajes[i-1]))
							System.out.println(mensajes[i]);
					}
				}

				System.out.println("---------");

				break;

			case 8:
				//(1C)
				int zonaOrigen1C;
				int zonaDestino1C;
				int horaInicial1C;
				int horaFinal1C;
				try
				{
					System.out.println("--------- \nDar Id zona de origen: ");
					zonaOrigen1C = lector.nextInt();					

					System.out.println("--------- \nDar Id zona de destino: ");
					zonaDestino1C = lector.nextInt();

					System.out.println("--------- \nDar numero del mes: ");
					horaInicial1C = lector.nextInt();

					if(horaInicial1C < 0 || horaInicial1C > 23)
					{
						System.out.println("Debe ingresar un valor válido (entre 0 y 23).\n---------");
						break;
					}

					System.out.println("--------- \nDar numero del mes: ");
					horaFinal1C = lector.nextInt();

					if(horaFinal1C < 0 || horaFinal1C > 23)
					{
						System.out.println("Debe ingresar un valor válido (entre 0 y 23).\n---------");
						break;
					}
				}
				catch(InputMismatchException e)
				{
					option = 0;
					break;
				}

				Queue<double[]> respuesta1C = modelo.consultarTiempoPromedioYDesviacionEstandarFranjaHoraria(zonaOrigen1C, zonaDestino1C, horaInicial1C, horaFinal1C);

				if(respuesta1C.darNumeroElementos() == 0)
				{
					System.out.println("No hay viajes registrados con la información dada.\n---------");
					break;
				}
				else
				{
					int i = 1;

					System.out.println("---------\nTotal de viajes: " + respuesta1C.darNumeroElementos() + "\n---------");

					while(respuesta1C.darNumeroElementos() > 0)
					{
						double[] datosActual = respuesta1C.dequeue();

						System.out.println("Datos del viaje " + i + ":");
						System.out.println("Tiempo promedio de viaje: " + datosActual[3]);
						System.out.println("Desviación estandar: " + datosActual[4] + "\n---------");

						i++;
					}
				}

				break;

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