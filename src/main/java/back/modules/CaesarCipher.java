package back.modules;

public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                encryptedText.append((char) (base + (currentChar - base + shift) % 26));
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, int shift) {
        if (encryptedText == null || encryptedText.isEmpty()) {
            return "";
        }

        shift = (26 - shift) % 26; 

        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                decryptedText.append((char) (base + (currentChar - base + shift) % 26));
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String plaintext = "abcd";
        int shift = 3;
        String encryptedText = encrypt(plaintext, shift);
        String decryptedText = decrypt(encryptedText, shift);

        System.out.println("Original text: " + plaintext);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}

