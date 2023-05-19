import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String hex = "0123456789abcdeffedcba9876543210";
        byte[] bytes = DatatypeConverter.parseHexBinary(hex);
        System.out.println(Arrays.toString(bytes));
    }
}
