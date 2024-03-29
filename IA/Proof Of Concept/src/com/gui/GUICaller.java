package com.gui;

import com.asset.Asset;

import javax.swing.*;

public class GUICaller {
    public GUICaller(){
    }

    public static void startup(){
        // TODO: Start everything off here, manage caching etc... Start instantiating assets from watchlist etc... and save it a arraylist
        Login();
    }

    public static void HomeScreen() throws Exception {
        System.out.println("SEQUENCE: Home Screen");
        JFrame frame = new JFrame("Home Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomeScreen myGUI = new HomeScreen(700, 450);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public static void AssetInfo(Asset asset) throws Exception {
        System.out.println("SEQUENCE: Stock Info");
        JFrame frame = new JFrame("Stock");
        AssetInfo myGUI = new AssetInfo(600, 630, asset);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public static void SimulationResults(Asset asset) throws Exception {
        System.out.println("SEQUENCE: SimulationResults");
        JFrame frame = new JFrame("Simulation Results");
        SimulationResults myGUI = new SimulationResults(600, 630, asset);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public static void Simulate(Asset asset, int sma1, int sma2) throws Exception {
        System.out.println("SEQUENCE: Simulate");
        JFrame frame = new JFrame("Backdating");
        SimulateGraphically myGUI = new SimulateGraphically(600, 630, asset, sma1, sma2);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }


    public static void Login(){
        System.out.println("SEQUENCE: Login");
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login myGUI = new Login(350, 150, frame); // passing the frame so that it can close itself...
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
