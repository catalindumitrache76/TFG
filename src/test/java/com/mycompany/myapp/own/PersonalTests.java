package com.mycompany.myapp.own;

import com.mycompany.myapp.TfgJHipsterApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TfgJHipsterApp.class)


/** Cargamos las opciones de configuración de hive o de esta manera: **/
//@ActiveProfiles("no-liquibase")
//@ContextConfiguration("/config/own_config/hive_context.xml")

/** o de esta manera: **/
@ActiveProfiles("hive")

/** La manera limpia es la segunda puesto que en la primera necesitamos crear el fichero
 * resources/config/own_config/hive_context.xml para sobreescribir las propiedades del perfil de hive que
 * ya hemos creado en el fichero application-hive.yml
 */

@Transactional
public class PersonalTests {

    @Test
    public void personalTest() {
        ApplicationContext ctx = new GenericApplicationContext();
        Environment env = ctx.getEnvironment();
        boolean containsFoo = env.containsProperty("foo");
        System.out.println("Does my environment contain the 'foo' property? " + containsFoo);
    }
}

//      long usuarios = userRepository.count();

//    @Autowired
//    private PersistentTokenRepository persistentTokenRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;





//    private void generateUserToken(User user, String tokenSeries, LocalDate localDate) {
//        PersistentToken token = new PersistentToken();
//        token.setSeries(tokenSeries);
//        token.setUser(user);
//        token.setTokenValue(tokenSeries + "-data");
//        token.setTokenDate(localDate);
//        token.setIpAddress("127.0.0.1");
//        token.setUserAgent("Test agent");
//        persistentTokenRepository.saveAndFlush(token);
//    }


//}
