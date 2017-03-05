package catalin_tfg;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class TfgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TfgApplication.class, args);
		try {
			MainApp.hadoop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOEXCEPTION");
		}
	}
}
