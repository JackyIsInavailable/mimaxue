package SM4;


import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;

/**
 * SM4
 */
public class SM4 {
    public static int[][] SBOX =
            {
                    { 0xd6 , 0x90 , 0xe9 , 0xfe , 0xcc , 0xe1 , 0x3d , 0xb7 , 0x16 , 0xb6 , 0x14 , 0xc2 , 0x28 , 0xfb , 0x2c , 0x05},
                    { 0x2b , 0x67 , 0x9a , 0x76 , 0x2a , 0xbe , 0x04 , 0xc3 , 0xaa , 0x44 , 0x13 , 0x26 , 0x49 , 0x86 , 0x06 , 0x99},
                    { 0x9c , 0x42 , 0x50 , 0xf4 , 0x91 , 0xef , 0x98 , 0x7a , 0x33 , 0x54 , 0x0b , 0x43 , 0xed , 0xcf , 0xac , 0x62},
                    { 0xe4 , 0xb3 , 0x1c , 0xa9 , 0xc9 , 0x08 , 0xe8 , 0x95 , 0x80 , 0xdf , 0x94 , 0xfa , 0x75 , 0x8f , 0x3f , 0xa6},
                    { 0x47 , 0x07 , 0xa7 , 0xfc , 0xf3 , 0x73 , 0x17 , 0xba , 0x83 , 0x59 , 0x3c , 0x19 , 0xe6 , 0x85 , 0x4f , 0xa8},
                    { 0x68 , 0x6b , 0x81 , 0xb2 , 0x71 , 0x64 , 0xda , 0x8b , 0xf8 , 0xeb , 0x0f , 0x4b , 0x70 , 0x56 , 0x9d , 0x35},
                    { 0x1e , 0x24 , 0x0e , 0x5e , 0x63 , 0x58 , 0xd1 , 0xa2 , 0x25 , 0x22 , 0x7c , 0x3b , 0x01 , 0x21 , 0x78 , 0x87},
                    { 0xd4 , 0x00 , 0x46 , 0x57 , 0x9f , 0xd3 , 0x27 , 0x52 , 0x4c , 0x36 , 0x02 , 0xe7 , 0xa0 , 0xc4 , 0xc8 , 0x9e},
                    { 0xea , 0xbf , 0x8a , 0xd2 , 0x40 , 0xc7 , 0x38 , 0xb5 , 0xa3 , 0xf7 , 0xf2 , 0xce , 0xf9 , 0x61 , 0x15 , 0xa1},
                    { 0xe0 , 0xae , 0x5d , 0xa4 , 0x9b , 0x34 , 0x1a , 0x55 , 0xad , 0x93 , 0x32 , 0x30 , 0xf5 , 0x8c , 0xb1 , 0xe3},
                    { 0x1d , 0xf6 , 0xe2 , 0x2e , 0x82 , 0x66 , 0xca , 0x60 , 0xc0 , 0x29 , 0x23 , 0xab , 0x0d , 0x53 , 0x4e , 0x6f},
                    { 0xd5 , 0xdb , 0x37 , 0x45 , 0xde , 0xfd , 0x8e , 0x2f , 0x03 , 0xff , 0x6a , 0x72 , 0x6d , 0x6c , 0x5b , 0x51},
                    { 0x8d , 0x1b , 0xaf , 0x92 , 0xbb , 0xdd , 0xbc , 0x7f , 0x11 , 0xd9 , 0x5c , 0x41 , 0x1f , 0x10 , 0x5a , 0xd8},
                    { 0x0a , 0xc1 , 0x31 , 0x88 , 0xa5 , 0xcd , 0x7b , 0xbd , 0x2d , 0x74 , 0xd0 , 0x12 , 0xb8 , 0xe5 , 0xb4 , 0xb0},
                    { 0x89 , 0x69 , 0x97 , 0x4a , 0x0c , 0x96 , 0x77 , 0x7e , 0x65 , 0xb9 , 0xf1 , 0x09 , 0xc5 , 0x6e , 0xc6 , 0x84},
                    { 0x18 , 0xf0 , 0x7d , 0xec , 0x3a , 0xdc , 0x4d , 0x20 , 0x79 , 0xee , 0x5f , 0x3e , 0xd7 , 0xcb , 0x39 , 0x48},
            };

    public static int[] rk = new int[32];
    public static int[] FK = {0XA3B1BAC6,0X56AA3350,0X677D9197,0XB27022DC};
    public static int[] CK = {
            0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,
            0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9,
            0xe0e7eef5, 0xfc030a11, 0x181f262d, 0x343b4249,
            0x50575e65, 0x6c737a81, 0x888f969d, 0xa4abb2b9,
            0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d, 0x141b2229,
            0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,
            0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209,
            0x10171e25, 0x2c333a41, 0x484f565d, 0x646b7279};

