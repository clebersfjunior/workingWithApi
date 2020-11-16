package informacoes;

import org.json.JSONArray;

public class Naves {
    public static String[] coletaNaves (JSONArray obj, int i){
        String sNomeNave = obj.getJSONObject(i).get("name").toString();
        String sModelo = obj.getJSONObject(i).get("model").toString();
        String sFabricante = obj.getJSONObject(i).get("manufacturer").toString();
        String sFilme = obj.getJSONObject(i).get("films").toString();

        String [] inf = {"Nave: " + sNomeNave,
                "Modelo: " + sModelo,
                "Fabricante: " + sFabricante,
                "Informações de Filmes: " + sFilme};

        return inf;
    }
}
