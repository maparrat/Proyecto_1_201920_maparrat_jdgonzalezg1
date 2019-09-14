package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {}
	    
		public void printMenu()
		{
			System.out.println("1. Cargar archivos del semestre indicado");
			System.out.println("2. Consultar el tiempo promedio de viaje y su desviaci�n est�ndar de los viajes entre una zona de origen y una zona destino para un mes dado");
			System.out.println("3. Consultar la informaci�n de los N viajes con mayor tiempo promedio para un mes dado");
			System.out.println("4. Comparar los tiempos promedios de los viajes para una zona dada contra cada zona X en un rango de zonas dado para un mes dado");
			System.out.println("5. Consultar el tiempo promedio de viaje y su desviaci�n est�ndar de los viajes entre una zona de origen y una zona destino para un d�a dado de la semana");
			System.out.println("6. Consultar la informaci�n de los N viajes con mayor tiempo promedio para un d�a dado");
			System.out.println("7. Comparar los tiempos promedios de los viajes para una zona dada contra cada zona X en un rango de zonas dado para un d�a dado");
			System.out.println("8. Consultar los viajes entre una zona de origen y una zona destino en una franja horaria dada");
			System.out.println("9. Consultar la informaci�n de los N viajes con mayor tiempo promedio para una hora dada");
			System.out.println("10. Generar una gr�fica ASCII con el tiempo promedio de los viajes entre una zona origen y una zona destino para cada hora del d�a");
			System.out.println("11. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
}