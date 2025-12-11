import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CryptoTask {

    private static final String TARGET =
            "764aa26b55a4da654df6b19e4bce00f4ed05e09346fb0e762583cb7da2ac93a2";

    private static String toHex(byte[] data, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) sb.append(String.format("%02x", data[i]));
        return sb.toString();
    }

    private static byte[] hexToBytes(String hex) {
        byte[] out = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            int hi = Character.digit(hex.charAt(i), 16);
            int lo = Character.digit(hex.charAt(i + 1), 16);
            out[i / 2] = (byte) ((hi << 4) | lo);
        }
        return out;
    }

    private static byte[] makeKey(String word) {
        byte[] result = new byte[16];
        byte[] w = word.getBytes();
        int i = 0;
        while (i < w.length && i < 16) {
            result[i] = w[i];
            i++;
        }
        while (i < 16) {
            result[i] = '#';
            i++;
        }
        return result;
    }

    private static String encryptWord(String word) throws Exception {
        byte[] keyBytes = makeKey(word);
        byte[] ivBytes = hexToBytes("aabbccddeeff00998877665544332211");

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        String text = "This is a top secret.";
        byte[] input = text.getBytes();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] out = cipher.doFinal(input);
        return toHex(out, out.length);
    }

    public static void main(String[] args) {
        File f = new File("words.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null && !found) {
                line = line.trim();
                if (line.length() == 0 || line.length() > 16) continue;

                System.out.println("Trying: " + line);

                try {
                    String enc = encryptWord(line);
                    if (enc.equals(TARGET)) {
                        System.out.println("Key found: " + line);
                        found = true;
                    } else {
                        System.out.println("No match");
                    }
                } catch (Exception e) {
                    System.out.println("Error on key: " + line);
                }
            }

            if (!found) System.out.println("Key not in dictionary.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