    public static void keyextension(int[] MK){
        int K0,K1,K2,K3;
        K0 = MK[0] ^ FK[0];
        K1 = MK[1] ^ FK[1];
        K2 = MK[2] ^ FK[2];
        K3 = MK[3] ^ FK[3];
        int[] K = new int[36];
        K[0] = K0;K[1] = K1;K[2] = K2;K[3] = K3;
        for(int i=0;i<32;i++){
            K[i+4]  = K[i] ^ T1(K[i+1] ^ K[i+2] ^ K[i+3] ^ CK[i]);
            rk[i] = K[i+4];
        }

    }

    public static int sbx(int n){
        int i = (n >> 4) & 0x0000000f;
        int j = n & 0x0f;
        return SBOX[i][j];
    }

    public static int[] t(int[] A){
        int[] B = {sbx(A[0]),sbx(A[1]),sbx(A[2]),sbx(A[3])};
        return B;
    }

    public static int L(int B){
        return B ^ (lshit(B, 2)) ^ (lshit(B, 10)) ^ (lshit(B, 18)) ^ (lshit(B, 24));
    }

    public static int L1(int B){
        return B ^ (lshit(B, 13)) ^ (lshit(B, 23));
    }

    public static int T(int n){
        int a0,a1,a2,a3;
        a0 = (n>>24) & 0x000000ff;
        a1 = (n>>16) & 0x000000ff;
        a2 = (n>>8)  & 0x000000ff;
        a3 = n       & 0x000000ff;
        int[] A = {a0,a1,a2,a3};
        int[] temp = t(A);
        int B = (temp[0]<<24) | (temp[1]<<16) | (temp[2]<<8) | (temp[3]);
        return L(B);
    }

    public static String h(int n){
        return Integer.toHexString(n);
    }

    public static int T1(int n){
        int a0,a1,a2,a3;
        a0 = (n>>24) & 0x000000ff;
        a1 = (n>>16) & 0x000000ff;
        a2 = (n>>8)  & 0x000000ff;
        a3 = n       & 0x000000ff;
        int[] A = {a0,a1,a2,a3};
        int[] temp = t(A);
        int B = (temp[0]<<24) | (temp[1]<<16) | (temp[2]<<8) | (temp[3]);
        return L1(B);
    }

    public static int lshit(int x,int s){
        s = s % 32;
        if (s == 0) {
            return x;
        }
        return (((x << s) & 0xFFFFFFFF) | ((x >> (32 - s)) & (0x7FFFFFFF >> (31 - s))));
    }

    public static int rshit(int x,int s){
        s = s % 32;
        if(s==0){
            return x;
        }
        return (x<< (32-s) & 0xFFFFFFFF) | ((x>>s) & (0x7fffffff >> (s-1)));
    }

    public static int[] R(int[] A){
        int len = A.length;
        int[] r = new int[len];
        for(int i=len-1,j=0;i>=0; i--,j++){
            r[j] = A[i];
        }
        return r;
    }

    public static int F(int X0,int X1,int X2,int X3,int rk){
        return X0 ^ T((X1) ^ (X2) ^ (X3) ^ rk);
    }

    public static int[] encode(int[] X,int[] key){
        int[] x = {X[0],X[1],X[2],X[3]};
        for(int i=0;i<32;i++){
            x[(i+4)%4] = F(x[i%4],x[(i+1)%4],x[(i+2)%4],x[(i+3)%4],rk[i]);
        }
        int[] Y = {x[3],x[2],x[1],x[0]};
        return Y;
    }

    public static int[] decode(int[] X,int[] key){
        int[] x = {X[0],X[1],X[2],X[3]};
        for(int i=0;i<32;i++){
            x[(i+4)%4] = F(x[i%4],x[(i+1)%4],x[(i+2)%4],x[(i+3)%4],rk[i]);
        }
        int[] Y = {x[3],x[2],x[1],x[0]};
        return Y;
    }

    public static int d(byte b){
        return 0x000000ff & (int)b;
    }

