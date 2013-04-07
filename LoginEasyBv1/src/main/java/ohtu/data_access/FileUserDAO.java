package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

@Component
public class FileUserDAO implements UserDao {

	private File usersFile;
	private final String PATH_TO_FILE = "salasanat.txt";

	public FileUserDAO() {
		this.usersFile = new File(PATH_TO_FILE);
	}

	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<User>();
		try {
			Scanner scanner = new Scanner(this.usersFile);
			while (scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] userInfo = row.split(";");
				User u = new User(userInfo[0], userInfo[1]);
				users.add(u);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return users;
		
	}

	@Override
	public User findByName(String name) {
		User u = null;
		try {
			Scanner scanner = new Scanner(this.usersFile);
			String row;
			while (scanner.hasNextLine()) {
				row = scanner.nextLine();
				if (row.split(";")[0].equals(name)) {
					u = new User(row.split(";")[0], row.split(";")[1]);
				}
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return u;
	}

	@Override
	public void add(User user) {
		try {
			FileWriter writer = new FileWriter(usersFile, true);
			writer.append(user.getUsername() + ";" + user.getPassword()+"\n");
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
