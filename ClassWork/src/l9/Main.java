package l9;


import l9.entity.People;
import l9.entity.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String link = "http://kiparo.ru/t/test.xml";
        File file = new File("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/ClassWork/src/l9/newfile.html");
        URL url = new URL(link);
        //почему не обойтись без HttpURLConnection. в URL есть метод openStream();
        //что вообще такое URL и HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (InputStream inputStream = connection.getInputStream()) {
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    int bufSize;
                    byte[] buf = new byte[1024];
                    //почему не работает через availaible???
                    while ((bufSize = inputStream.read(buf)) != -1) {
                        outputStream.write(buf, 0, bufSize);
                    }
                }
            }
            parseXml();
        }
    }

    public static void parseXml() {
        Document dom = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            dom = builder.parse("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/ClassWork/src/l9/newfile.html");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Root root = new Root();
        Element rootElement = dom.getDocumentElement();
        System.out.println("root tag name =  " + rootElement.getTagName());
        NodeList nameNodeList = rootElement.getElementsByTagName("name");
        Node nameNode = nameNodeList.item(0);
        System.out.println("name tag name + " + nameNode.getNodeName());
        String nameFile = nameNode.getTextContent();
        System.out.println("content tag name = " + nameFile);
        root.setName(nameFile);
        NodeList peopleList = rootElement.getElementsByTagName("people");
        Node peopleNode = peopleList.item(0);
        NodeList elementsNodeList = peopleNode.getChildNodes();
        List<People> people = new ArrayList<>();
        for (int i = 0; i < elementsNodeList.getLength(); i++) {
            Node node = elementsNodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            String surname = element.getElementsByTagName("surname").item(0).getTextContent();
            int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
            int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            Boolean isDegree = Boolean.valueOf(element.getElementsByTagName("name").item(0).getTextContent());
            People cur = new People();
            cur.setName(name);
            cur.setSurname(surname);
            cur.setAge(age);
            cur.setId(id);
            cur.setDegree(isDegree);
            people.add(cur);
        }
        root.setPeople(people);
        System.out.println(root.toString());
    }
}
