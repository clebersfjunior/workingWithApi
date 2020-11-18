import Configuracoes.ConnectionAPI;
import javax.swing.*;

public class RunApi {

    public static void main(String[] args) throws Exception {
        Object[] opcoes = {"Personagens","Planetas","Naves","Filmes"};
        Object res = JOptionPane.showInputDialog(null, "Escolha um item" , "Selecao de itens" ,
                JOptionPane.PLAIN_MESSAGE , null ,opcoes,"");
        String sApi = ConnectionAPI.informaApi(res.toString());
        ConnectionAPI.runJob(sApi);
        System.out.println("Testando stash commit");
    }
}