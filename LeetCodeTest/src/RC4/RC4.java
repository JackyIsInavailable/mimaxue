package RC4;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class RC4 {

    private final byte[] key;

    public RC4(String key) {
        this.key = key.getBytes();
    }

    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];
        int[] S = initSBox(key);
        int i = 0, j = 0;
        for (int k = 0; k < plaintext.length; k++) {
            i = (i + 1) % 256;
            j = (j + S[i]) % 256;
            swap(S, i, j);
            int t = (S[i] + S[j]) % 256;
            ciphertext[k] = (byte) (plaintext[k] ^ S[t]);
        }
        return ciphertext;
    }


    public byte[] decrypt(byte[] ciphertext) {
        return encrypt(ciphertext);
    }

    private int[] initSBox(byte[] key) {
        int[] S = new int[256];
        int j = 0;

        for (int i = 0; i < 256; i++) {
            S[i] = i;
        }

        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % key.length]) % 256;
            swap(S, i, j);
        }

        return S;
    }

    private void swap(int[] S, int i, int j) {
        int temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    public static void main(String[] args) {
//        String key = "mimaxue";
//        String plaintext = "hdtxmmx";
        Scanner kb =new Scanner(System.in);
        System.out.println("请输入明文:");
        String plaintext = kb.next();
        System.out.println("********************************************************");
        System.out.println("明文:");
        System.out.println(plaintext);
        System.out.println("明文的二进制表示:");
        System.out.println(bytesToBinaryString(plaintext.getBytes()));
        System.out.println("********************************************************");
        System.out.println("请输入密钥:");
        String key = kb.next();
        RC4 rc4 = new RC4(key);
        byte[] ciphertext = rc4.encrypt(plaintext.getBytes());
        byte[] decrypted = rc4.decrypt(ciphertext);
        System.out.println("密钥:");
        System.out.println(key);
        System.out.println("密钥的二进制表示:");
        System.out.println(bytesToBinaryString(key.getBytes()));
        System.out.println("********************************************************");

        System.out.println("加密后的二进制表示:");
        System.out.println(bytesToBinaryString(ciphertext));
        System.out.println("********************************************************");
        System.out.println("解密后的明文:");
        System.out.println(binaryToString(decrypted));
        System.out.println("解密后的二进制表示:");
        System.out.println(bytesToBinaryString(decrypted));
    }

    private static String bytesToBinaryString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));}
        return sb.toString();
    }

    public static String binaryToString(byte[] binaryBytes) {
        return new String(binaryBytes, StandardCharsets.UTF_8);
    }

}
