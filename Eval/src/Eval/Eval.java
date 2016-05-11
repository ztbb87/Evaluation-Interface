package Eval;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author brilaw&friends
 */
public class Eval extends JFrame implements ActionListener, ItemListener
{
    //DECLARE THE ELEMENTS OR OBJECTS THAT YOU WILL PUT IN YOUR FRAME
    //NOTICE HOW A PANEL IS CREATED FOR EACH ONE THIS WILL MAKE IT EASIER BUILD
    private JPanel teamPanel;
    public JLabel teamLabel;
    private JComboBox teamComboBox;
    public JComboBox imagesComboBox;
    //Questions 1-4 Sliders
    public JSlider Q1slider;
    public JLabel Q1label;
    public JPanel Q1panel;
    
    public JSlider Q2slider;
    public JLabel Q2label;
    public JPanel Q2panel;
    
    public JSlider Q3slider;
    public JLabel Q3label;
    public JPanel Q3panel;
    
    public JSlider Q4slider;
    public JLabel Q4label;
    public JPanel Q4panel;
    //Comment Box
    public JPanel commentsPanel;
    public JLabel commentsLabel;
    public JTextArea commentsText;
    //Calculating Average
    private JPanel calcAvgPanel;
    public JLabel avgLabel;
    private JButton calcavgButton;
    public JTextArea avgText;
    //Clear & Submit Buttons
    private JPanel buttonPanel;
    private JButton clearButton;
    private JButton submitButton;

   //these are fields that will be used to hold the values pulled from the interface
    String teamname;
    int q1;
    int q2;
    int q3;
    int q4;
    String comments;
    double teamavg;
    double teamavg1;

    // instance variables used to manipulate database
    private Connection myConnection;
    private Statement myStatement;
    private ResultSet myResultSet;

    //MAIN METHOD: NOTICE WE TAKE IN THE ARGUMENTS THAT ARE
    //PASSED IN AND INSTANTIATE OUR CLASS WITH THEM
    public static void main(String args[])
    {
      // check command-line arguments
      //if ( args.length == 2 )
      //{
            // get command-line arguments
            String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
            //String databaseDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
            String databaseURL = "jdbc:derby://localhost:1527/Eval;create=true";

            // create new Eval
            Eval eval = new Eval( databaseDriver, databaseURL );
            eval.createUserInterface();
            eval.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
      //}
      //else // invalid command-line arguments
      //{
            //System.out.println( "Usage: java EVAL needs databaseDriver databaseURL" );
      //}
    }
  
    //CONSTRUCTOR: WE SET UP OUR DATABASE HERE THEN MAKE A CALL
    //TO A FUNCTION CALLED CREATEUSERINTERFACE TO BUILD OUR GUI
    public Eval(String databaseDriver, String databaseURL)
    {
      // establish connection to database
      try
      {
         //load Sun driver
         //Class.forName( databaseDriver );
         DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
         // connect to database
         myConnection = DriverManager.getConnection( databaseURL );

         // create Statement for executing SQL
         myStatement = myConnection.createStatement();
      }
      catch ( SQLException exception )
      {
          
      }
      //catch ( ClassNotFoundException exception )
      //{
      //   exception.printStackTrace();
      //}
      //set up accountNumberJComboBox
      //createUserInterface(); 
      // set up GUI
    }
  
