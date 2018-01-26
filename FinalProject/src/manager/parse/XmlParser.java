package manager.parse;

import model.Model;
import model.entity.Stock;
import model.entity.StockExchange;
import manager.interfaces.ParseCompleteListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class XmlParser extends AbstractParser {
    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss X";

    XmlParser(Model model, ParseCompleteListener listener) {
        super(model, listener);
    }

    //парсит данные из файла и оповещает слушателя в случае успеха или неудачи
    @Override
    public void parse(File source) {
        StockExchange stockExchange = new StockExchange();
        Document dom;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            dom = builder.parse(source);
            Element rootElement = dom.getDocumentElement();
            NodeList curNodeList = rootElement.getElementsByTagName("name");
            String name = curNodeList.item(0).getTextContent();
            stockExchange.setName(name);
            curNodeList = rootElement.getElementsByTagName("location");
            String location = curNodeList.item(0).getTextContent();
            stockExchange.setLocation(location);
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            curNodeList = rootElement.getElementsByTagName("date");
            Date date = sdf.parse(curNodeList.item(0).getTextContent());
            stockExchange.setDate(date);
            stockExchange.setStock(new ArrayList<>());
            NodeList stockNodeList = rootElement.getElementsByTagName("stock");
            for (int i = 0; i < stockNodeList.getLength(); i++) {
                rootElement = (Element) stockNodeList.item(i);
                Stock stock = new Stock();
                int id = (int) Long.parseLong(rootElement.getElementsByTagName("id").item(0).getTextContent());
                String stockName = rootElement.getElementsByTagName("name").item(0).getTextContent();
                double bid = Double.parseDouble(rootElement.getElementsByTagName("bid").item(0).getTextContent());
                double minPrice = Double.parseDouble(rootElement.getElementsByTagName("minPrice").item(0).getTextContent());
                double maxPrice = Double.parseDouble(rootElement.getElementsByTagName("maxPrice").item(0).getTextContent());
                boolean visible = Boolean.parseBoolean(rootElement.getElementsByTagName("visible").item(0).getTextContent());
                stock.setId(id);
                stock.setName(stockName);
                stock.setBid(bid);
                stock.setMinPrice(minPrice);
                stock.setMaxPrice(maxPrice);
                stock.setVisible(visible);
                stockExchange.getStock().add(stock);
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            getListener().onParseFailed(e.getMessage());
        }
        getListener().onParseSuccess(stockExchange);
    }
}