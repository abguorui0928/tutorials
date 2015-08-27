package lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	private IndexWriter writer;

	public Indexer(String indexDir) throws IOException {
		FSDirectory directory = FSDirectory.open(new File(indexDir));
		writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_4_10_1, new EnglishAnalyzer()));
	}

	public void close() throws IOException {
		writer.close();
	}

	public void index(String dataDir, FileFilter filter) throws IOException {
		File[] files = new File(dataDir).listFiles();
		for (File f : files) {
			if (!f.exists() || !f.canRead()) {
				continue;
			}
			if (f.isDirectory()) {
				index(f.getAbsolutePath(), filter);
			}
			if ((filter == null || filter.accept(f))) {
				indexFile(f);
			}
		}
	}

	private void indexFile(File f) throws IOException {
		Document document = getDocument(f);
		writer.addDocument(document);
	}

	private Document getDocument(File f) throws IOException {
		Document document = new Document();
		document.add(new TextField("contents", new FileReader(f)));
		document.add(new TextField("filename", f.getName(), Field.Store.YES));
		document.add(new TextField("fullpath", f.getCanonicalPath(), Field.Store.YES));
		return document;
	}

	private int numDocs() {
		return writer.numDocs();
	}

	public static void main(String[] args) throws IOException {
		String indexDir = "D:\\libs\\index";
		String dataDir = "D:\\libs";

		Indexer indexer = new Indexer(indexDir);
		long start = System.currentTimeMillis();

		indexer.index(dataDir, new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().toLowerCase().endsWith(".java");
			}
		});
		long end = System.currentTimeMillis();
		System.out.println("Indexing " + indexer.numDocs() + " files took " + (end - start) + " milliseconds");
		indexer.close();
	}
}
