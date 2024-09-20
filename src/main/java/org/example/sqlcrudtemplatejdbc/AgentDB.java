package org.example.sqlcrudtemplatejdbc;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AgentDB {
    public static Connection GetConnection(){
        String db_url = "";
        String db_user = "";
        String db_pass = "";

        //retrieve connection properties
        //add a connection.properties files to the root of your c: drive
        //contents should be in the following format

        // url=jdbc:mysql://localhost:3306/databasename
        // user=username
        // password=password

        try {
            FileInputStream fis = new FileInputStream("c:\\connection.properties");
            Properties props = new Properties();

            props.load(fis);
            db_url  = (String) props.get("url");
            db_user = (String) props.get("user");
            db_pass = (String) props.get("password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(db_url,db_user,db_pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } // end sql/runtime catch error
        return conn;
    } // end GetConnection

    //READ method
    public static ObservableList<Agent> GetAgents() throws SQLException {
        ObservableList<Agent> agents = FXCollections.observableArrayList();
        Agent agent;
        Connection conn = GetConnection();
        Statement st = conn.createStatement();

        //Query string to select
        String query = "SELECT * FROM agents;";
        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        Integer columnSize = rsmd.getColumnCount();

        //Loop through table and display data
        while(rs.next()){
            agent = new Agent(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getInt(9)
            ); // end new agent
            agents.add(agent);
        } // end while loop
        conn.close();
        return agents;
    } // end GetAgents

    //CREATE method
    public static int InsertAgent(Agent agent) throws SQLException {
        int numRows = 0;
        Connection conn = GetConnection();
        String sql = "INSERT INTO agents (AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId, AgtIsActive) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, agent.getAgentId());
        ps.setString(2, agent.getAgtFirstName());
        ps.setString(3, agent.getAgtMiddleInitial());
        ps.setString(4, agent.getAgtLastName());
        ps.setString(5, agent.getAgtBusPhone());
        ps.setString(6, agent.getAgtEmail());
        ps.setString(7, agent.getAgtPosition());
        ps.setInt(8, agent.getAgencyId());
        ps.setInt(9, agent.getAgtIsActive());

        //execute sql and close connection
        numRows = ps.executeUpdate();
        conn.close();
        return numRows;
    } // end InsertAgent

    //UPDATE method
    public static int UpdateAgent(int agentId, Agent agent) throws SQLException {
        int numRows = 0;
        Connection conn = GetConnection();
        String sql = "UPDATE agents SET " +
                     "AgentId = ?, " +
                     "AgtFirstName = ?, " +
                     "AgtMiddleInitial = ?, " +
                     "AgtLastName = ?, " +
                     "AgtBusPhone = ?, " +
                     "AgtEmail = ?, " +
                     "AgtPosition = ?, " +
                     "AgencyId = ?, " +
                     "AgtIsActive = ? " +
                     "WHERE AgentId = ? ";

        //Generate the prepared statement for the database
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, agentId);
        ps.setString(2, agent.getAgtFirstName());
        ps.setString(3, agent.getAgtMiddleInitial());
        ps.setString(4, agent.getAgtLastName());
        ps.setString(5, agent.getAgtBusPhone());
        ps.setString(6, agent.getAgtEmail());
        ps.setString(7, agent.getAgtPosition());
        ps.setInt(8, agent.getAgencyId());
        ps.setInt(9, agent.getAgtIsActive());
        ps.setInt(10, agentId);

        //execute sql and close connection
        numRows = ps.executeUpdate();
        conn.close();
        return numRows;
    } // end UpdateAgent

    //DELETE method
    public static int DeleteAgent(int agentId) throws SQLException {

        //open connection
        int numRows = 0;
        Connection conn = GetConnection();

        //delete sql query
        String sql = "DELETE FROM agents WHERE AgentId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,agentId);

        //execute query and close connection
        numRows = ps.executeUpdate();
        conn.close();
        return numRows;
    }

} // end class
