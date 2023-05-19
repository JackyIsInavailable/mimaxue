package Caesar;

public class Caesar {
    public static void main(String[] args) {
        String message = "Hello, World!";
        int key = 3;
        String encryptedMessage = encrypt(message, key);
        System.out.println("密文: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("解密后: " + decryptedMessage);
    }

    public static String encrypt(String message, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    char encryptedCh = (char) ((ch - 'A' + key) % 26 + 'A');
                    encryptedText.append(encryptedCh);
                } else {
                    char encryptedCh = (char) ((ch - 'a' + key) % 26 + 'a');
                    encryptedText.append(encryptedCh);
                }
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String encryptedMessage, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char ch = encryptedMessage.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    char decryptedCh = (char) ((ch - 'A' - key + 26) % 26 + 'A');
                    decryptedText.append(decryptedCh);
                } else {
                    char decryptedCh = (char) ((ch - 'a' - key + 26) % 26 + 'a');
                    decryptedText.append(decryptedCh);
                }
            } else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }
}
