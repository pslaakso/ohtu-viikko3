package ohtu;

import ohtu.data_access.InMemoryUserDao;
import ohtu.data_access.UserDao;
import ohtu.io.ConsoleIO;
import ohtu.io.IO;
import ohtu.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {

	@Autowired
    private IO io;
	@Autowired
    private AuthenticationService auth;

	public App() {
//		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
//		this.io = (IO)ctx.getBean(IO.class);
//		this.auth = (AuthenticationService)ctx.getBean(AuthenticationService.class);
	}

	public App(IO io, AuthenticationService auth) {
        this.io = io;
        this.auth = auth;
    }

    public String[] ask() {
        String[] userPwd = new String[2];
        userPwd[0] = io.readLine("username:");
        userPwd[1] = io.readLine("password:");
        return userPwd;
    }

    public void run() {
        while (true) {
            String command = io.readLine(">");

            if (command.isEmpty()) {
                break;
            }

            if (command.equals("new")) {
                String[] usernameAndPasword = ask();
                if (auth.createUser(usernameAndPasword[0], usernameAndPasword[1])) {
                    io.print("new user registered");
                } else {
                    io.print("new user not registered");
                }

            } else if (command.equals("login")) {
                String[] usernameAndPasword = ask();
                if (auth.logIn(usernameAndPasword[0], usernameAndPasword[1])) {
                    io.print("logged in");
                } else {
                    io.print("wrong username or password");
                }
            }

        }
    }

    public static void main(String[] args) {
//        UserDao dao = new InMemoryUserDao();
//        IO io = new ConsoleIO();
//        AuthenticationService auth = new AuthenticationService(dao);
//        new App(io, auth).run();

		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
		App application = ctx.getBean(App.class);
		application.run();
    }
    
    // testejä debugatessa saattaa olla hyödyllistä testata ohjelman ajamista
    // samoin kuin testi tekee, eli injektoimalla käyttäjän syötteen StubIO:n avulla
    //
    // UserDao dao = new InMemoryUserDao();  
    // StubIO io = new StubIO("new", "eero", "sala1nen" );   
    //  AuthenticationService auth = new AuthenticationService(dao);
    // new App(io, auth).run();
    // System.out.println(io.getPrints());
}
