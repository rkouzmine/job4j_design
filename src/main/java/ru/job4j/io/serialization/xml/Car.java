package ru.job4j.io.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringWriter;
import java.util.Arrays;
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean isElectric;
    private String model;
    private int year;
    private Engine engine;
    @XmlElementWrapper(name = "features")
    @XmlElement(name = "feature")
    private String[] features;

    public Car() { }

    public Car(boolean isElectric, String model, int year, Engine engine, String[] features) {
        this.isElectric = isElectric;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isElectric=" + isElectric
                + ", model='" + model + '\''
                + ", year=" + year
                + ", engine=" + engine
                + ", features=" + Arrays.toString(features)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Car car = new Car(false, "Geely Coolray", 2022,
                new Engine(170), new String[]{"Air Conditioning", "Navigation System"});

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
