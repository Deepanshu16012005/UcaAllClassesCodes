import java.util.*;
class RandomizedSet {
    HashMap<Integer,Integer> map;
    List<Integer> list;
    public RandomizedSet() {
        map = new HashMap<>();
	list = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(!map.containsKey(val)){
	    int index= list.size();
	    list.add(list.size(),val);
	    map.put(val,index);
	    return true;
	}
	return false;
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val)){
	    int index = map.get(val);
	    int lastElement = list.get(list.size()-1);
	    list.set(index , lastElement);
	    map.put(lastElement,index);
	    list.remove(list.size()-1);
	    map.remove(val);
	    return true;
	}
	return false;
    }
    public int getRandom() {
        int randomIndex = (int)(Math.random() * list.size());
        return list.get(randomIndex);
    }
}

