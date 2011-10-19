package core.jsf;

import org.richfaces.ui.model.States;

public class StatesConfig {

    public States getStates() {
        States states = new States();
        
        states.setCurrentState("register");

        states.put("showEmail", Boolean.TRUE); 
        states.put("showUserPassWord", Boolean.FALSE);
        states.put("link", "Entrar no sistema."); 
        states.put("button", "Enviar");
        
        states.setNavigation("switch", "login");

        states.setCurrentState("login");
        states.put("showEmail", Boolean.FALSE);
        states.put("showUserPassWord", Boolean.TRUE);
        states.put("link", "Esqueci minha senha.");
        states.put("button", "Logar");
        
        states.setNavigation("switch", "register");
        
        return states;
    }
}