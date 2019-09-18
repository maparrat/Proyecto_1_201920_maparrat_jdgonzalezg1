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

				System.out.println("--------- \nIngresar numero de mes a consultar los mejores viajes: ");
				int mes = Integer.parseInt(lector.next());

				System.out.println("--------- \nIngresar la cantidad de datos que desea consultar: ");
				int tamaño = Integer.parseInt(lector.next());

				model.data_structures.Queue respuesta = modelo.mejoresPromediosMes(tamaño, mes);

				System.out.println("--------- \nLos mejores viajes son: ");

				for(int i = 0; i < respuesta.darNumeroElementos(); i++)
				{
					UBERTrip y = (UBERTrip) respuesta.dequeue();
					double[] datos = y.darDatosViaje();
					System.out.println("--------- \n Su origen fue " + datos[0]+" , Su destino fue " + datos[1]+ " , Su tiempo promedio fue " + datos[3] + " , Su desviacion estandar fue " + datos[4]);	
				}

				break;

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

					for (int j = 0; j < mensajes.length; j++)
					{
						if(j == 0 || !mensajes[j].equals(mensajes[j-1]))
							System.out.println(mensajes[j]);
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
					int k = 1;

					System.out.println("---------\nTotal de viajes: " + respuesta1B.darNumeroElementos() + "\n---------");

					while(respuesta1B.darNumeroElementos() > 0)
					{
						double[] datosActual = respuesta1B.pop();

						System.out.println("Datos del viaje " + k + ":");
						System.out.println("Tiempo promedio de viaje: " + datosActual[3]);
						System.out.println("Desviación estandar: " + datosActual[4] + "\n---------");

						k++;
					}
				}

				break;

			case 6:
				
				System.out.println("--------- \nIngresar numero de dia a consultar los mejores viajes: ");
				int dia = Integer.parseInt(lector.next());
				
				System.out.println("--------- \nIngresar la cantidad de datos que desea consultar: ");
				int tamañoD = Integer.parseInt(lector.next());
				
				model.data_structures.Stack respuestaD = modelo.mejoresPromediosDia(tamañoD, dia);
				System.out.println("--------- \nLos mejores viajes son   : ");
				
				UBERTrip[] x = new UBERTrip[respuestaD.darNumeroElementos()];
				
				for(int l = 0; l < respuestaD.darNumeroElementos(); l++)
				{
					x[l] = (UBERTrip) respuestaD.pop();
				}
				
				for (int m = x.length; m>0; m--)
				{
					double[] datos = x[i].darDatosViaje();
					System.out.println("---------\n Su origen fue " + datos[0] + " , Su destino fue " + datos[1] + " , Su tiempo promedio fue " + datos[3] + " , Su desviacion estandar fue " + datos[4]);	
				}
				
				break;

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

					for (int n = 0; n < mensajes.length; n++)
					{
						if(n == 0 || !mensajes[n].equals(mensajes[n-1]))
							System.out.println(mensajes[n]);
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
					int o = 1;

					System.out.println("---------\nTotal de viajes: " + respuesta1C.darNumeroElementos() + "\n---------");

					while(respuesta1C.darNumeroElementos() > 0)
					{
						double[] datosActual = respuesta1C.dequeue();

						System.out.println("Datos del viaje " + o + ":");
						System.out.println("Tiempo promedio de viaje: " + datosActual[3]);
						System.out.println("Desviación estandar: " + datosActual[4] + "\n---------");

						o++;
					}
				}

				break;

			case 9:
				
				System.out.println("--------- \nIngresar la hora a consultar los mejores viajes: ");
				int hora = Integer.parseInt(lector.next());
				
				System.out.println("--------- \nIngresar la cantidad de datos que desea consultar: ");
				int tamañoH = Integer.parseInt(lector.next());
				
				model.data_structures.Queue respuestaH = modelo.mejoresPromediosMes(tamañoH, hora);
				
				System.out.println("--------- \nLos mejores viajes son: ");
				
				for (int p = 0; p < respuestaH.darNumeroElementos(); p++)
				{
					UBERTrip z = (UBERTrip) respuestaH.dequeue();
					double[] datos = z.darDatosViaje();
					System.out.println("---------\n Su origen fue " + datos[0] + " , Su destino fue " + datos[1] + " , Su tiempo promedio fue " + datos[3] + " , Su desviacion estandar fue " + datos[4]);	
				}
				
				break;

			case 10:
				//(3C)
				
				System.out.println("--------- \nIngresar numero de zona de origen que desea reporte: ");
				int zonaA = Integer.parseInt(lector.next());
				
				System.out.println("--------- \nIngresar numero de zona de destino que desea reporte: ");
				int zonaB = Integer.parseInt(lector.next());
				
				int[] arreglo = modelo.generarTabla(zonaA, zonaB);
				
				System.out.println("--------- \nAproximación en minutos de viajes entre zona origen y zona destino: ");
				System.out.println("--------- \nTrimestre " + numeroTrimestre + " del 2018 detallado por cada hora del día ");
				System.out.println("--------- \nZona de origen: " + zonaA );
				System.out.println("--------- \nZona de destino: " + zonaB );
				System.out.println("--------- \nHora| # de minutos ");
				
				for(int q = 0; q<arreglo.length;q++)
				{
					String asteriscos = null;
					if(arreglo[q] == -1)
					{
						System.out.println("---------\n 0" + q + "|Hora sin viajes");
					}
					else
					{
						for(int r= 0; r < arreglo[q];r++)
						{
							if (asteriscos == null)
							{
								asteriscos= "*";
							}
							else
							{
								asteriscos = asteriscos + "*";
							}

						}
						System.out.println("---------\n 0" + q + "|" + asteriscos);
					}
				}

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