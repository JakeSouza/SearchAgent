package Websearch;

import jsjf.LinkedBinarySearchTree;
import jsjf.exceptions.ElementNotFoundException;

public class URLWords {
	private String URLName;
	private LinkedBinarySearchTree<Word> holder = new LinkedBinarySearchTree<Word>();
	
	public URLWords(String name){
		URLName = name;
	}
	
	public void addWord(String w){
		Word temp = new Word(w);
		if(holder.contains(temp) == false){
			holder.addElement(temp);
		}
		else{
			holder.find(temp).incrementCount();
		}
	}
	
	public void addWord(Word w){
		if(holder.contains(w) == false){
			holder.addElement(w);
		}
		else{
			holder.find(w).incrementCount();
		}
	}
	
	public boolean contains(String w){
		Word temp = new Word(w);
		try{
	    	holder.find(temp);
    	}catch(ElementNotFoundException e){
    	return false;
    	}
    	return true;
	}
	
	public int getFrequency(String w){
		int count = 0;
		Word temp = new Word(w);
		count = holder.find(temp).getCount();
		return count;
	}
	
	public String toString(){
		String printer = "";
		for(Word s: holder){
			printer += s.toString();
		}
		return printer;
	}
	
	public String getName(){
		return URLName;
	}
	
}
