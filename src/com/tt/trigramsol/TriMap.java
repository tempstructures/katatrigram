package com.tt.trigramsol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TriMap {
    protected HashMap<String, List<String>> map = 
          new HashMap<String, List<String>>();
    
    protected HashMap<String, Integer> counterValueMap = 
            new HashMap<String, Integer>();

    public String get(String key) { 
    	//If multiple values, track record
    	if(map.get(key).size()>1) {
    		//if already read
    		if(counterValueMap.containsKey(key)) {
    			int readCounter = counterValueMap.get(key).intValue()+1;
    			if(map.get(key).size() >= readCounter) {
    				counterValueMap.put(key, readCounter);
        			return map.get(key).get(readCounter).toString();
    			} else {
    				//reset counter
    				counterValueMap.put(key, 1);
        			return map.get(key).get(0).toString();
    			}
    		} else {
    			counterValueMap.put(key, 1);
    			return map.get(key).get(0).toString();
    		}
    	} else {
    		if(map.get(key).size()==1) {
    			return map.get(key).get(0).toString();
    		} else {
    			return null;
    		}
    	}
    }

    public void put(String key, String value) {
       if (map.containsKey(key)) {
    	   //just add value
    	   map.get(key).add(value);
//    	   counterValueMap.put(key, counterValueMap.get(key).intValue()+1);
       } else {
         map.put(key, new ArrayList<String>(Arrays.asList(value)));
//         counterValueMap.put(key, 1);
       }
    }
    // other methods goes here
    
    public void print() {
    	//Read the hashmap
	    Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println("["+pair.getKey()+"]" + " : [" + pair.getValue()+"]");
	        it.remove(); // avoids a ConcurrentModificationException
	    }
    }
 }
