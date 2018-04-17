package com.ipartek.formacion.nidea.ejemplos;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParseJsoup {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();

		System.out.println("Titulo" + title);
		Elements anclas = doc.getElementsByTag("a");
		for (Element ancla : anclas) {
			String urlPagina2 = ancla.attr("href");
			Document doc2 = Jsoup.connect(urlPagina2).get();
			System.out.println("Titulo pagina: " + doc2.title());
		}

	}

}
