package DES;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DesTestWithJava {

    private static int length;

    public static final int[] BEGIN = new int[] { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54,
            46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
            11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 }; // 64位
    public static final int[] END = new int[] { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46,
            14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59,
            27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 }; // 64位

    public static final int[] PC_1 = new int[] { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51,
            43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53,
            45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };

    public static final int[] PC_2 = new int[] { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16,
            7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36,
            29, 32 };

    public static final int[] E = new int[] { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 6, 9, 10, 11, 12, 13, 12, 13, 14, 15,
            16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };

    public static final int[] P = new int[] { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14,
            32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };

    public static final int[][] S_BOX = new int[][] {
            { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3,
                    8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10,
                    0, 6, 13 }, // s1
            { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11,
                    5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0,
                    5, 14, 9 }, // s2
            { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11,
                    5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0,
                    5, 14, 9 }, // s3
            { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,
                    9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12,
                    7, 2, 14 }, // s4
            { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8,
                    6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9,
                    10, 4, 5, 3, }, // s5
            { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3,
                    8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6,
                    0, 8, 13 }, // s6
            { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8,
                    6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14,
                    2, 3, 12 }, // s7
            { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9,
                    2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3,
                    5, 6, 11, } // s8
    };

    public static final int[] K_MOVE = new int[] { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
    public static final String ZEROS = "00000000";

    //加密模块
    public static String encrypt(String plainText, String secret) throws UnsupportedEncodingException {
        // 1. 构造密钥
        String[] ks = buildKs(secret);
        // 2. 明文分组
        String[] divide = divide(plainText);
        System.out.println(Arrays.toString(divide));
        // 3. 调用Feistel函数
        String cipher = feistel(divide, ks);
        return cipher;
    }

    //解密模块
    public static String decrypt(String cipher, String secret) throws UnsupportedEncodingException {
        // 1. 构造密钥, 解密时密钥倒转
        String[] ks = buildKs(secret);
        String[] decryptKs = new String[ks.length];
        for (int i = 0; i < ks.length; i++) {
            decryptKs[i] = ks[ks.length - i - 1];
        }
        // 2. 密文分组
        String[] cipherGroup = divide4Decrypt(cipher);
        // 3. 调用Feistel函数
        String plainText = feistel(cipherGroup, decryptKs);
        // 4. 将二进制明文转为原来的字符串
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i += 8) {
            String bitStr = plainText.substring(i, i + 8);
            if (!ZEROS.equals(bitStr)) {
                byte byteVal = Integer.valueOf(bitStr, 2).byteValue();
                byteList.add(byteVal);
            }
        }
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            bytes[i] = byteList.get(i);
        }

        return new String(bytes,"UTF-8").substring(0,length);
//        return new String(bytes, "UTF-8");
    }
    //f函数
    public static String feistel(String[] plainTextGroup, String[] ks) {
        StringBuilder cipher = new StringBuilder(plainTextGroup.length << 64);
        for (int i = 0; i < plainTextGroup.length; i++) {
            // 2. 初始转置
            String lr = transpose(plainTextGroup[i], BEGIN); // 某一组明文
            // 3. 16轮加密
            String l = lr.substring(0, 32); //
            String r = lr.substring(32);
            String li = l;
            String ri = r;
            for (int j = 0; j < ks.length; j++) {
                // 3.1 将lr右半部分(32位)扩展为48位
                String rr = transpose(ri, E);
                // 3.2 将ri与ki异或运算得到48位数据
                String rk = xor(rr, ks[j]);
                // 3.3 查S盒
                String rrr = searchSBox(rk);
                // 3.4 转置得到RR(32位)
                rrr = transpose(rrr, P);
                // 3.5 rrr与li异或得到新的ri, 原来的ri赋值给li
                String newRi = xor(rrr, li);
                li = ri;
                ri = newRi;
            }
            // li - r15 ri r16
            // 4. 求逆转置, 得到这一组对应的密文
            String result = transpose(ri + li, END); // 这一组加密的结果
            cipher.append(result);
        }

        return cipher.toString();
    }

    /**
     * 将明文按64位分组
     * 明文转为二进制可能不是64数的倍数, 需要在分组后补0
     *
     * @throws UnsupportedEncodingException
     */
    public static String[] divide(String text) throws UnsupportedEncodingException {
        String zeros = "00000000";
        StringBuilder sb = new StringBuilder(text.length() << 3);//每个数字有8位
        length = text.length();
        // 将每一个字符转成二进制, 拼接
        byte[] bytes = text.getBytes("UTF-8");

        for (int i = 0; i < bytes.length; i++) {
            String bitStr = Integer.toBinaryString(bytes[i]);
            int len = 8 - bitStr.length(); // 前面补0
            if (len > 0) {
                bitStr = zeros.substring(0, len) + bitStr;
            }
            sb.append(bitStr);
        }

        // 若二进制不是64位的倍数, 后面补0
        int num = sb.length() & (1 << 6) - 1;//对64取模
        while (num != 64) {
            sb.append(zeros);
            num += 8;
        }

        // 分组
        String[] result = new String[sb.length() >>> 6];
        for (int i = 0; i < sb.length(); i += 64) {
            result[i >>> 6] = sb.substring(i, i + 64);
        }
        return result;
    }

    /**
     * 密文分组, 密文一定是64位的倍数, 分组时不需要补齐数据
     */
    public static String[] divide4Decrypt(String cipher) {
        String[] cipherGroup = new String[cipher.length() >>> 6];
        for (int i = 0; i < cipher.length(); i += 64) {
            cipherGroup[i >>> 6] = cipher.substring(i, i + 64);
        }
        return cipherGroup;
    }

    /**
     * 将二进制数按照某数组进行置换
     */
    public static String transpose(String source, int[] reverseArr) {
        /*
         * (下标从0开始)取出source的第reverseArr[i] - 1位, 作为转置结果的第i位
         */
        StringBuilder sb = new StringBuilder(source.length() + 16);
        for (int i = 0; i < reverseArr.length; i++) {
            sb.append(source.charAt(reverseArr[i] - 1));
        }
        return sb.toString();
    }

    /**
     * 构造秘钥组(16个48位ki)
     *
     * @param secret
     * @return
     */
    public static String[] buildKs(String secret) {
        // 构造64位初始秘钥
        StringBuilder sb = new StringBuilder(1 << 6);//每一个字母4位 一共8字母32位
        byte[] bytes = secret.getBytes();
        if (bytes.length > 8) {
            throw new RuntimeException("秘钥必须在8个字节以内");//先变字母
        }
        for (int i = 0; i < bytes.length; i++) {//再变二进制
            String bitStr = Integer.toBinaryString(bytes[i]);
            int len = 8 - bitStr.length(); // 前面补0
            if (len > 0) {
                bitStr = ZEROS.substring(0, len) + bitStr;
            }
            sb.append(bitStr);
        }
        // 补齐64位
        while (sb.length() <= 64) {
            sb.append(ZEROS);
        }
        // 置换, 得到56位有效秘钥
        //PC-1
        String validSecret = transpose(sb.toString(), PC_1);

        // 分别对前后28位进行左移调度
        String[] ks = new String[16];
        String ci = validSecret.substring(0, 28);
        String di = validSecret.substring(28, 56);
        /**
         * ci.substring(j)取从J开始到结尾的字母 拼接上 从0开始到j的字母
         */
        //K—MOVE移位表
        for (int i = 0; i < K_MOVE.length; i++) {
            int j = K_MOVE[i];
            ci = ci.substring(j) + ci.substring(0, j);
            di = di.substring(j) + di.substring(0, j);
            // ci + di => 置换 => ki
            ks[i] = transpose(ci + di, PC_2);
        }
        return ks;
    }

    /**
     * 根据右半部分ri和秘钥ki异或后的加密数据(48位)查询S盒
     *
     * @param rk
     * @return 返回32位加密数据
     */
    public static String searchSBox(String rk) {
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < rk.length(); i += 6) {
            // 1获取6位一组的数据
            String s = rk.substring(i, i + 6);
            // 2取出首尾两位计算出行, 取出中间四位, 计算出列
            int row = Integer.valueOf("" + s.charAt(0) + s.charAt(5), 2);
            int col = Integer.valueOf(s.substring(1, 5), 2);
            // 3根据索引获取到S盒中对应的值, 转成4位二进制数据
            int index = ((row << 4) + col);
            s = Integer.toBinaryString(S_BOX[i / 6][index]);
            // 补齐四位
            int len = s.length();
            for (int j = 0; j < 4 - len; j++) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 求两个字符串的异或结果
     * 注意: 两字符串长度必须相同
     */
    public static String xor(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            throw new RuntimeException("字符串长度不相等");
        StringBuilder sb = new StringBuilder(s1.length());
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                sb.append("0");
            } else {
                sb.append("1");
            }
        }
        return sb.toString();
    }



    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入明文");
        String plainText = scanner.nextLine();
        System.out.println("请输入秘钥");
        String secret = scanner.nextLine();
        String cipher = encrypt(plainText, secret);
        System.out.println("加密结果: " + cipher);
        String decryptText = decrypt(cipher, secret);
        System.out.println("解密结果："+decryptText);

    }

}