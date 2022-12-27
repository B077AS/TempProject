package Groups;

public class StudentsList {
	private String[] list;
	
	public StudentsList(String[] list) {
		this.list=list;
	}
	
	public String printableList() {
		StringBuffer buff=new  StringBuffer();
		for(int i=0; i<this.list.length; i++) {
			buff.append(","+this.list[0]);
		}
		return buff.toString();
	}
	
	@Override
	public String toString() {
		return printableList();
	}
}
