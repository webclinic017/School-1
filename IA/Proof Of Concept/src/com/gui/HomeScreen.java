package com.gui;

import com.asset.Asset;
import com.asset.NewsData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.user.Watchlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

// TODO: Considering making this the home page so will include other info as well...
public class HomeScreen extends JPanel {
    // canvas for other GUI widgets

    JButton button;
    JTextField textfield;
    JLabel label;
    JLabel label1;

    public HomeScreen(int width, int height) throws Exception {
        System.out.println("SEQUENCE: GUI constructor");
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);

        JLabel search = new JLabel("Search");
        search.setFont(new Font("Verdana", Font.BOLD, 20));
        search.setBounds(70, 50, 150, 50);
        add(search);

        button = new JButton("\uD83D\uDD0D"); // search icon
        button.setBounds(190,99, 50, 30);

        textfield = new JTextField();
        textfield.setBounds(70,100, 100, 30);

        // Showing watchlist on the side screen
        Watchlist Watchlists = new Watchlist("default");
        String[] watchlist = Watchlists.get();

        JLabel label = new JLabel("Watchlist");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setBounds(400, 50, 150, 50);
        add(label);

        int counter = 20;
        JButton[] watchlistlabel = new JButton[5];
        for (int i = 0; i < 5; i++) {

            Asset asset = Asset.create(watchlist[i]);

            watchlistlabel[i] = new JButton("  " + asset.ticker);
            watchlistlabel[i].setFont(new Font("Verdana", Font.BOLD,12));
            watchlistlabel[i].setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            watchlistlabel[i].setBounds(400,(i*60)+100, 140, 50);
            watchlistlabel[i].setHorizontalAlignment(SwingConstants.LEFT);
            watchlistlabel[i].setContentAreaFilled(false); // TODO: Try how this differs for MacOS



            int finalI = i;
            watchlistlabel[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        GUICaller.AssetInfo(Asset.create(watchlist[finalI]));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            add(watchlistlabel[i]);
        }



        add(button);
        add(textfield);


        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String textFieldValue = textfield.getText().toUpperCase();
                System.out.println(textFieldValue);

                try {
                    Asset asset = Asset.create(textFieldValue);
                    GUICaller.AssetInfo(asset);

                } catch (Exception e) {
                    System.out.println("Stock Doesn't Exists"); // TODO: check if it's true if not print the error message
                    System.out.println(e);
                }
            }
        });


        // News TODO: Add this...
        NewsData NewsData = new NewsData();
        System.out.println(Arrays.toString(watchlist));
        counter = 20;
        JButton[] newslabel = new JButton[5];
        for (int i = 0; i < 2; i++) {
            System.out.println(watchlist[i]);

            JsonArray response = NewsData.get(watchlist[i], 1);
            JsonObject newsdata = response.get(0).getAsJsonObject().get("news").getAsJsonArray().get(0).getAsJsonObject();

            String header = newsdata.get("headline").getAsString();
            String summary = newsdata.get("summary").getAsString();
            String image = newsdata.get("images").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();

            newslabel[i] = new JButton();
            String imageInHtml = "<html><h5>" + header + "</h5><h6>" + summary + "</h6>  </html>";

            newslabel[i].setText(imageInHtml);

//            newslabel[i].setFont(new Font("Verdana", Font.BOLD,15));
//            newslabel[i].setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            newslabel[i].setBounds(70,(i*110)+170, 300, 105);
//            newslabel[i].setHorizontalAlignment(SwingConstants.LEFT);
            newslabel[i].setContentAreaFilled(false); // TODO: Try how this differs for MacOS
            add(newslabel[i]);
        }


        JLabel news = new JLabel("News");
        news.setFont(new Font("Verdana", Font.BOLD, 20));
        news.setBounds(70, 130, 150, 50);
        add(news);

    }
}