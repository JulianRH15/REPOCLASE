package co.edu.unbosque.model.persistence;

import java.io.IOException;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.edu.unbosque.model.PeliculaDTO;
import co.edu.unbosque.model.SerieDTO;
import co.edu.unbosque.model.UsuarioDTO;

//arcquitectura cliente servidor, request-response
public class ExternalHttpRequestHandler {
	private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10)).build();
	
	public static String doGetAndParse(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Content-type", "application/json").build();
		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("status code ->" + response.statusCode());
		String uglyJson = response.body();
		return prettyPrintUsingGson(uglyJson);// Gson
	}

	public static String prettyPrintUsingGson(String uglyJson) { // hacer mas bonito el gson
		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
		JsonElement jsonElemnt = JsonParser.parseString(uglyJson);
		String prettyJsonString = gson.toJson(jsonElemnt);
		return prettyJsonString;
	}
	public static void main(String[] args) throws IOException, InterruptedException {
	//	String json = "{\r\n"
		//		+ "  \"nombre\": \"Karen\",\r\n"
		//		+ "  \"documento\": 316165165,\r\n"
			//	+ "  \"numeroPasaporte\": \"658498\",\r\n"
				//+ "  \"paisOrigen\": \"colombia\",\r\n"
			//	+ "  \"banderaOrigen\": \"kjbcejce\",\r\n"
				//+ "  \"viajeOrigen\": \"yjfjtf\",\r\n"
				//+ "  \"viajeDestino\": \"hgmgh\"\r\n"
				//+ "}"
				//;
	//	ViajeroDTO si = new ViajeroDTO();
		//System.out.println(getAllViajeros("http://localhost:8082/viajero/mostrarnombre?nombre="+"karen"));
		//System.out.println(doDelete("http://localhost:8082/viajero/eliminarporid/13"));
		
	}


	public static String doPut(String url, String json) {
		HttpRequest solicitud = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(json)).uri(URI.create(url)).header("Content-type","application/json").build();
		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta.statusCode()+"\n" + respuesta.body();
	}
	
	public static String doDelete(String url) {
		HttpRequest solicitud = HttpRequest.newBuilder().DELETE().uri(URI.create(url)).header("Content-type","application/json").build();

		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return respuesta.statusCode()+"\n" + respuesta.body();
	}
	public static ArrayList<UsuarioDTO> getAllUsuarios(String url) {
	    HttpRequest solicitud = HttpRequest.newBuilder().GET()
	            .uri(URI.create(url)).build();
	    HttpResponse<String> respuesta = null;
	    try {
	        respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    String json = respuesta.body();
	    Gson g = new Gson();
	    UsuarioDTO[] temps = g.fromJson(json, UsuarioDTO[].class);        
	    return new ArrayList<>(Arrays.asList(temps));
	}
	public static ArrayList<SerieDTO> getAllSeries(String url) {
	    HttpRequest solicitud = HttpRequest.newBuilder().GET()
	            .uri(URI.create(url)).build();
	    HttpResponse<String> respuesta = null;
	    try {
	        respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    String json = respuesta.body();
	    Gson g = new Gson();
	    SerieDTO[] temps = g.fromJson(json, SerieDTO[].class);        
	    return new ArrayList<>(Arrays.asList(temps));
	}
	public static String doPost(String url, String json) {
	    HttpRequest solicitud = HttpRequest.newBuilder()
	        .POST(HttpRequest.BodyPublishers.ofString(json))
	        .uri(URI.create(url))
	        .header("Content-type", "application/json")
	        .build();
	    
	    HttpResponse<String> respuesta = null;
	    try {
	        respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    return respuesta.statusCode() + "\n" + respuesta.body();
	}
	public static ArrayList<PeliculaDTO> getAllPeliculas(String url) {
		HttpRequest solicitud = HttpRequest.newBuilder().GET()
				.uri(URI.create(url)).build();
		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String json = respuesta.body();
		Gson g = new Gson();
		PeliculaDTO[] temps=g.fromJson(json, PeliculaDTO[].class);		
		return new ArrayList<>(Arrays.asList(temps));

	}


}
