package informacoes;

import org.json.JSONArray;

public class Personagens {
    public static String[] coletaPersonagens (JSONArray obj, int i){
        String sNomePersonagem = obj.getJSONObject(i).get("name").toString();
        String sFilmes = obj.getJSONObject(i).get("films").toString();
        String sPlanetas = obj.getJSONObject(i).get("homeworld").toString();

        String [] inf = {"Personagem: " + sNomePersonagem,
                "Filme: " + sFilmes,
                "Planeta: " + sPlanetas};

        return inf;
    }
}
