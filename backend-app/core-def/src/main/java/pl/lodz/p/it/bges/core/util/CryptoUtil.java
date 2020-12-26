package pl.lodz.p.it.bges.core.util;

import org.jasypt.util.numeric.AES256IntegerNumberEncryptor;

public class CryptoUtil {

    private static AES256IntegerNumberEncryptor encryptor;

    private CryptoUtil() {
    }

    public static AES256IntegerNumberEncryptor getInstance() {
        if (encryptor == null) {
            encryptor = new AES256IntegerNumberEncryptor();
            encryptor.setPassword(System.getenv("CRYPTO_SEED"));
        }
        return encryptor;
    }
}
