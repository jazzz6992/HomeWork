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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FindAllByKey {
    private String key;
    Document document;

    public void makeXml(String[] arr) throws TransformerException, IOException {
        key = arr[arr.length - 2];
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            Element element = document.createElement("files");
            document.appendChild(element);
            for (int i = 0; i < arr.length - 2; i++) {
                process(new File(arr[i]));
            }
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            DOMSource source = new DOMSource(document);
            File newFile = new File(arr[arr.length - 1]);
            newFile.createNewFile();
            StreamResult result = new StreamResult(newFile);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void process(File current) throws IOException {
        if (current.isDirectory()) {
            for (File f :
                    current.listFiles()) {
                process(f);
            }
        } else if (current.isFile()) {
            int idx = current.getName().lastIndexOf(".");
            if (idx != -1 && current.getName().toLowerCase().substring(idx).equals(key)) {
                Element element = document.getDocumentElement();
                Element file = document.createElement("file");
                Element name = document.createElement("name");
                name.setTextContent(current.getName());
                Element path = document.createElement("path");
                path.setTextContent(current.getParent());
                Element size = document.createElement("size");
                size.setTextContent(String.valueOf(current.length()));
                BasicFileAttributes attributes = Files.readAttributes(Paths.get(current.getAbsolutePath()), BasicFileAttributes.class);
                Element date = document.createElement("date");
                date.setTextContent(attributes.creationTime() + "");
                file.appendChild(name);
                file.appendChild(path);
                file.appendChild(size);
                file.appendChild(date);
                element.appendChild(file);
            }
        }
    }

    public static void main(String[] args) throws TransformerException, IOException {
        FindAllByKey findAllByKey = new FindAllByKey();
        findAllByKey.makeXml(args);
    }
}
