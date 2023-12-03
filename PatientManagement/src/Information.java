class Patient {
	int id;
	String name;
	int age;
	String sex;
	String disease;
	Patient(int id, String name, int age, String sex, String disease){
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.disease = disease;
		
	}
}
class Doctor {
	int id;
	String name;
	String type;
	int availability;
	Doctor(int id, String name, String type, int avail){
		this.id = id;
		this.name = name;
		this.type = type;
		this.availability = avail;
	}
	String getAvail() {
		return availability == 0 ? "Available" : "Unavailable";
	}
}
class Room {
	int id;
	int availability;
	Room(int id, int avail){
		this.id = id;
		this.availability = avail;
	}
	String getAvail() {
		return availability == 0 ? "Available" : "Unavailable";
	}
}