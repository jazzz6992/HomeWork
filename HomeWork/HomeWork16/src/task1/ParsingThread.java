package task1;

import task1.entity.Stock;
import task1.entity.StockMarket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParsingThread extends Thread implements FileReciever {


    private File file;

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(parse());
            synchronized (this) {
                this.notifyAll();
            }
        }
    }

    @Override
    public void setFileReadyForProcess(File file) {
        this.file = file;
    }

    private StockMarket parse() {
        String type = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (type.equals("xml")) {
            return parseXML();
        } else if (type.equals("json")) {
            return parseJson();
        }
        return null;
    }

    private StockMarket parseJson() {
        StockMarket market = new StockMarket();
        market.setStocks(new ArrayList<>());
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            market.setName((String) jsonObject.get("name"));
            market.setLocation((String) jsonObject.get("location"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
            try {
                market.setDate(sdf.parse((String) jsonObject.get("date")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONArray array = (JSONArray) jsonObject.get("stock");
            for (int i = 0; i < array.size(); i++) {
                jsonObject = (JSONObject) array.get(i);
                Stock stock = new Stock();
                long id = (Long) jsonObject.get("id");
                stock.setId((int) id);
                stock.setName((String) jsonObject.get("name"));
                stock.setBid(getDouble((Number) jsonObject.get("bid")));
                stock.setMinPrice(getDouble((Number) jsonObject.get("minPrice")));
                stock.setMaxPrice(getDouble((Number) jsonObject.get("maxPrice")));
                stock.setVisible((Boolean) jsonObject.get("visible"));
                market.getStocks().add(stock);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return market;
    }

    private StockMarket parseXML() {
        Document dom = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            dom = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
        StockMarket market = new StockMarket();
        Element element = dom.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName("name");
        Node node = nodeList.item(0);
        market.setName(node.getTextContent());
        nodeList = element.getElementsByTagName("location");
        node = nodeList.item(0);
        market.setLocation(node.getTextContent());
        nodeList = element.getElementsByTagName("date");
        node = nodeList.item(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        Date date = null;
        try {
            date = sdf.parse(node.getTextContent());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        market.setDate(date);
        List<Stock> stocks = new ArrayList<>();
        market.setStocks(stocks);
        nodeList = element.getElementsByTagName("stock");
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Stock stock = new Stock();
            Element stockElement = (Element) node;
            NodeList stockNodeList = stockElement.getElementsByTagName("id");
            Node stockNode = stockNodeList.item(0);
            stock.setId(Integer.valueOf(stockNode.getTextContent()));
            stockNodeList = stockElement.getElementsByTagName("name");
            stockNode = stockNodeList.item(0);
            stock.setName(stockNode.getTextContent());
            stockNodeList = stockElement.getElementsByTagName("bid");
            stockNode = stockNodeList.item(0);
            stock.setBid(Double.valueOf(stockNode.getTextContent()));
            stockNodeList = stockElement.getElementsByTagName("minPrice");
            stockNode = stockNodeList.item(0);
            stock.setMinPrice(Double.valueOf(stockNode.getTextContent()));
            stockNodeList = stockElement.getElementsByTagName("maxPrice");
            stockNode = stockNodeList.item(0);
            stock.setMaxPrice(Double.valueOf(stockNode.getTextContent()));
            stockNodeList = stockElement.getElementsByTagName("visible");
            stockNode = stockNodeList.item(0);
            stock.setVisible(Boolean.valueOf(stockNode.getTextContent()));
            stocks.add(stock);
        }
        return market;
    }

    private Double getDouble(Number number) {
        if (number instanceof Double) {
            return (Double) number;
        } else {
            long l = (Long) number;
            Double d = (double) l;
            return d;
        }
    }
}
