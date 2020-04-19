package com.liteworm.javaLearn.demos.xmlTest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

/**
 * @ClassName CreateXml
 * @Decription
 * 测试创建xml
 * @AUthor LiteWorm
 * @Date 2020/4/12 20:35
 * @Version 1.0
 **/
public class CreateXml {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("Languages");
        root.setAttribute("cat", "it");

        Element lan1 = document.createElement("lan");
        root.setAttribute("id", "1");

        Element name1 = document.createElement("name");
        name1.setTextContent("Java");
        Element ide1 = document.createElement("ide");
        ide1.setTextContent("Eclips");

        lan1.appendChild(name1);
        lan1.appendChild(ide1);
        root.appendChild(lan1);
        document.appendChild(root);

        System.out.println(document.toString());

        //获取xml对象
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StringWriter stringWriter = new StringWriter();
        //生成为一个字符串
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        System.out.println(stringWriter.toString());
        //生成到文件
       transformer.transform(new DOMSource(document), new StreamResult(new File("./testxml.xml") ));
    }




}