package br.senac.constructor.security.token;

import br.senac.constructor.exception.JWTAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
@AllArgsConstructor
public class TokenValidation {
    private final ObjectMapper objectMapper;
    private final Token tokenProperties;

    public TokenStructure buscarTokenData(String bearerToken){
        try{
            Base64.Decoder b64 = Base64.getDecoder();
            byte[] decoded = b64.decode(this.tokenProperties.getPublicKey());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            final Algorithm algorithm = Algorithm.RSA384((RSAPublicKey) kf.generatePublic(spec), null);
            final JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.tokenProperties.getIssuer())
                    .build();
            final DecodedJWT user = verifier.verify(bearerToken.split(" ")[1]);
            return this.objectMapper
                    .readValue(new String(Base64.getDecoder().decode(user.getPayload())), TokenStructure.class);
        } catch(JWTVerificationException | JsonProcessingException | NoSuchAlgorithmException |
                InvalidKeySpecException e){
            throw  new JWTAuthenticationException("Erro ao validar o token, verifique!");
        } catch(ArrayIndexOutOfBoundsException e){
            throw new JWTAuthenticationException("Autorização fora de padrão. ERRO: %s".formatted(e.getMessage()));
        }
    }
}