    private void createUserInterface()
    {
      //get content pane for attaching GUI components
      Container contentPane = getContentPane();
      
      contentPane.setLayout(null);
     
      //INSTRUCTOR COMBO BOX SET UP
      //set up Instructor Panel
     
      //enable explicit positioning of GUI components
      teamPanel = new JPanel();
      teamPanel.setBounds(40, 20, 276, 48 );
      teamPanel.setBorder( BorderFactory.createEmptyBorder());
      teamPanel.setLayout( null );
      contentPane.add( teamPanel );

      // set up Instructor Label
      teamLabel = new JLabel();
      teamLabel.setBounds( 1, 15, 100, 20 );
      teamLabel.setText( "Teams:" );
      teamPanel.add( teamLabel );

      // set up accountNumberJComboBox
      teamComboBox = new JComboBox(); 
      teamComboBox.setBounds( 75, 15, 200, 25 );
      teamComboBox.addItem( "Select Team" );
      teamComboBox.setSelectedIndex( 0 );
      teamPanel.add( teamComboBox );
      
      //Question 1 Slider
      Q1panel = new JPanel();
      Q1panel.setBounds(41, 100, 276, 60);
      Q1panel.setBorder(BorderFactory.createEmptyBorder());
      Q1panel.setLayout(null);
      contentPane.add(Q1panel);
      
      Q1label = new JLabel();
      Q1label.setBounds( 41, 5, 250, 170 );
      Q1label.setText( "Q1 Technical:" );
      contentPane.add(Q1label);
      
      Q1slider = new JSlider(1,8,1);
      Q1slider.setMinorTickSpacing(1);
      Q1slider.setMajorTickSpacing(1);
      Q1slider.setPaintTicks(true);
      Q1slider.setPaintLabels(true);
      Q1slider.setBounds(20, 5, 250, 50);
      Q1panel.add(Q1slider);
      
      //Question 2 Slider
      Q2panel = new JPanel();
      Q2panel.setBounds(41, 200, 276, 60);
      Q2panel.setBorder(BorderFactory.createEmptyBorder());
      Q2panel.setLayout(null);
      contentPane.add(Q2panel);
      
      Q2label = new JLabel();
      Q2label.setBounds( 41, 105, 250, 170 );
      Q2label.setText( "Q2 Usefulness:" );
      contentPane.add(Q2label);
      
      Q2slider = new JSlider(1,8,1);
      Q2slider.setMinorTickSpacing(1);
      Q2slider.setMajorTickSpacing(1);
      Q2slider.setPaintTicks(true);
      Q2slider.setPaintLabels(true);
      Q2slider.setBounds(20, 5, 250, 50);
      Q2panel.add(Q2slider);
      
      //Question 3 Slider
      Q3panel = new JPanel();
      Q3panel.setBounds(41,300, 276, 60);
      Q3panel.setBorder(BorderFactory.createEmptyBorder());
      Q3panel.setLayout(null);
      contentPane.add(Q3panel);
      
      Q3label = new JLabel();
      Q3label.setBounds( 41, 205, 250, 170 );
      Q3label.setText( "Q3 Clarity:" );
      contentPane.add(Q3label);
      
      Q3slider = new JSlider(1,8,1);
      Q3slider.setMinorTickSpacing(1);
      Q3slider.setMajorTickSpacing(1);
      Q3slider.setPaintTicks(true);
      Q3slider.setPaintLabels(true);
      Q3slider.setBounds(20, 5, 250, 50);
      Q3panel.add(Q3slider);
      
      //Question 4 Slider
      Q4panel = new JPanel();
      Q4panel.setBounds(41,400, 276, 60);
      Q4panel.setBorder(BorderFactory.createEmptyBorder());
      Q4panel.setLayout(null);
      contentPane.add(Q4panel);
      
      Q4label = new JLabel();
      Q4label.setBounds( 41, 305, 250, 170 );
      Q4label.setText( "Q4 Overall:" );
      contentPane.add(Q4label);
      
      Q4slider = new JSlider(1,8,1);
      Q4slider.setMinorTickSpacing(1);
      Q4slider.setMajorTickSpacing(1);
      Q4slider.setPaintTicks(true);
      Q4slider.setPaintLabels(true);
      Q4slider.setBounds(20, 5, 250, 50);
      Q4panel.add(Q4slider);
      
      //Comment Box
      commentsPanel = new JPanel();
      commentsPanel.setBounds(40,500,276,60);
      commentsPanel.setBorder(BorderFactory.createEmptyBorder());
      commentsPanel.setLayout(null);
      contentPane.add(commentsPanel);
      
      commentsLabel = new JLabel();
      commentsLabel.setBounds( 41, 405, 250, 170 );
      commentsLabel.setText( "Comments:" );
      contentPane.add(commentsLabel);
      
      commentsText = new JTextArea(10,20);
      commentsText.setBounds(20, 5, 250, 50);
      commentsText.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
      commentsPanel.add(commentsText);
      
      //Calculate Average Panel
      calcAvgPanel = new JPanel();
      calcAvgPanel.setBounds(40,575,276,60);
      calcAvgPanel.setBorder(BorderFactory.createEmptyBorder());
      calcAvgPanel.setLayout(null);
      
      avgLabel = new JLabel();
      avgLabel.setBounds( 41, 490, 300, 170 );
      avgLabel.setText( "Computed Average from Questions Above:" );
      contentPane.add(avgLabel);
      
      calcavgButton = new JButton("Calculate Avg.");
      calcavgButton.setBounds(10,20,120,30);
      calcAvgPanel.add(calcavgButton);
      contentPane.add(calcAvgPanel);
      
      avgText = new JTextArea(10,20);
      avgText.setBounds(175, 15, 40, 30);
      avgText.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
      calcAvgPanel.add(avgText);
      contentPane.add(calcAvgPanel);
      
      calcavgButton.addActionListener(this);
      
      //Submit & Clear Panel
      buttonPanel = new JPanel();
      buttonPanel.setBounds(40, 650, 276, 60);
      buttonPanel.setBorder( BorderFactory.createEmptyBorder() );
      buttonPanel.setLayout(null);
      
      clearButton = new JButton("Clear");
      clearButton.setBounds(50,15,78,40);
      buttonPanel.add(clearButton);
      
      clearButton.addActionListener(this);
     
      submitButton = new JButton("Submit");
      submitButton.setBounds(150, 15, 78, 40);
      buttonPanel.add(submitButton);
      contentPane.add(buttonPanel);
     
      submitButton.addActionListener(this);
      
      loadTeams();

      setTitle( "Evaluation Interface" );   // set title bar string
      setSize( 355, 750 ); // set window size
      setVisible( true );  // display window
    }

  
    private void loadTeams()
    {
         // get all account numbers from database
      try
      {
       
          myResultSet = myStatement.executeQuery( "SELECT TEAMNAME FROM APP.TEAMS");
      
         while ( myResultSet.next() )
         {
               teamComboBox.addItem(myResultSet.getString( "TEAMNAME" ) );
         }

         myResultSet.close(); // close myResultSet
       

      } // end try

      catch ( SQLException exception )
      {
      }
    }

