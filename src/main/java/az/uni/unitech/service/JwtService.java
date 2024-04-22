package az.uni.unitech.service;

import az.uni.unitech.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private static final String USERNAME_KEY = "USERNAME";

    public String generate(User user) {
        return JWT
                .create()
                .withClaim(USERNAME_KEY, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(algorithmKey));
    }

    public String getUsername(String token) {
        DecodedJWT jwt = JWT
                .require(Algorithm.HMAC256(algorithmKey))
                .withIssuer(issuer)
                .build()
                .verify(token);
        return jwt.getClaim(USERNAME_KEY).asString();
    }

}