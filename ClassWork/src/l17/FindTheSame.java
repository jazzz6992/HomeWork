package l17;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindTheSame {
    private Map<String, Set<File>> map = new HashMap<>();
    private Document document = null;

    public void process(String[] arr) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
        Element files = document.createElement("files");
        document.appendChild(files);
        for (String s :
                arr) {
            add(new File(s));
        }
        makeXML();
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        DOMSource source = new DOMSource(document);
        File newFile = new File("thesame.xml");
        newFile.createNewFile();
        StreamResult result = new StreamResult(newFile);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public void add(File file) {
        if (file.isDirectory()) {
            for (File f :
                    file.listFiles()) {
                add(f);
            }
        } else if (file.isFile()) {
            int idx = file.getName().lastIndexOf(".");
            if (idx != -1) {
                String key = file.getName().toLowerCase().substring(idx);
                if (!map.containsKey(key)) {
                    Set<File> set = new HashSet<>();
                    set.add(file);
                    map.put(key, set);
                } else {
                    map.get(key).add(file);
                }
            }
        }
    }

    public void makeXML() {
        Element root = document.getDocumentElement();
        for (Map.Entry<String, Set<File>> e :
                map.entrySet()) {
            Element element = document.createElement("ext");
            element.setAttribute("name", e.getKey());
            for (File f :
                    e.getValue()) {
                Element file = document.createElement("file");
                file.setTextContent(f.getAbsolutePath());
                element.appendChild(file);
            }
            root.appendChild(element);
        }
    }

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        FindTheSame findTheSame = new FindTheSame();
        findTheSame.process(args);
    }
}
