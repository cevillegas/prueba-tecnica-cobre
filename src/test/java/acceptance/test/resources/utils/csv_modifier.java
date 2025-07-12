package acceptance.test.resources.utils;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class csv_modifier {
    private static final String EXPIRATION_DATE_VALUE = "2024-04-20";
    private static final String HEADER_NAME = "Fecha de vencimiento";

    public static boolean modifyExpirationDate(String filePath) {
        if (!filePath.endsWith("recaudoTemplateCaracteresEspeciales.csv") &&
                !filePath.endsWith("recaudoTemplateDatosCorrectos.csv")) {
            return false;
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Leer encabezado y encontrar el index a modificar
        String header = lines.get(0);
        String[] headers = header.split(",");
        int indexToModify = 0;

        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(HEADER_NAME)) {
                indexToModify = i;
                break;
            }
        }

        // Modificar el campo en cada línea (a partir de la 2da)
        List<String> modifiedLines = new ArrayList<>();
        modifiedLines.add(header);

        for (int i = 1; i < lines.size(); i++) {
            String[] cols = lines.get(i).split(",");
            cols[indexToModify] = EXPIRATION_DATE_VALUE;
            modifiedLines.add(String.join(",", cols));
        }

        try {
            Files.write(Paths.get(filePath), modifiedLines);
            System.out.println("Archivo modificado exitosamente: " + filePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
