package ru.job4j.jdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.03.2019
 */
public class StoreXML {
    private File file;

    public StoreXML(File file) {
        this.file = file;
    }

    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsage.Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(
                    new XmlUsage.Entries(list.stream().map(e -> new XmlUsage.Entry(e.getField())).collect(Collectors.toList())),
                    this.file
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}