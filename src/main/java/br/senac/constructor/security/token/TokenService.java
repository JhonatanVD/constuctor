package br.senac.constructor.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class TokenService {
    private Token properties;
    private Algorithm algorithm;
    private ObjectMapper mapper;

    public TokenService(Token properties, ObjectMapper mapper){
        this.properties = properties;
        this.mapper = mapper;

        try {
            this.algorithm = this.getAlgorithm(this.properties.getPublicKey(), this.properties.getPrivateKey());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public String generateToken(UserToken user, String role) {

        LocalDateTime now = LocalDateTime.now();
        return JWT.create()
                .withSubject("logged")
                .withIssuer(this.properties.getIssuer())
                .withIssuedAt(Date
                        .from(now.atZone(ZoneId.systemDefault())
                                .toInstant()))
                .withExpiresAt(Date.from(now.plusSeconds(properties.getMaxAgeSeconds())
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .withClaim("user", this.mapper.convertValue(user, Map.class))
                .withClaim("role", role)
                .sign(algorithm);
    }

    public Algorithm getAlgorithm(String publicKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return privateKey.isEmpty() ?
                Algorithm.RSA384(this.getPublicKey(publicKey), null) :
                Algorithm.RSA384(this.getPublicKey(publicKey), this.getPrivateKey(privateKey));
    }

    private RSAPublicKey getPublicKey(String pk) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Base64.Decoder b64 = Base64.getDecoder();
        byte[] decoded = b64.decode(pk);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    private RSAPrivateKey getPrivateKey(String pk) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Base64.Decoder b64 = Base64.getDecoder();
        byte[] decoded = b64.decode(pk);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(spec);
    }
}
