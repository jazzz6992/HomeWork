package task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckSumMaker {
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getCheckSum(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] dataBytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(dataBytes)) > 0) {
                md.update(dataBytes, 0, bytesRead);
            }
            byte[] mdBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte mdByte : mdBytes) {
                sb.append(Integer.toString((mdByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getAbsolutePath() + " is not found");
        } catch (IOException e) {
            System.out.println("I/O exception for reading file " + file.getAbsolutePath());
            e.getMessage();
        }
        return null;
    }
}
