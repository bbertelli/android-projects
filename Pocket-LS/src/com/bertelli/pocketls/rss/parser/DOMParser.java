package com.bertelli.pocketls.rss.parser;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DOMParser {

	private RSSFeed _feed = new RSSFeed();

	public RSSFeed parseXml(String xml) {

		URL url = null;
		try {
			url = new URL(xml);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		try {
			// Create required instances
			DocumentBuilderFactory dbf;
			dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			// Parse the xml
			Document doc = db.parse(new InputSource(url.openStream()));
			doc.getDocumentElement().normalize();

			// Get all <item> tags.
			NodeList nl = doc.getElementsByTagName("item");
			int length = nl.getLength();

			for (int i = 0; i < length; i++) {
				Node currentNode = nl.item(i);
				RSSItem _item = new RSSItem();

				NodeList nchild = currentNode.getChildNodes();
				int clength = nchild.getLength();

				// Get the required elements from each Item
				for (int j = 1; j < clength; j = j + 2) {

					Node thisNode = nchild.item(j);
					String theString = null;
					String nodeName = thisNode.getNodeName();

					theString = nchild.item(j).getFirstChild().getNodeValue();

					if (theString != null) {
						if ("title".equals(nodeName)) {
							// Node name is equals to 'title' so set the Node
							// value to the Title in the RSSItem.
							_item.setTitle(theString);
						}

						else if ("content:encoded".equals(nodeName)) {
							_item.setDescription(theString);

							// Parse the html description to get the image url
							String html = theString;
							org.jsoup.nodes.Document docHtml = Jsoup
									.parse(html);
							Elements imgEle = docHtml.select("img");
							_item.setImage(imgEle.attr("src"));
						}

						else if ("pubDate".equals(nodeName)) {

							// We replace the plus and zero's in the date with
							// empty string
							String formatedDate = theString.replace(" +0000",
									"");
							formatedDate = formatedDate.replace("Mon", "Publicada Segunda-feira");
							formatedDate = formatedDate.replace("Tue", "Publicada Terça-feira");
							formatedDate = formatedDate.replace("Wed", "Publicada Quarta-feira");
							formatedDate = formatedDate.replace("Thu", "Publicada Quinta-feira");
							formatedDate = formatedDate.replace("Fri", "Publicada Sexta-feira");
							formatedDate = formatedDate.replace("Sat", "Publicada Sábado");
							formatedDate = formatedDate.replace("Sun", "Publicada Domingo");
							
							formatedDate = formatedDate.replace("Jan", "de Janeiro de");
							formatedDate = formatedDate.replace("Feb", "de Fevereiro de");
							formatedDate = formatedDate.replace("Mar", "de Março de");
							formatedDate = formatedDate.replace("Apr", "de Abril de");
							formatedDate = formatedDate.replace("May", "de Maio de");
							formatedDate = formatedDate.replace("Jun", "de Junho de");
							formatedDate = formatedDate.replace("Jul", "de Julho de");
							formatedDate = formatedDate.replace("Aug", "de Agosto de");
							formatedDate = formatedDate.replace("Sep", "de Setembro de");
							formatedDate = formatedDate.replace("Nov", "de Outubro de");
							formatedDate = formatedDate.replace("Dec", "de Novembro de");
							formatedDate = formatedDate.replace("Jan", "de Dezembro de");
							
							_item.setDate(formatedDate);
						}

					}
				}

				// add item to the list
				_feed.addItem(_item);
			}

		} catch (Exception e) {
		}

		// Return the final feed once all the Items are added to the RSSFeed
		// Object(_feed).
		return _feed;
	}

}
