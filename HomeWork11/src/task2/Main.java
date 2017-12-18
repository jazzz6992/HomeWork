package task2;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import task2.entity.Human;
import task2.entity.Root;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String link = "http://kiparo.ru/t/test.xml";
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                File file = new File("src/test.xml");
                boolean created;
                if (!(created = file.exists())) {
                    created = file.createNewFile();
                }
                if (created) {
                    try (InputStream is = connection.getInputStream()) {
                        try (OutputStream os = new FileOutputStream(file)) {
                            byte[] buf = new byte[2048];
                            int bufSize;
                            while ((bufSize = is.read(buf)) != -1) {
                                os.write(buf, 0, bufSize);
                            }
                        }
                    }
                    System.out.println(parseXML(file));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static Root parseXML(File file) throws ParserConfigurationException, IOException, SAXException {
        Root root = new Root();
        root.setHumans(new ArrayList<>());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Element element = document.getDocumentElement();
        NodeList list = element.getElementsByTagName("name");
        Node node = list.item(0);
        root.setName(node.getTextContent());
        list = element.getElementsByTagName("people");
        node = list.item(0);
        list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            element = (Element) list.item(i);
            int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
            int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            boolean isDegree = Boolean.parseBoolean(element.getElementsByTagName("isDegree").item(0).getTextContent());
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            String surname = element.getElementsByTagName("surname").item(0).getTextContent();
            Human human = new Human();
            human.setAge(age);
            human.setId(id);
            human.setDegree(isDegree);
            human.setName(name);
            human.setSurname(surname);
            root.getHumans().add(human);
        }
        return root;
    }
}