package A2;
import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * The way you implement this method will define which order the assignments appear in when you sort.
	 * Return 1 if a1 should appear after a2
	 * Return -1 if a1 should appear before a2
	 * Return 0 if a1 and a2 are equivalent 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		//YOUR CODE GOES HERE, DONT FORGET TO EDIT THE RETURN STATEMENT
                if(a2.weight > a1.weight) {
                    return 1;
                }
                if(a2.weight < a1.weight) {
                    return -1;
                }
                else return 0;				
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to when assignment #i will be completed. output[i] is 0 if assignment #i is never completed.
	 * The homework you complete first will be given an output of 1, the second, 2, etc.
	 */
	public int[] SelectAssignments() {
		//Use the following command to sort your Assignments: 
		//Collections.sort(Assignments, new Assignment());
		//This will re-order your assignments. The resulting order will depend on how the compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		//Initializes the homeworkPlan, which you must fill out and output
		int[] homeworkPlan = new int[Assignments.size()];
		//YOUR CODE GOES HERE
                int t = 0;
	        for(int x=0; x<Assignments.size(); x++) {
                    t = available(Assignments.get(x).deadline, homeworkPlan);
                    if(t != -1) {
                        homeworkPlan[Assignments.get(x).number] = t;
                    }
                    if(t == -1) {
                        homeworkPlan[Assignments.get(x).number] = 0;
                    }
                }
		
		return homeworkPlan;
	}
        
        //helper method
        public static int available(int deadline, int[] homeworkPlan) {
            boolean conflict = false;
            if(deadline == 0) {
                return -1;
            }
            for(int y=0; y<homeworkPlan.length; y++) {
                if(deadline == homeworkPlan[y]) {
                    conflict = true;
                }
                if(conflict == true) {
                    deadline = available(deadline - 1, homeworkPlan);
                    break;
                }
            }
            if(conflict == false) {
                return deadline;
            }
            return deadline;
        }
}
	



