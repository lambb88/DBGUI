import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class Table extends JFrame{
	JTextField aiField;
	JTextField criteriaField;
	JTextField semesterField;
	JTextField fnameField;
	JTextField lnameField;
	JScrollPane scrollpane;
	ResultSetTableModelFactory rstm;

	  public Table() throws ClassNotFoundException, SQLException {
	    super("Database Test Frame");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    
	    setSize(350, 200);
	    
	    JPanel p1 = new JPanel();
	    p1.setLayout(new GridLayout(3, 2));
	    p1.add(new JLabel("AssessmentItem"));
	    p1.add(aiField = new JTextField());
	    p1.add(new JLabel("Criteria"));
	    p1.add(criteriaField = new JTextField());
	    p1.add(new JLabel("Semester"));
	    p1.add(semesterField = new JTextField());
	    p1.add(new JLabel("First Name"));
	    p1.add(fnameField = new JTextField());
	    p1.add(new JLabel("Last Name"));
	    p1.add(lnameField = new JTextField());
	    
	    JButton search = new JButton("Search");
	    search.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	QueryBuilder q = new QueryBuilder(fnameField.getText(), lnameField.getText(), criteriaField.getText(), aiField.getText(), semesterField.getText());
	    	try {
				rstm = new ResultSetTableModelFactory();
			} catch (ClassNotFoundException e1) {
				// do nothing
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  	    QueryTableModel qtm = null;
			try {
				qtm = rstm.getResultSetTableModel(q.buildQuery());
			} catch (SQLException e1) {
				// do nothing
				e1.printStackTrace();
			}
	  	    JTable table = new JTable(qtm);
	  	    scrollpane = new JScrollPane(table);
	      }
	    });
	    p1.add(search);
	    getContentPane().add(p1, BorderLayout.NORTH);
	    getContentPane().add(scrollpane, BorderLayout.CENTER);
	  }

	  public static void main(String args[]) throws ClassNotFoundException, SQLException {
	    Table tt = new Table();
	    tt.setVisible(true);
	  }
	}

	//QueryTableModel.java
	//A basic implementation of the TableModel interface that fills out a Vector of
	//String[] structures from a query's result set.
	//

	class QueryTableModel extends AbstractTableModel {
		private ResultSet results ; // The ResultSet to interpret to bind to the model
	    private ResultSetMetaData metadata ; // Additional information about the results
	    private int numcols , numrows ; // How many rows and columns in the table
	   
	    /**
	    * Creates a TableModel from a ResultSet .
	    **/
	    QueryTableModel(ResultSet results) throws SQLException {
	        this.results = results ;
	        metadata = results.getMetaData (); // Get metadata on them
	        numcols = metadata.getColumnCount (); // How many columns ?
	        results.last (); // Here ’s the kludge -- Move to last row
	        numrows = results.getRow (); // And use getRow to determine how many rows
	    }
	    
	    // These two TableModel methods return the size of the table
	    public int getColumnCount () { return numcols ; }
	    public int getRowCount () { return numrows ; }
	    
	    // This TableModel method returns columns names from the
	    // ResultSetMetaData so that we can provide column labels in the
	    // JTable display
	    public String getColumnName(int column) {
	        try {
	            return metadata.getColumnLabel(column +1);
	        } catch (SQLException e) { return e. toString (); }
	    }
	    
	    /**
	    * Returns the value , as a String , at each cell of the table .
	    * Note that SQL row and column numbers start at 1, but TableModel
	    * column numbers start at 0.
	    **/
	    public Object getValueAt(int row , int column) {
	        try {
	            results.absolute(row +1); // Go to the specified row
	            Object o = results.getObject(column +1); // Get value of the column
	            if (o == null) return null ;
	            else return o. toString (); // Convert it to a string
	        } 
	        catch ( SQLException e) { return e. toString (); }
	    }
	    
	    public void close() {
	        try { results.getStatement(). close(); }
	        catch ( SQLException e) {};
	    }
	    
	    protected void finalize () { close(); }
	  }
	class ResultSetTableModelFactory {
	    Connection connection;
	    
	    /** The constructor method uses the argument to create db Connection */
	    public ResultSetTableModelFactory() throws ClassNotFoundException , SQLException
	    {
	       try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// do nothing
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// do more nothing
			e.printStackTrace();
		}
	       String dbURL = "jdbc:mysql://webdev.cs.uwosh.edu:4381/";
	       String dbname = "lambb88";
	       String user = "lambb88";
	       String pwd = "j477488";
	        connection = DriverManager.getConnection( dbURL+dbname, user,pwd);
	    }
	    
	    /**
	    * Takes a SQL query , pass it to the database , obtain the results
	    * as a ResultSet , and return a ResultSetTableModel object
	    **/
	    public QueryTableModel getResultSetTableModel(String query)throws SQLException
	    {
	        if ( connection == null )
	        throw new IllegalStateException ("Connection already closed .");
	        // Create a Statement object that will be used to execute the
	        // query . The arguments specify that the returned ResultSet
	        // will be scrollable and read - only . The scrollable property
	        // is a necessary kludge to determine the number of rows in
	        // the table / ResultSet
	        Statement statement = connection. createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY );
	        // Run the query , creating a ResultSet
	        ResultSet r = statement.executeQuery(query);
	        // Create and return a TableModel for the ResultSet
	        return new QueryTableModel(r);
	    }
	    
	    public void close () {
	        try { connection.close(); } // Try to close the connection
	        catch ( Exception e) {} // Do nothing on error
	        connection = null ;
	    }
	    
	    protected void finalize () { 
	        close (); 
	    }
	}