    public static int[][] padding(byte[] m){
        int len = m.length;
        int arraylen = len/16 + 2;
        byte[] padarray = new byte[16];
        int notpadlen = len % 16;
        int[][] r = new int[arraylen][4];
        for(int i=0;i<notpadlen; i++){
            padarray[i] = m[(len/16)*16+i];
        }
        for(int i=notpadlen;i<16; i++){
            padarray[i] = (byte)0x00;
        }
        for(int i=0; i<arraylen-2; i++){
            for(int j=0; j<4; j++){
                r[i][j] = d(m[i*16+4*j])<<24 ^ d(m[i*16+4*j+1])<<16 ^ d(m[i*16+4*j+2])<<8 ^ d(m[i*16+4*j+3]);
            }
        }

        for(int i=0; i<4; i++){
            r[arraylen-2][i] = d(padarray[i*4])<<24 ^ d(padarray[i*4+1])<<16 ^ d(padarray[i*4+2])<<8 ^ d(padarray[i*4+3]);
        }

        r[arraylen-1][0] = 0x00000000;
        r[arraylen-1][1] = 0x00000000;
        r[arraylen-1][2] = 0x00000000;
        r[arraylen-1][3] = 0x000000ff & notpadlen;
        return r;
    }

    public static int[][] enc(byte[] M,int[] key){
        keyextension(key);
        int[][] paddingarray = padding(M);
        for(int i=0; i<paddingarray.length; i++){
            paddingarray[i] = encode(paddingarray[i], key);
        }
        return paddingarray;
    }

    public static int[][] dec(byte[] M,int[] key){
        keyextension(key);
        rk = R(rk);
        int[][] cryptarray = new int[M.length/16][4];
        for(int i=0; i<cryptarray.length; i++){
            for(int j=0; j<4; j++){
                cryptarray[i][j] = d(M[i*16+4*j])<<24 ^ d(M[i*16+4*j+1])<<16 ^ d(M[i*16+4*j+2])<<8 ^ d(M[i*16+4*j+3]);
            }
        }
        for(int i=0; i<cryptarray.length; i++){
            cryptarray[i] = decode(cryptarray[i], key);
        }
        return cryptarray;
    }

    public static byte[] sm4enc(byte[] M,int[] key){
        byte[] cryptbyte = null;
        try {
            int[][] cryptarray = enc(M, key);
            cryptbyte = new byte[cryptarray.length*16];
            for(int i=0; i<cryptarray.length; i++){
                for(int j=0; j<4; j++){
                    byte[] temp = ByteBuffer.allocate(4).putInt(cryptarray[i][j]).array();
                    cryptbyte[16*i+4*j] = temp[0];
                    cryptbyte[16*i+4*j+1] = temp[1];
                    cryptbyte[16*i+4*j+2] = temp[2];
                    cryptbyte[16*i+4*j+3] = temp[3];
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return cryptbyte;
    }

    public static byte[] sm4dec(byte[] M,int[] key){
        byte[] cryptbyte = null;
        try {
            int[][] cryptarray = dec(M, key);
            int notpadlen = cryptarray[cryptarray.length-1][3];
            cryptbyte = new byte[(cryptarray.length-2)*16+notpadlen];
            for(int i=0; i<cryptarray.length-2; i++){
                for(int j=0; j<4; j++){
                    byte[] temp = ByteBuffer.allocate(4).putInt(cryptarray[i][j]).array();
                    cryptbyte[16*i+4*j] = temp[0];
                    cryptbyte[16*i+4*j+1] = temp[1];
                    cryptbyte[16*i+4*j+2] = temp[2];
                    cryptbyte[16*i+4*j+3] = temp[3];
                }
            }
            byte[] temp = new byte[16];
            for(int i=0; i<4; i++){
                byte[] t = ByteBuffer.allocate(4).putInt(cryptarray[cryptarray.length-2][i]).array();
                temp[i*4]= t[0];
                temp[i*4+1]= t[1];
                temp[i*4+2]= t[2];
                temp[i*4+3]= t[3];
            }
            for(int i=0; i<notpadlen; i++){
                cryptbyte[(cryptarray.length-2)*16+i] = temp[i];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cryptbyte;
    }


    public static void main(String[] args) {

        System.out.println("明文 = 0x0123456789abcdeffedcba9876543210"+" (128bit)");
        System.out.println("密钥 = 0x0123456789abcdeffedcba9876543210"+" (128bit)");
        String plaintext = "0123456789abcdeffedcba9876543210";
        int[] key = {0x01234567, 0x89abcdef, 0xfedcba98, 0x76543210};//mk0,1,2,3
//        System.out.println("Bytes:"+Arrays.toString(plaintext.getBytes()));
        byte[] ciphertext = sm4enc(DatatypeConverter.parseHexBinary(plaintext), key);

        String ciphertextHex = "";
        for (byte b : ciphertext) {
            ciphertextHex += String.format("%02x", b);
        }
        System.out.println("密文 = 0x" + ciphertextHex.substring(0,32)+" (128bit)");

//        byte[] decryptedtext = sm4dec(ciphertext, key);
//        System.out.println("解密后的明文:" + new String(decryptedtext));
    }

}