    @Override
   public void actionPerformed(ActionEvent event)
    {
       if(event.getSource().equals(calcavgButton))
       {
           q1 = Q1slider.getValue();
           q2 = Q2slider.getValue();
           q3 = Q3slider.getValue();
           q4 = Q4slider.getValue();
           teamavg1=(q1+q2+q3+q4)/4;
           avgText.setText(Double.toString(teamavg1));
       }
       else if (event.getSource().equals(submitButton))
       {
           updateTeams();
       }
       else if (event.getSource().equals(clearButton))
       {
           commentsText.setText("");
           Q1slider.setValue(1);
           Q2slider.setValue(1);
           Q3slider.setValue(1);
           Q4slider.setValue(1);
           avgText.setText("");
           teamComboBox.setSelectedIndex(0);
       }
        
        
       
        //JOptionPane.showMessageDialog( null, "You pressed: " + teamComboBox.getSelectedItem().toString() );
        //Object obj = teamComboBox.g
        //teamname = teamComboBox.getSelectedItem().toString();
        //int x = teamComboBox.getSelectedIndex();
       // q1 = 5;
        //q2 = 3;
        //updateTeams();
        //System.out.println(teamComboBox.getSelectedIndex() + "     " + (String)teamComboBox.getSelectedItem());

    }
  
   // @Override
   /* public void itemStateChanged(ItemEvent event)
    {
      
        if ( event.getSource() == rb1 && event.getStateChange() == ItemEvent.SELECTED)
        {
            q1 = Integer.parseInt(rb1.getText());
        }
        else if (event.getSource() == rb2 && event.getStateChange() == ItemEvent.SELECTED)
        {
            q1 = Integer.parseInt(rb2.getText());
        }
        else if (event.getSource() == rb3 && event.getStateChange() == ItemEvent.SELECTED)
        {
           q1 = Integer.parseInt(rb3.getText());
        }
        else if( event.getSource() == rb1 && event.getStateChange() == ItemEvent.DESELECTED)
        {
            JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
        }
    }*/

    private void updateTeams()
   {
      // update balance in database
      try
      {
          teamname=teamComboBox.getSelectedItem().toString();
          String s = commentsText.getText();
          //
          String sql1 = "UPDATE APP.TEAMS SET Q1 = " + q1 + " WHERE " + "TEAMNAME = " + "'" + teamname + "'";
          String sql2 = "UPDATE APP.TEAMS SET Q2 = " + q2 + " WHERE " + "TEAMNAME = " + "'" + teamname + "'";
          String sql3 = "UPDATE APP.TEAMS SET Q3 = " + q3 + " WHERE " + "TEAMNAME = " + "'" + teamname + "'";
          String sql4 = "UPDATE APP.TEAMS SET Q4 = " + q4 + " WHERE " + "TEAMNAME = " + "'" + teamname + "'";
          String sql5 = "UPDATE APP.TEAMS SET AVGSCORE = " + teamavg1 + " WHERE " + "TEAMNAME = " + "'" + teamname + "'";
          String sql6 = "UPDATE APP.TEAMS SET COMMENTS = " + "'" + s + "'" + "WHERE " + "TEAMNAME = " + "'" + teamname + "'";
          
          
          
          myStatement.executeUpdate(sql1);
          myStatement.executeUpdate(sql2);
          myStatement.executeUpdate(sql3);
          myStatement.executeUpdate(sql4);
          myStatement.executeUpdate(sql5);
          myStatement.executeUpdate(sql6);
          //myStatement.executeUpdate(sql2);
        
      }
      catch ( SQLException exception )
      {
      }
   }

    @Override
   public void itemStateChanged( ItemEvent event )
   {
//        if ( event.getStateChange() == ItemEvent.SELECTED )
//        {
//                int x = teamComboBox.getSelectedIndex();
//        }
   }

   static {
//        final JFrame frame = new JFrame("JSlider Demo");
// 
//        // create a slider
//        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 7, 2);
//        
//        slider.setMinorTickSpacing(1);
//        slider.setMajorTickSpacing(2);
//        slider.setPaintTicks(true);
//        slider.setPaintLabels(true);
//        slider.setLabelTable(slider.createStandardLabels(2));
//        
//        //
//        frame.setLayout(new FlowLayout());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//        frame.getContentPane().add(slider);
//        frame.setVisible(true);
//    }

}
}