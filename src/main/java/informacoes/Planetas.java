package informacoes;

import org.json.JSONArray;

public class Planetas {
    public static String[] coletaPlanetas (JSONArray obj, int i){
        String sNomePlaneta = obj.getJSONObject(i).get("name").toString();
        String sClima = obj.getJSONObject(i).get("climate").toString();
        String sResidents = obj.getJSONObject(i).get("residents").toString();

        String [] inf = {"Planeta: " + sNomePlaneta,
                "Clima: " + sClima,
                "Residentes: " + sResidents};

        return inf;
    }
}
