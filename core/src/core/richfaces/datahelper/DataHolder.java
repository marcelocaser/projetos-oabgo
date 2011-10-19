package core.richfaces.datahelper;

import java.io.Serializable;
import java.util.UUID;

final class DataHolder implements Serializable {
	
    /**
     * 
     */
    private static final long serialVersionUID = -4708402152090117167L;

    private DualLRUMap<String, Object> graphMap = new DualLRUMap<String, Object>(2048);

    public Object getDataByKey(Object key){
        synchronized (graphMap) {
            return graphMap.get(key);
        }
    }
	
	public Object storeDataAndGetKey(Object data) {
		String key;
		
		synchronized (graphMap) {
			if (graphMap.containsValue(data)){
				key = graphMap.getKey(data);
			} else{
				key = UUID.randomUUID().toString();
				graphMap.put(key, data);
			}
		}
		
		return key;
	}
}