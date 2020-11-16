package informacoes;

import org.json.JSONArray;

import javax.swing.*;

public class Filmes {
    public static String[] coletaFilmes (JSONArray obj, int i){
        String sNomeFilme = obj.getJSONObject(i).get("title").toString();
        String sEpisodio = obj.getJSONObject(i).get("episode_id").toString();
        String sPersonagem = obj.getJSONObject(i).get("characters").toString();
        String sPlaneta = obj.getJSONObject(i).get("planets").toString();
        String sStarships = obj.getJSONObject(i).get("starships").toString();

        String [] inf = {"Filme: " + sNomeFilme,
                "Episodio: " + sEpisodio,
                "Personagens do Filme: " + sPersonagem,
                "Planetas: " + sPlaneta,
                "Naves: " + sStarships};

        return inf;
    }
}
