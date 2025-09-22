import javax.xml.parsers.*; //XML parsers
import org.w3c.dom.*; //DOM (Document Object Model)
import java.io.File; // XML file load

public class ReadFacultyXML {
    public static void main(String[] args) {
        try {
            // XML file load
            File file = new File("faculty.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            // employee nodes
            NodeList list = doc.getElementsByTagName("employee");

            // Header
            System.out.println("ID | Name | Salary | Dept_Name | Building | Budget");

            // Rows
            for (int i = 0; i < list.getLength(); i++) {
                Element emp = (Element) list.item(i);
                String id = emp.getElementsByTagName("ID").item(0).getTextContent();
                String name = emp.getElementsByTagName("name").item(0).getTextContent();
                String salary = emp.getElementsByTagName("salary").item(0).getTextContent();
                String dept = emp.getElementsByTagName("dept_name").item(0).getTextContent();
                String building = emp.getElementsByTagName("building").item(0).getTextContent();
                String budget = emp.getElementsByTagName("budget").item(0).getTextContent();

                System.out.println(id + " | " + name + " | " + salary + " | " + dept + " | " + building + " | " + budget);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
