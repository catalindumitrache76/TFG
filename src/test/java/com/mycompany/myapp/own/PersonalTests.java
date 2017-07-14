package com.mycompany.myapp.own;

import com.mycompany.myapp.TfgJHipsterApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TfgJHipsterApp.class)
@ContextConfiguration()

@ActiveProfiles("hive")
//@Transactional
public class PersonalTests {

//    @Autowired
//    private PersistentTokenRepository persistentTokenRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;


    @Test
    public void testPersonal() {
//      long usuarios = userRepository.count();
        System.out.println("Encontrados " + 5 + " usuarios en la B.D.");
    }


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


}
