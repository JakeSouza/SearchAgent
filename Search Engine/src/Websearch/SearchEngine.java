package Websearch;

import java.util.HashMap;

import jsjf.ArrayUnorderedList;
import jsjf.PriorityQueue;

public class SearchEngine {
	ArrayUnorderedList<URLWords> store = new ArrayUnorderedList<URLWords>();
	final int MAX_SEARCH = 10;
	HashMap<String, String> cache = new HashMap<String, String>();
	
	public SearchEngine(ArrayUnorderedList<URLWords> temp){
		store = temp;
	}
	
	public String search(String s){
		String[] temp = s.split("\\s+");
		bubbleSort(temp);
		String key = "";
		for(String p: temp){
			key += (p + " ");
		}
		if(cache.containsKey(key)){
			return (cache.get(key) + "\nQuery found in cache");
		}
		else{
			PriorityQueue<String> p = new PriorityQueue<String>();
			Word w;
			for(URLWords u: store){
				int total = 0;
				for(String x: temp){
					if(u.contains(x)){
						w = new Word(x);
						total += u.getFrequency(x);
					}
				}
				if(total != 0){
					p.addElement(u.getName(), (-1)*total);
				}
			}
			String result = "";
			for(int i = 0; i < MAX_SEARCH; i++){
				result += (p.removeNext() + "\n");
			}
			cache.put(key, result);
			return (result + "\nQuery not found in cache");
		}
	}
	
	public void bubbleSort(String list[]){
		String temp;
		do {
				temp = list[0];
				for (int j=1; j<list.length; j++){
					if (list[j-1].compareTo(list[j])>0){
						temp = list[j];
						list[j] = list[j-1];
						list[j-1] = temp;
					}
				}
		} while (temp != list[0]);
	}
}
