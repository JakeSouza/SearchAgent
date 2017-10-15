package Websearch;
import jsjf.ArrayUnorderedList;
import jsjf.LinkedQueue;

public class WebCrawler {
	private int maxURL = 100;
	private String start = "";
	
	public WebCrawler(String url){
		start = url;
	}
	
	public WebCrawler(){
		start = "http://www.rwu.edu";
	}
	
	public String getStart(){
		return start;
	}
	
	public void setStart(String temp){
		start = temp;
	}
	
	public int getMaxURL(){
		return maxURL;
	}
	
	public void setMaxURL(int n){
		maxURL = n;
	}
	
	public void crawlAndPrint(){
		ArrayUnorderedList<String> visitedList = new ArrayUnorderedList<String>();
		LinkedQueue<String> pendingQueue = new LinkedQueue<String>();
		pendingQueue.enqueue(start);
		String nextURL = "";
		while(!pendingQueue.isEmpty() && visitedList.size() < maxURL){
			nextURL = pendingQueue.dequeue();
			if(!visitedList.contains(nextURL)){
				System.out.println(nextURL);
				visitedList.addToRear(nextURL);
			}
			URLParser parser = new URLParser(nextURL);
			for(String s: parser.getSubURLS()){
				if(!visitedList.contains(s)){
					pendingQueue.enqueue(s);
				}
			}
		}
	}
	
}
