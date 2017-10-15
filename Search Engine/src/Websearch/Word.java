package Websearch;
/******************************
* Name: Jake Souza
* Date: 2/7/17
* Class: ComSci 111L
* Prof: Dr. Cates
*******************************/

public class Word implements Comparable<Word> {
	//Default "Word" object does't have a word and the count is 0
	private String name = "";
	private int count = 0;
	
	//Basic word constructor, associates the String input to the word, default count=1
	public Word(String word){
		name = word;
		count = 1;
	}
	
	//Constructs word with input name but also input count
	public Word(String word, int i){
		name = word;
		count = i;
	}
	
	//Word Setter
	public void setString(String word){
		name = word;
	}
	
	//Word Getter
	public String getString(){
		return name;
	}
	
	//Count setter
	public void setCount(int i){
		count = i;
	}
	
	//Count getter
	public int getCount(){
		return count;
	}
	
	//Will increment count by one 
	public void incrementCount(){
		count++;
	}
	
	//Will display the word along with the number of instances
	@Override
	public String toString(){
		return name + " " + count;
	}
	
	//Will see if the word is literally the same word
	@Override
	public boolean equals(Object o){
		return name.equals(((Word) o).getString());
	}
	
	//Will return a value of how much the words differ
	@Override
	public int compareTo(Word o) {
		return name.compareTo(o.getString());
	}
}
