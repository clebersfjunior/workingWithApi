package Configuracoes;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class DisabledHostnameVerifier implements HostnameVerifier {
    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
