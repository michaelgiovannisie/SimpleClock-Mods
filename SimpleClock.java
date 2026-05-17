//package SimpleClock;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.*;


public class SimpleClock extends JFrame implements Runnable{
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton formatButton;
        JButton gmtButton;
        String time;
        String day;
        String date;
        boolean hourFormat = false;
        boolean gmtFormat = false;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new GridLayout(5, 1, 0, 15));
            ((JComponent) this.getContentPane())
                .setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            this.setSize(900, 450);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.YELLOW);
            timeLabel.setOpaque(true);
            timeLabel.setHorizontalAlignment(JLabel.CENTER);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
            dayLabel.setHorizontalAlignment(JLabel.CENTER);
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));
            dateLabel.setHorizontalAlignment(JLabel.CENTER);
            formatButton = new JButton("24 Hour");
            formatButton.addActionListener(e -> {
            if (hourFormat == false) {
                timeFormat = new SimpleDateFormat("HH:mm:ss");
                if (gmtFormat) {
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                } else {
                    timeFormat.setTimeZone(TimeZone.getDefault());
                }
                hourFormat = true;
                formatButton.setText("12 Hour");
            } else {
                timeFormat = new SimpleDateFormat("hh:mm:ss a");
                if (gmtFormat) {
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                } else {
                    timeFormat.setTimeZone(TimeZone.getDefault());
                }
                hourFormat = false;
                formatButton.setText("24 Hour");
            }
        });
            gmtButton = new JButton("GMT");
            gmtButton.addActionListener(e -> {
                if(gmtFormat == false) {
                    TimeZone gmt = TimeZone.getTimeZone("GMT");
                    timeFormat.setTimeZone(gmt);
                    dayFormat.setTimeZone(gmt);
                    dateFormat.setTimeZone(gmt);
                    gmtFormat = true;
                    gmtButton.setText("Local Time");
                } else {
                    TimeZone local = TimeZone.getDefault();
                    timeFormat.setTimeZone(local);
                    dayFormat.setTimeZone(local);
                    dateFormat.setTimeZone(local);
                    gmtFormat = false;
                    gmtButton.setText("GMT");
                }
            });
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(formatButton);
            this.add(gmtButton);
            this.setVisible(true);

            Thread thread = new Thread(this);
            thread.start();
        }
    
        public void run() {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);
    
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }
    }
