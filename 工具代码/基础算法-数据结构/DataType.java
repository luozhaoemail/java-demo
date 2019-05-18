package view;

public class DataType {

	public static void main(String[] args) {
		byte a = 10;
		System.out.println(a+" = "+byteToBit(a));
		System.out.println("00001010="+decodeBinaryString("00001010"));
		
		byte[] bb = getBooleanArray(a);
		for(int i=0; i<bb.length;i++)
			System.out.print(bb[i]+" ");
		System.out.println();

		int b = 10;
		System.out.println(b+" = "+Integer.toBinaryString(b));
		 
		char ch = '\u0001';//Unicode字符
		String str = "\u0001";
		System.out.println("ch="+ch+"   str="+str);

		int i =128;   
		byte j = (byte)i;//因为 byte 类型是 8 位，最大值为127，所以当 int 强制转换为 byte 类型时，值 128 时候就会导致溢出。
		System.out.println(j);
		
		System.out.println(Math.floor(-8.5));//-9.0
		System.out.println(Math.ceil(-8.5));//-8.0
		System.out.println(Math.round(-8.5));//-8
		
		Integer xx = 10; // 自动装箱
		int x = xx; // 自动拆箱
		Integer vv = new Integer(10);  // 手动装箱
		int v = vv.intValue();// 手动拆箱
		
		System.out.println((x==xx)+" "+xx.equals(x));//true true
		
		int p1=59;
		Integer p2=59;
		Integer p3=Integer.valueOf(59);
		Integer p4=new Integer(59);
		System.out.println(p1==p3);//true
		System.out.println(p2==p3);//true
		System.out.println(p3==p4);//false  java会缓存-128-127
		System.out.println(p1==p4);//true
	}

	/** 
     * 把byte转为字符串的bit，即字节转二进制位
     */  
    public static String byteToBit(byte b) {  
        return ""  
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)  
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)  
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)  
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);  
    } 
    
    /** 
     * 二进制字符串转byte 
     */  
    public static byte decodeBinaryString(String byteStr) {  
        int re, len;  
        if (null == byteStr) {  
            return 0;  
        }  
        len = byteStr.length();  
        if (len != 4 && len != 8) {  
            return 0;  
        }  
        if (len == 8) {// 8 bit处理  
            if (byteStr.charAt(0) == '0') {// 正数  
                re = Integer.parseInt(byteStr, 2);  
            } else {// 负数  
                re = Integer.parseInt(byteStr, 2) - 256;  
            }  
        } else {// 4 bit处理  
            re = Integer.parseInt(byteStr, 2);  
        }  
        return (byte) re;  
    } 
    
    /** 
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit 
     */  
    public static byte[] getBooleanArray(byte b) {  
        byte[] array = new byte[8];  
        for (int i = 7; i >= 0; i--) {  
            array[i] = (byte)(b & 1);  
            b = (byte) (b >> 1);  
        }  
        return array;  
    } 
}
