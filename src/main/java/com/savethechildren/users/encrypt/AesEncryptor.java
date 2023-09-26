package com.savethechildren.users.encrypt;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Configuration
@Log4j2
public class AesEncryptor implements AttributeConverter<Object, String> {

    //AES key required for encryption and decryption. 32Bytes
    private final String encryptionKey = "DfasfADServcvhJ%";

    //Encryption Cypher using
    private final String encryptionCipher = "AES";


    //encryption key String
    private Key key;
    //Encryption Cipher String
    private Cipher cipher;

    public Key getKey() {
        if (key == null) {
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        }
        return key;
    }

    public Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (cipher == null) {
            cipher = Cipher.getInstance(encryptionCipher);
        }
        return cipher;
    }

    /**
     * @param encryptMode defines the Encryption mode.
     */
    private void initCipher(int encryptMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        getCipher().init(encryptMode, getKey());
    }


    /**
     * Convert to Database column. Convert plain text object into encrypted text
     *
     * @param attribute
     * @return plain text
     */
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        if (attribute == null) {
            return null;
        }
        initCipher(Cipher.ENCRYPT_MODE);
        byte[] bytes = SerializationUtils.serialize(attribute);

        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
    }


    /**
     * Convert to Entity Attribute. Convert encrypted text into plain text
     *
     * @param dbData
     * @return
     */
    @SneakyThrows //THROW CHECKED EXCEPTION
    @Override

    public Object convertToEntityAttribute(String dbData) {
        Object object = null;
        if (dbData == null) {
            return null;
        }
        initCipher(Cipher.DECRYPT_MODE);

        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));

        try {
            object = this.deserialize(bytes);
        } catch (IOException e) {
            log.error(e);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }

        return object;

    }

    private Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }
}
