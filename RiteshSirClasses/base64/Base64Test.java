public class Base64Test {
    public static void main(String[] args) {
        Base64 base64 = new Base64();

        String original = "Man";
        String encoded = base64.encode(original);
        System.out.println("Encoded: " + encoded); 
        String decoded = base64.decode(encoded);
        System.out.println("Decoded: " + decoded);
	
        String original2 = "Ma";
        String encoded2 = base64.encode(original2);
        System.out.println("Encoded: " + encoded2);
        String decoded2 = base64.decode(encoded2);
        System.out.println("Decoded: " + decoded2);

        String original3 = "";
        String encoded3 = base64.encode(original3);
        System.out.println("Encoded: " + encoded3);
        String decoded3 = base64.decode(encoded3);
        System.out.println("Decoded: " + decoded3);
	
	String original4 = "Hello, World!";
        String encoded4 = base64.encode(original4);
        System.out.println("Encoded: " + encoded4);
        String decoded4 = base64.decode(encoded4);
        System.out.println("Decoded: " + decoded4);

	String original5 = "AB\tC\nD";
        String encoded5 = base64.encode(original5);
        System.out.println("Encoded: " + encoded5);
        String decoded5 = base64.decode(encoded5);
        System.out.println("Decoded: " + decoded5);


    }
}

