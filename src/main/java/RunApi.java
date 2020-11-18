import Configuracoes.ConnectionAPI;
import javax.swing.*;

public class RunApi {

    public static void main(String[] args) throws Exception {
        Object[] opcoes = {"Personagens","Planetas","Naves","Filmes"};
        Object res = JOptionPane.showInputDialog(null, "Escolha um item" , "Selecao de itens" ,
                JOptionPane.PLAIN_MESSAGE , null ,opcoes,"");
        String sApi = ConnectionAPI.informaApi(res.toString());
        ConnectionAPI.runJob(sApi);
<<<<<<< HEAD
        System.out.println("Testando stash commit");
        System.out.println("Salvando na stash");
=======
>>>>>>> parent of 3d84ed9 (Teste revert GIT)
    }
}