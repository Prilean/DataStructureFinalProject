import java.util.*;
public class mainProgram {
	static Node slot1 = new Node();
	static Node slot2 = new Node();
	static Node slot3 = new Node();
	static Node slot4 = new Node();
	static Node slot5 = new Node();
	static LinkedList queue = new LinkedList(slot1);
	public static void main(String[] args) {
		slot1.next = slot2;
		slot2.next = slot3;
		slot3.next = slot4;
		slot4.next = slot5;
		
		Doctor d1 = new Doctor(1, "Ronald He", "Surgery", 0);
		Doctor d2 = new Doctor(2, "Socho Po", "Heart", 0);
		Doctor d3 = new Doctor(3, "Pokari Sweet", "Bone Disease", 0);
		Doctor d4 = new Doctor(4, "Jika Chi", "General Disease", 0);
		Doctor d5 = new Doctor(5, "Sicha Sa", "Surgery", 0);
		
		Room r1 = new Room(1, 0);
		Room r2 = new Room(2, 0);
		Room r3 = new Room(3, 0);
		Room r4 = new Room(4, 0);
		Room r5 = new Room(5, 0);
		
		List<Patient> patient = new ArrayList<Patient>();
		List<Doctor> doctor = new ArrayList<Doctor>();
		List<Room> room = new ArrayList<Room>();
		
		doctor.add(d1);
		doctor.add(d2);
		doctor.add(d3);
		doctor.add(d4);
		doctor.add(d5);

		room.add(r1);
		room.add(r2);
		room.add(r3);
		room.add(r4);
		room.add(r5);
		
		
		// Graph 
		int V = 10;
		List<List<MNode> > adj
        = new ArrayList<List<MNode> >();

	    for (int i = 0; i < V; i++) {
	        List<MNode> item = new ArrayList<MNode>();
	        adj.add(item);
	    }
	
	    adj.get(0).add(new MNode(1, 4));
	    adj.get(0).add(new MNode(3, 9));
	    adj.get(1).add(new MNode(0, 4));
	    adj.get(1).add(new MNode(3, 4));
	    adj.get(1).add(new MNode(2, 7));
	    adj.get(1).add(new MNode(5, 8));
	    adj.get(1).add(new MNode(4, 4));
	    adj.get(2).add(new MNode(1, 7));
	    adj.get(3).add(new MNode(0, 9));
	    adj.get(3).add(new MNode(1, 4));
	    adj.get(3).add(new MNode(5, 3));
	    adj.get(4).add(new MNode(1, 4));
	    adj.get(4).add(new MNode(5, 7));
	    adj.get(4).add(new MNode(6, 6));
	    adj.get(4).add(new MNode(7, 8));
	    adj.get(5).add(new MNode(1, 8));
	    adj.get(5).add(new MNode(3, 3));
	    adj.get(5).add(new MNode(4, 7));
	    adj.get(5).add(new MNode(9, 6));
	    adj.get(5).add(new MNode(6, 4));
	    adj.get(6).add(new MNode(5, 4));
	    adj.get(6).add(new MNode(9, 3));
	    adj.get(6).add(new MNode(4, 6));
	    adj.get(6).add(new MNode(8, 4));
	    adj.get(7).add(new MNode(4, 8));
	    adj.get(7).add(new MNode(8, 3));
	    adj.get(8).add(new MNode(6, 4));
	    adj.get(8).add(new MNode(7, 3));
	    adj.get(9).add(new MNode(5, 6));
	    adj.get(9).add(new MNode(6, 3));
	    Graph dpq = new Graph(V);
	    
	    
		while (true) {
			try {
				Scanner s = new Scanner(System.in);
				System.out.println("Hospital Management System\n");
				System.out.println("1.  Add Patient\n2.  Add Patient to Emergency\n3.  Find Nearest Hospital\n4.  Find Patient\n5.  Show All Patient\n6.  Show All Emergency\n7.  Show All Room\n8.  Find Doctor\n9.  Show All Doctor\n10. Remove Treated Patient\n0.  Exit\n");
				System.out.print("Enter choice: ");
				int c = s.nextInt();
				switch(c) {
					case 1:
						Scanner a = new Scanner(System.in);
						System.out.print("Enter Patient ID: ");
						int id = s.nextInt();
						System.out.println("Enter Patient Name: ");
						String name = a.nextLine();
						System.out.println("Enter Patient Age: ");
						int age = a.nextInt();
						a.nextLine();
						System.out.println("Enter Patient Sex: ");
						String sex = a.nextLine();
						System.out.println("Enter Patient Disease: ");
						String disease = a.nextLine();
						patient.add(new Patient(id, name, age, sex, disease));
						break;	
					case 2:
						Scanner a2 = new Scanner(System.in);
						System.out.print("Enter patient ID: ");
						int id1 = a2.nextInt();
						System.out.print("Enter Priorit Level: ");
						int level = a2.nextInt();
						Patient p = FindPatient(patient, id1);
						Node current = queue.head;
						while (current != null) {
							if (current.avail == 0) {
								current.val = level;
								current.p = p;
								for (Doctor d : doctor) {
									if (d.availability == 0) {
										current.d = d;
										d.availability = 1;
										break;
									}
								}
								for (Room r : room) {
									if (r.availability == 0) {
										current.r = r;
										r.availability = 1;
										break;
									}
								}
								current.avail = 1;
								break;
							}
							current = current.next;
							if (current == null) {
								System.out.println("Unavailable Slot!\n");
								break;
							}
						}
						queue.insertionSort();
						break;
					case 3:
						Scanner hos = new Scanner(System.in);
						String[] location = {"Doan Penh", "Toul Kork", "Tbong Khmum", "Phnom Penh", "Psar Tmey", "Russey Keo", "Pepol", "Hong Kong", "Shanghai", "American"};
						String[] hospital = {"Phnom Penh International Hospital", "Pisoth Hospital", "Vichet Hospital"};
						for (int i = 0 ; i < 10; i ++) {
							System.out.println(i +". "+ location[i]);
						}
						System.out.print("Enter your location: ");
						int source = hos.nextInt();
				        dpq.dijkstra(adj, source);
				        int[] z = {4, 9};
				        int near = 0;
				        for (int i : z) {
				        	if (dpq.dist[near] > dpq.dist[i]) {
				        		near = i;
				        	}
				        }
				        if (near == 0) {
				        	System.out.println("Nearest Hospital: " + hospital[0] + " in " + location[near] + " (" + dpq.dist[near] + " KM)" + "\n" );
				        }
				        else if (near == 4) {
				        	System.out.println("Nearest Hospital: " + hospital[1] + " in " + location[near] + " (" + dpq.dist[near] + " KM)" + "\n" );
				        }
				        else {
				        	System.out.println("Nearest Hospital: " + hospital[2] + " in " + location[near] + " (" + dpq.dist[near] + " KM)" + "\n" );
				        }
						break;
					case 4:
						Scanner patienti = new Scanner(System.in);
						System.out.print("Enter patient ID: ");
						int id0 = patienti.nextInt();
						Patient founded = FindPatient(patient, id0);
						if (founded == null) {
							System.out.println("Patient does not exist!\n");
						}
						else {
							System.out.println("ID: " + founded.id);
							System.out.println("Name: " + founded.name + "\n");
							System.out.println("Age: " + founded.age + "\n");
							System.out.println("Sex: " + founded.sex + "\n");
							System.out.println("Disease: " + founded.disease + "\n");
							
						}
						break;
					case 5:
						System.out.println("All Patient: ");
						for (Patient p99 : patient) {
							System.out.println("ID: " + p99.id);
							System.out.println("Name: " + p99.name + "\n");
							System.out.println("Age: " + p99.age + "\n");
							System.out.println("Sex: " + p99.sex + "\n");
							System.out.println("Disease: " + p99.disease + "\n");
						}
						break;
					case 6:
						Node current1 = queue.head;
						while (current1 != null) {
							if (current1.avail != 0) {
								System.out.println("Patient Name: " + current1.p.name);
								System.out.println("Priority Level: " + current1.val + "\n");
							}
							current1 = current1.next;
						}
						break;
					case 7:
						System.out.println("All Room: ");
						for (Room rr : room) {
							System.out.println("Room ID: " + rr.id);
							System.out.println("Availability: " + rr.getAvail() + "\n");
						}
						break;
					case 8:
						Scanner findD = new Scanner(System.in);
						System.out.print("Enter Doctor ID: ");
						int doctorID = findD.nextInt();
						Doctor tempD = FindDoctor(doctor, doctorID);
						if (tempD == null) {
							System.out.println("Doctor with that ID does not exist! \n");
						}
						else { 
							System.out.println("Doctor ID: " + tempD.id);
							System.out.println("Name: " + tempD.name);
							System.out.println("Availability: " + tempD.getAvail() + "\n");
						}
						break;
					case 9:
						System.out.println("All Doctor: ");
						for (Doctor d : doctor) {
							System.out.println("ID: " + d.id);
							System.out.println("Name: " + d.name);
							System.out.println("Availability: " + d.getAvail());
						}
						break;
					case 10:
						Scanner patientii = new Scanner(System.in);
						System.out.print("Enter patient ID: ");
						int id11 = patientii.nextInt();
						removePatient(id11);
						System.out.println("Successfully removed!\n");
						break;
					case 0:
						System.out.println("Thank you for using our application!");
						System.exit(0);
					default:
                        System.out.println("Invalid choice. Please enter a valid option.\n");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid number.\n");
				// Clear the input buffer
			}	
		}
	}
	static Patient FindPatient(List<Patient> a, int id) {
		for (Patient j : a) {
			if (j.id == id) {
				return j;
			}
		}
		return null;
	}
	static Doctor FindDoctor(List<Doctor> a, int id) {
		for (Doctor j : a) {
			if (j.id == id) {
				return j;
			}
		}
		return null;
	}
	static void removePatient(int id) {
		Node current = queue.head;
		while (current != null) {
			if (current.p != null) {
				if (current.p.id == id) {
					current.avail = 0;
					current.r.availability = 0;
					current.d.availability = 0;
					current.val = 99;
					break;
				}
			}
			current = current.next;
		}
	}
}
