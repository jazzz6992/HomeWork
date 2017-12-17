package task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import task1.entity.Human;
import task1.entity.Root;

import java.util.ArrayList;

public class MyHandler extends DefaultHandler {
    private Root root;
    private boolean isFileName;
    private boolean isStart;
    private Human human;
    private String thisElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        isStart = true;
        thisElement = qName;
        if (qName.equals("root")) {
            root = new Root();
            isFileName = true;
        } else if (qName.equals("people")) {
            isFileName = false;
            root.setHumans(new ArrayList<>());
        }
        if (qName.equals("element")) {
            human = new Human();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        isStart = false;
        if (qName.equals("element")) {
            root.getHumans().add(human);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isStart) {
            switch (thisElement) {
                case "name":
                    if (isFileName) {
                        root.setName(new String(ch, start, length));
                    } else {
                        human.setName(new String(ch, start, length));
                    }
                    break;
                case "age":
                    human.setAge(Integer.parseInt(new String(ch, start, length)));
                    break;
                case "id":
                    human.setId(Integer.parseInt(new String(ch, start, length)));
                    break;
                case "isDegree":
                    human.setDegree(Boolean.parseBoolean(new String(ch, start, length)));
                    break;
                case "surname":
                    human.setSurname(new String(ch, start, length));
                    break;
            }
        }
    }

    public Root getRoot() {
        return root;
    }
}
