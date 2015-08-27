package lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {
	public static void search(String indexDir, String q) throws IOException, ParseException {
		Directory dir = FSDirectory.open(new File(indexDir));
		DirectoryReader reader = DirectoryReader.open(dir);
		IndexSearcher indexSearcher = new IndexSearcher(reader);

		QueryParser parser = new QueryParser("contents", new EnglishAnalyzer());
		Query query = parser.parse(q);
		long start = System.currentTimeMillis();
		TopDocs hits = indexSearcher.search(query, 10);
		long end = System.currentTimeMillis();
		System.out.println("Found " + hits.totalHits + "documents in " + (end - start) + " milliseconds that match query:" + q);

		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("fullpath"));
		}
		reader.close();
	}

	public static void main(String[] args) throws IOException, ParseException {
		String indexDir = "D:\\libs\\index";
		search(indexDir, "copy");
	}
}
