public class QueryBuilder {
	
	private String fname; 
	private String lname; 
	private String criterion; 
	private String assessItem; 
	private String semester;

	QueryBuilder(String fname, String lname, String criterion, String assessItem, String semester){
		this.fname = fname;
		this.lname = lname;
		this.criterion = criterion;
		this.assessItem = assessItem;
		this.semester = semester;
	}
	
	public String buildQuery(){
		String query = null;
		if(fname.equals("") && !criterion.equals("")){
			query = "SELECT avg(`SCORE`) AS AverageScore, min(`SCORE`) AS MinScore, max(`SCORE`) AS MaxScore "
					+ "FROM `GRADES` WHERE `CID` = '"+ criterion +"' GROUP BY `CID`;";
		}
		else if(fname.equals("")  && criterion.equals("") && !assessItem.equals("") && !semester.equals("")){
			query = "SELECT `AID`, AVG(SCORE) AS AverageScore, min(SCORE) AS MinScore, max(SCORE) AS MaxScore FROM `CRITERION` "
					+ "Join `GRADES` ON GRADES.CID = CRITERION.CID WHERE `AID` = '" + assessItem + semester +"' group by `AID`;";
		}
		else if(fname.equals("")  && criterion.equals("") && !assessItem.equals("") && semester.equals("")){
			query = "SELECT `AI`, AVG(`SCORE`) AS AverageScore, min(`SCORE`) AS MinScore, max(`SCORE`) AS MaxScore "
					+ "FROM `CRITERION` Join GRADES ON GRADES.CID = CRITERION.CID WHERE `AI` = '" + assessItem + "' group by `AI`;";
		}
		else if(!fname.equals("")  && criterion.equals("") && !assessItem.equals("") && semester.equals("")){
			query = "SELECT `AI` ,MAX(GRADES.SCORE) as Highest,MIN(GRADES.SCORE) as Lowest,AVG(GRADES.SCORE) as AVG "
					+ "FROM `GRADES` JOIN `CRITERION` ON GRADES.CID = CRITERION.CID JOIN "
					+ "(SELECT `CID`, `SCORE` FROM `GRADES` WHERE `STUDENTKEY` = "
					+ "(SELECT `STUDENTKEY` FROM `STUDENTS` WHERE `FNAME` = '"+ fname +"' AND LNAME = '"+ lname +"'))tmp ON GRADES.CID = tmp.CID "
							+ "GROUP BY `AI`;";
		}
		/**else if(fname.equals("")  && !criterion.equals("") && !assessItem.equals("") && semester.equals("")){
			query = "SELECT `CID`, avg(`SCORE`) AS AverageScore, min(`SCORE`) AS MinScore, max(`SCORE`) AS MaxScore "
					+ "FROM `GRADES` ORDER BY `CID`;";
		}**/
		else{
			//DEFUALT
			query = "Select `LNAME`,`FNAME`, `CID`, `SCORE` FROM `STUDENTS` Join `GRADES` on STUDENTS.STUDENTKEY = GRADES.STUDENTKEY ORDER BY `LNAME`, `CID`;";
		}

		return query;
	}
	
	public String allStudents(){
		return "SELECT * FROM `STUDENTS`;";
	}
	
	public String criterionScores(){
		return "SELECT `CID`, avg(`SCORE`) AS AverageScore, min(`SCORE`) AS MinScore, max(`SCORE`) AS MaxScore "
				+ "FROM `GRADES` ORDER BY `CID`;";
	}
	
	public String aiScores(){
		return "SELECT `AI`, avg(SCORE) AS AverageScore, min(SCORE) AS MinScore, max(SCORE) AS MaxScore FROM `CRITERION` Join GRADES ON GRADES.CID = CRITERION.CID group by `AI`;";
	}
}
