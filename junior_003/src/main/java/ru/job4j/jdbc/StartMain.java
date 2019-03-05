package ru.job4j.jdbc;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.03.2019
 */
public class StartMain {

    private static void createTemplate(File file) throws FileNotFoundException {
        String xslt = "<?xml version=\"1.0\"?>\n"
                + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + " <xsl:template match=\"/\">\n"
                + "<entries>\n"
                + "<xsl:for-each select=\"entries/entry\">\n"
                + "<entry>\n"
                + "<xsl:attribute name=\"field\">\n"
                + "<xsl:value-of select=\"field\"/>\n"
                + "</xsl:attribute>\n"
                + "</entry>\n"
                + "</xsl:for-each>\n"
                + "</entries>\n"
                + "</xsl:template>\n"
                + "</xsl:stylesheet>";
        PrintWriter out = new PrintWriter(file.getPath());
        out.write(xslt);
        out.close();
    }

    private static void checkFileCreation(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.init();
        storeSQL.generate(1000000);

        File fileFromStoreXML = new File("fileFromStoreXML.xml");
        checkFileCreation(fileFromStoreXML);

        StoreXML storeXML = new StoreXML(fileFromStoreXML);
        storeXML.save(storeSQL.load());

        ConvertXSQT convertXSQT = new ConvertXSQT();

        File xsltTemplate = new File("template.xslt");
        checkFileCreation(xsltTemplate);
        createTemplate(xsltTemplate);

        File targetXML = new File("targetXML.xml");
        checkFileCreation(targetXML);

        convertXSQT.convert(fileFromStoreXML, targetXML, xsltTemplate);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        ParserSAX handler = new ParserSAX();
        saxParser.parse(targetXML, handler);

        System.out.println(handler.getSum());
    }
}