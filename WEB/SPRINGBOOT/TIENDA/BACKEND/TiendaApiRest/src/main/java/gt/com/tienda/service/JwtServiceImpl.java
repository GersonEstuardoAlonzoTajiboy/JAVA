package gt.com.tienda.service;

import gt.com.tienda.dto.UsuarioDTO;
import gt.com.tienda.entity.UsuarioEntity;
import gt.com.tienda.mapper.UsuarioMapper;
import gt.com.tienda.repository.IUsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements IJwtService {
    private static final String SECRET_KEY = "67fd457ad16841dacce2ba9f3abcc7c1c6edbbfc0fad9b52dd456fb9f521915e";
    private final UsuarioMapper usuarioMapper;
    private final IUsuarioRepository IUsuarioRepository;

    @Override
    public String extractUsuarioCorreoElectronico(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String jwtToken, @NonNull Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsTFunction.apply(claims);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, @NonNull UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String usuarioCorreoElectronico = extractUsuarioCorreoElectronico(jwtToken);
        return (usuarioCorreoElectronico.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    @Override
    public UsuarioDTO getUsuario(String jwtToken) {
        Claims claims = extractAllClaims(jwtToken);
        String usuarioCorreoElectronico = claims.getSubject();
        UsuarioEntity usuarioEntity = IUsuarioRepository.findByCorreoElectronicoAndEstadoIsTrue(usuarioCorreoElectronico).orElseThrow();
        return usuarioMapper.convertToDto(usuarioEntity);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private @NonNull Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }
}
