package catalin_tfg;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
