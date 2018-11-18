package com.ygl.canteen.util;

import java.io.*;
import java.util.Arrays;

public class FileUtils {

    public static void writeByteArrayToFile(File file , byte[] bs){

        if(bs != null){

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(bs,0,bs.length);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static String getSuffix(byte[] source){

        byte[] byteSuffix= Arrays.copyOf(source,3);

        String hexSuffix=bytesToHexString(byteSuffix);

        switch (hexSuffix){
            case "89504e":
                return ".png";
            case "ffd8ff":
                return ".jpg";
            default:
                return ".jpg";
        }

    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toLowerCase();
    }

    public static byte[] File2byte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }
}
