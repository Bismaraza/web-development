import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;

public class ReadFacultyXML {
    public static void main(String[] args) {
        try {
            File inputFile = new File("faculty.xml"); // XML file ka path
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            // Table Header
            System.out.printf("%-8s %-12s %-8s %-15s %-10s %-8s\n",
                    "ID", "Name", "Salary", "Dept_Name", "Building", "Budget");
            System.out.println("-------------------------------------------------------------------------------");

            // Table Rows
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nNode;
                    System.out.printf("%-8s %-12s %-8s %-15s %-10s %-8s\n",
                            e.getElementsByTagName("ID").item(0).getTextContent(),
                            e.getElementsByTagName("name").item(0).getTextContent(),
                            e.getElementsByTagName("salary").item(0).getTextContent(),
                            e.getElementsByTagName("dept_name").item(0).getTextContent(),
                            e.getElementsByTagName("building").item(0).getTextContent(),
                            e.getElementsByTagName("budget").item(0).getTextContent()
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
