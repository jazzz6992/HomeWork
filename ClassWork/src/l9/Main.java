package l9;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import l9.entity.Gender;
import l9.entity.People;
import l9.entity.Root;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String linkJson = "http://kiparo.ru/t/test.json";
        String linkXML = "http://kiparo.ru/t/test.xml";
        File file = new File("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/ClassWork/src/l9/newfile.json");
        URL url = new URL(linkJson);
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
//            parseXml();
            System.out.println(parseJson());
            System.out.println(parseGson());
        }
    }

    //    public static Root parseGson() {
//        Root root = null;
//        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/ClassWork/src/l9/newfile.json"))) {
//            Gson gson = new Gson();
//            root = gson.fromJson(reader, Root.class);
//            return root;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return root;
//    }
    public static Root parseGson() {
        Root root = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/ClassWork/src/l9/newfile.json"))) {
            GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Date.class, new DateGsonConverter());
            Gson gson = builder.create();
            root = gson.fromJson(reader, Root.class);
            return root;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static Root parseJson() throws IOException, ParseException {
        Root root = new Root();
        root.setPeople(new ArrayList<>());
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("/Users/vsevolodvisnevskij/IdeaProjects/HomeWork/ClassWork/src/l9/newfile.json")) {
            JSONObject jsonRoot = (JSONObject) parser.parse(reader);
            String fileName = (String) jsonRoot.get("name");
            root.setName(fileName);
            long genderId = (Long) jsonRoot.get("gender");
            Gender gender = Gender.values()[(int) genderId];
            root.setGender(gender);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse((String) jsonRoot.get("date"));
            root.setDate(date);
            JSONArray array = (JSONArray) jsonRoot.get("people");
            for (int i = 0; i < array.size(); i++) {
                JSONObject person = (JSONObject) array.get(i);
                long age = (long) person.get("age");
                long id = (long) person.get("id");
                boolean isDegree = (Boolean) person.get("isDegree");
                String name = (String) person.get("name");
                String surname = (String) person.get("surname");
                People people = new People();
                people.setDegree(isDegree);
                people.setId((int) id);
                people.setAge((int) age);
                people.setFirstName(name);
                people.setSurname(surname);
                root.getPeople().add(people);
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return root;
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
            cur.setFirstName(name);
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