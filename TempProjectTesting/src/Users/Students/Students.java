package Users.Students;


import java.io.*;
import java.util.*;
import Login.*;
import Users.GeneralUser.Users;
import Groups.StudentsGroup;
import Groups.StudentsList;

public class Students extends Users{
	
	private HashMap<String, StudentsGroup> groups;

	public Students(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		this.groups=new HashMap<String, StudentsGroup>();
	}

	@Override
	public void GUI(LoginGUI frame) {
		StudentsGUI gui=new StudentsGUI(name, lastName, email, this);
		frame.dispose();
	}
	
	public void loadGroups() throws IOException {
		File f=new File("Files/StudyGroups.csv");
		FileReader reader=new FileReader(f);
		BufferedReader br=new BufferedReader(reader);
		HashMap<String, StudentsGroup> allLines=new HashMap<String, StudentsGroup>();
		String buff="";
		String[] splitted;
		String[] admin;
		while((buff=br.readLine())!=null) {
			splitted=buff.split(",");
			admin=splitted[1].split("-");
			allLines.put(splitted[0], new StudentsGroup(splitted[0], admin[0]));
			for(int i=0; i<splitted.length-2; i++) {
			allLines.get(splitted[0]).addStudent(splitted[2+i]);
			}
		}
		br.close();
		this.groups=allLines;		
	}
	

	public void createGroup(String groupName) throws IOException {
		LinkedList<String> update=new LinkedList<String>();
		this.groups.put(groupName, new StudentsGroup(groupName, this.email));
		File f=new File("Files/StudyGroups.csv");
		FileWriter clear=new FileWriter(f, false);
		String[] usersArray;
		for(HashMap.Entry<String, StudentsGroup> group: this.groups.entrySet()){
			usersArray=new String[group.getValue().getStudentsList().size()];
			int i=0;
			for(HashMap.Entry<String, Students> student: group.getValue().getStudentsList().entrySet()) {
				usersArray[i]=student.getValue().getEmail();
			}
			StudentsList list=new StudentsList(usersArray);
			update.add(group.getKey()+","+group.getValue().getAdmin()+"-ADMIN"+list.toString());
		}
		clear.write("");
		clear.close();
		FileWriter writer=new FileWriter(f, true);
		BufferedWriter bw=new BufferedWriter(writer);
		for(String s: update) {
			bw.write(s);
			bw.newLine();
		}
		bw.close();
	}
	
	public void addGroup(StudentsGroup group,  HashMap<String, StudentsGroup> allGroups) throws IOException {
		this.groups.put(group.getID(), group);
		this.groups.get(group.getID()).addStudent(this.email);
		
		LinkedList<String> update=new LinkedList<String>();
		File f=new File("Files/StudyGroups.csv");
		FileWriter clear=new FileWriter(f, false);
		for(HashMap.Entry<String, StudentsGroup> groupEntry: allGroups.entrySet()){
			System.out.println(groupEntry.getValue().writeToFile());
			update.add(groupEntry.getValue().writeToFile());
		}
		clear.write("");
		clear.close();
		FileWriter writer=new FileWriter(f, true);
		BufferedWriter bw=new BufferedWriter(writer);
		for(String s: update) {
			bw.write(s);
			bw.newLine();
		}
		bw.close();
		
	}
	
	public HashMap<String, StudentsGroup> getGroups(){
		return this.groups;
	}
	
	public Students getUser() {
		return this;
	}
	
}
