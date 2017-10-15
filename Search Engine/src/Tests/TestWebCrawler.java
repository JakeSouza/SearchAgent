package Tests;

import Websearch.WebCrawler;

/** Test program for WebCrawler class*/
public class TestWebCrawler {

	/**Crawl acm.org or the 110 textbook author's site and find 10 
	 * subULRs.
	 * @param args
	 */
	public static void main(String[] args) {
            //String URLString = "http://www.acm.org";
            //String URLString = "http://www.cs.armstrong.edu/liang";
            String URLString = "http://www.rwu.edu";

            WebCrawler w = new WebCrawler(URLString);
            w.setMaxURL(10);
            w.crawlAndPrint();
                
	}

}
