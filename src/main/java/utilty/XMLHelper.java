package utilty;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Static interface used for reading and writing XML files using {@link ActionLoggerDTO} class
 *
 * @author erik
 * @version 1.0
 */
public interface XMLHelper {

    /**
     * Read all actions from the XML file, if not found return an empty list
     * @param pathName the file to read from
     * @return ActionLoggerDTO object
     * @throws JAXBException whenever there is an issue with JAXB library
     */
    public static ActionLoggerDTO readAllActions(String pathName) throws JAXBException {

            JAXBContext jaxbContext = JAXBContext.newInstance(ActionLoggerDTO.class);
            File actionsFile = new File(pathName);

            ActionLoggerDTO actions;
            if(actionsFile.exists()) {
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                actions = (ActionLoggerDTO) unmarshaller.unmarshal(actionsFile);
            } else {
                List<String> action = new ArrayList<>();
                actions = new ActionLoggerDTO(action);
            }
            return actions;
    }

    /**
     * Read all actions from the XML file, if not found return an empty list
     * @param pathName the file to read from
     * @param actions the {@link ActionLoggerDTO} object to write to the XML file
     * @throws JAXBException whenever there is an issue with JAXB library
     * @throws IOException whenever there is an issue creating directories to the file
     */
    public static void writeNewActions(ActionLoggerDTO actions, String pathName) throws JAXBException, IOException {
        try {
            Files.createDirectories(Paths.get(pathName).getParent());
        } catch (IOException e) {
            throw new IOException("Error creating directory", e);
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(ActionLoggerDTO.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(actions, new File(pathName));
    }

    public static void writeOneAction(String action, String pathName) throws JAXBException, IOException {
        ActionLoggerDTO actions = new ActionLoggerDTO(readAllActions(pathName).getAction());
        actions.addAction(action);
        writeNewActions(actions, pathName);
    }

    public static String getCurrentDateAndTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
