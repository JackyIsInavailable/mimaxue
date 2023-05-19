package RSA;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    public static void main(String[] args) {
        BigInteger p = getPrime();
        BigInteger q = p;
        while (p.equals(q)) {
            q = getPrime();
        }
        // 生成公钥和私钥
        KeyPair publicKeyPair = generatePublicKey(p, q);
        KeyPair privateKeyPair = generatePrivateKey(p, q);

        System.out.println("输入明文:");
        String text = "Hello, World!";
        BigInteger[] encryptedMessage = encrypt(text, publicKeyPair);
        System.out.println("密文:");
        for (BigInteger encryptedChar : encryptedMessage) {
            System.out.print(encryptedChar + " ");
        }

        String decryptedMessage = decrypt(encryptedMessage, privateKeyPair);
        System.out.println("\n解密后:");
        System.out.println(decryptedMessage);
    }

    // 获取一个大质数
    public static BigInteger getPrime() {
        Random random = new Random();
        BigInteger prime;
        do {
            prime = new BigInteger(1024, random);
        } while (!prime.isProbablePrime(50));
        return prime;
    }

    // 扩展欧几里的算法
    public static ExtendedEuclideanResult extendedEuclidean(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            BigInteger x = BigInteger.ONE;
            BigInteger y = BigInteger.ZERO;
            BigInteger r = a;
            return new ExtendedEuclideanResult(r, x, y);
        } else {
            ExtendedEuclideanResult prevResult = extendedEuclidean(b, a.mod(b));
            BigInteger x = prevResult.getY();
            BigInteger y = prevResult.getX().subtract(a.divide(b).multiply(prevResult.getY()));
            BigInteger r = prevResult.getR();
            return new ExtendedEuclideanResult(r, x, y);
        }
    }

    // 求两个数字的最大公约数（欧几里得算法）
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        } else {
            return gcd(b, a.mod(b));
        }
    }

    // 生成公钥
    public static KeyPair generatePublicKey(BigInteger p, BigInteger q) {
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537); // 选择65537作为公钥指数
        return new KeyPair(n, e);
    }

    // 生成私钥
    public static KeyPair generatePrivateKey(BigInteger p, BigInteger q) {
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537); // 选择65537作为公钥指数
        ExtendedEuclideanResult result = extendedEuclidean(e, phi);
        BigInteger d = result.getX();
        if (d.compareTo(BigInteger.ZERO) < 0) {
            d = d.add(phi);
        }
        return new KeyPair(n, d);
    }

    // 加密
    public static BigInteger[] encrypt(String message, KeyPair publicKey) {
        byte[] bytes = message.getBytes();
        BigInteger[] encryptedMessage = new BigInteger[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            BigInteger m = BigInteger.valueOf(bytes[i]);
            encryptedMessage[i] = m.modPow(publicKey.getExponent(), publicKey.getModulus());
        }
        return encryptedMessage;
    }

    // 解密
    public static String decrypt(BigInteger[] encryptedMessage, KeyPair privateKey) {
        byte[] decryptedBytes = new byte[encryptedMessage.length];
        for (int i = 0; i < encryptedMessage.length; i++) {
            BigInteger c = encryptedMessage[i];
            BigInteger m = c.modPow(privateKey.getExponent(), privateKey.getModulus());
            decryptedBytes[i] = m.byteValue();
        }
        return new String(decryptedBytes);
    }
}

class KeyPair {
    private BigInteger modulus;
    private BigInteger exponent;

    public KeyPair(BigInteger modulus, BigInteger exponent) {
        this.modulus = modulus;
        this.exponent = exponent;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public BigInteger getExponent() {
        return exponent;
    }
}

class ExtendedEuclideanResult {
    private BigInteger r;
    private BigInteger x;
    private BigInteger y;

    public ExtendedEuclideanResult(BigInteger r, BigInteger x, BigInteger y) {
        this.r = r;
        this.x = x;
        this.y = y;
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }
}
