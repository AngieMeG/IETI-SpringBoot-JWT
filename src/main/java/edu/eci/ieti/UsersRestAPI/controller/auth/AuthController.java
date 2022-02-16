package edu.eci.ieti.UsersRestAPI.controller.auth;

import edu.eci.ieti.UsersRestAPI.data.User;
import edu.eci.ieti.UsersRestAPI.dto.LoginDto;
import edu.eci.ieti.UsersRestAPI.dto.TokenDto;
import edu.eci.ieti.UsersRestAPI.exception.InvalidCredentialsException;
import edu.eci.ieti.UsersRestAPI.exception.UserException;
import edu.eci.ieti.UsersRestAPI.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static edu.eci.ieti.UsersRestAPI.data.utils.Constants.CLAIMS_ROLES_KEY;
import static edu.eci.ieti.UsersRestAPI.data.utils.Constants.TOKEN_DURATION_MINUTES;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping( "v1/auth" )
public class AuthController {
    @Value( "${app.secret}" )
    String secret;

    private final UserService userService;

    public AuthController( @Autowired @Qualifier("MongoImpl") UserService userService )
    {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto )
    {
        User user = null;
        try{
            user = userService.findByEmail(loginDto.getEmail());
        } catch (UserException e){
            throw new InvalidCredentialsException();
        }
        if ( BCrypt.checkpw(loginDto.getPassword(), user.getPasswordHash() ) )
        {
            return generateTokenDto( user );
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken( User user, Date expirationDate )
    {
        return Jwts.builder()
                .setSubject( user.getId() )
                .claim( CLAIMS_ROLES_KEY, user.getRoles() )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }

    private TokenDto generateTokenDto( User user )
    {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }
}
