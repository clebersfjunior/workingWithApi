package Configuracoes;

import informacoes.Filmes;
import informacoes.Naves;
import informacoes.Personagens;
import informacoes.Planetas;
import org.json.JSONArray;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.stream.Collectors;

public class ConnectionAPI {
    public static BufferedReader conectaApi (String sConsulta) throws Exception {
        URL url = new URL("https://swapi.dev/api/"+ sConsulta +"/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        HttpsURLConnection sslConnection = (HttpsURLConnection) connection;
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new TrustEverythingManager()}, new SecureRandom());
        sslConnection.setHostnameVerifier(new DisabledHostnameVerifier());
        sslConnection.setSSLSocketFactory(sslContext.getSocketFactory());
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type","application/json");
        connection.setRequestProperty("Accept", "*/*");
        connection.setDoOutput(true);
        int code = connection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((code == 200 || code == 201) ? connection.getInputStream() : connection.getErrorStream()));
        return bufferedReader;
    }

    public static String informaApi (String res){
        switch (res){
            case "Personagens":
                res = "people";
                break;
            case "Planetas":
                res = "planets";
                break;
            case "Naves":
                res = "starships";
                break;
            case "Filmes":
                res = "films";
                break;
        }
        return res;
    }

    public static void runJob(String sConsulta) throws Exception {
        BufferedReader bufferedReader = ConnectionAPI.conectaApi(sConsulta);
        String result = bufferedReader.lines().collect(Collectors.joining("\n"));
        if (!result.equalsIgnoreCase("[]")) {
            JSONArray obj = new JSONArray(result.substring(result.indexOf('[')));
            int i = obj.length();

            String [] inf = {};
            for (i = i - 1; i >= 0; i--) {
                switch (sConsulta) {
                    case "people":
                        inf = Personagens.coletaPersonagens(obj, i);
                        break;
                    case "planets":
                        inf = Planetas.coletaPlanetas(obj, i);
                        break;
                    case "starships":
                        inf = Naves.coletaNaves(obj, i);
                        break;
                    case "films":
                        inf = Filmes.coletaFilmes(obj, i);
                        break;
                }
                JOptionPane.showMessageDialog (null, inf);
            }
        }
    }
}
