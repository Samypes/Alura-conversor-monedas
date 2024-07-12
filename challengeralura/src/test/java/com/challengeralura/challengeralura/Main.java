package com.challengeralura.challengeralura;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) {

		try {
			double valorDolar = obtenerValorDolar("MXN");
			convertir("Pesos Mexicanos", valorDolar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			double valorDolar = obtenerValorDolar("COP");
			convertir("Pesos Colombianos", valorDolar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	static void convertir(String pais, double valorDolar) {
		Scanner leer = new Scanner(System.in);
		System.out.printf("Bienvenidos al conversor de monedas" )
		System.out.printf("Por favor ingrese la cantidad de %s: ", pais);
		double cantidadMoneda = leer.nextDouble();
		double dolares = cantidadMoneda / valorDolar;

		dolares = (double) Math.round(dolares * 1000) / 1000;
		System.out.println("-------------------------------------------");
		System.out.println("Tienes $" + dolares + " dólares");
	}

	private static double obtenerValorDolar(String moneda) throws Exception {
		String apiKey = "4073b38c63552cc9f5207ea2";
		String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD"+ moneda;
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");


		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		 in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        double tasaCambio = jsonResponse.getJSONObject("conversion_rates").getDouble("USD"); 

        return tasaCambio;
	}
}



