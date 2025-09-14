
import java.util.*;
public class hello {
    public static void main(String[] args) {


        Pen pen2 = new Pen();
        pen2.color = "black";
        pen2.type = "ballpoint";
        pen2.printColor();
        pen2.write();
        
    }
   static class Pen{
        String color;
        String type;

        public void write(){
            System.out.println("Writing something");
        }

        public void printColor(){
            System.out.println(this.color);
        }
    }
}