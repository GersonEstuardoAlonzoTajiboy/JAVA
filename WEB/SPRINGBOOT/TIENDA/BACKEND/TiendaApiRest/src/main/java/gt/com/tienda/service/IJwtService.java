package gt.com.tienda.service;

import gt.com.tienda.dto.UsuarioDTO;
import io.jsonwebtoken.Claims;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface IJwtService {
    public String extractUsuarioCorreoElectronico(String jwtToken);

    <T> T extractClaim(String jwtToken, @NonNull Function<Claims, T> claimsTFunction);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims, @NonNull UserDetails userDetails);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    UsuarioDTO getUsuario(String jwtToken);
}
