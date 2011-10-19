package core.richfaces.datahelper;


import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public final class SessionDataHelper {

    private static final String DATA_HOLDER_ATTRIBUTE_NAME = DataHolder.class.getName();

	private SessionDataHelper() {}
   
    private static DataHolder getGraphDataHolder() {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        ExternalContext externalContext = fc.getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Object session = externalContext.getSession(true);
        DataHolder holder;
        synchronized (session) {
            holder = (DataHolder) sessionMap.get(DATA_HOLDER_ATTRIBUTE_NAME);
            if(holder == null){
                holder = new DataHolder();
                sessionMap.put(DATA_HOLDER_ATTRIBUTE_NAME, holder);
            }
        }
        return holder;
    }

    public static Object getDataByKey(Object key) {
        DataHolder holder = getGraphDataHolder();
        
        return holder.getDataByKey(key);
    }

    public static Object storeDataAndGetKey(Object data) {
        DataHolder holder = getGraphDataHolder();
        
        return holder.storeDataAndGetKey(data);
    }

}
