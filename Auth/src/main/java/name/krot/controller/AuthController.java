package name.krot.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import name.krot.dto.TokenRequest;
import name.krot.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    // http://localhost:8088/swagger-ui/index.html#/PlayController/infoSpaceship


    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private BattleService battleService;

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> getToken(@RequestHeader("user_id") String userId,
                                                        @RequestBody TokenRequest tokenRequest) {
        if (battleService.isUserInBattle(userId, tokenRequest.getBattleId())) {
            Key secretKey = getSigningKey();

            String token = Jwts.builder()
                    .setSubject(userId)
                    .claim("battle_id", tokenRequest.getBattleId())
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("error", "User not in battle participants"));
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}