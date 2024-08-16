import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener
{
    JLabel heading,clockLable;
    Font font=new Font("",Font.BOLD,40);
    Font fontox=new Font("",Font.BOLD,60);
    JPanel mainPanal;
    JButton btns[] =new JButton[9];

    // game variable instence

    int gameChances[] ={2,2,2,2,2,2,2,2,2};
    int activePlayar=0;
    int wps[][]={
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {0,4,8},
            {2,4,6}
    };
    int winner=2;
    boolean gameover=false;

    MyGame()
    {
        System.out.println("Creating new instance of Game");
        setTitle("Tic Tac Toe by Sahil Dudhal");
        setSize(650,650);
        ImageIcon icon = new ImageIcon("src/img/logo.png");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGui();
        setVisible(true);

    }
    private void createGui()
    {
        this.getContentPane().setBackground(Color.decode("#17202A"));
        this.setLayout(new BorderLayout());
        heading=new JLabel("Tic Tac Toe");
        heading.setFont(font);
       // heading.setIcon(new ImageIcon("src/img/logo.png"));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.WHITE);
       // heading.setHorizontalTextPosition(SwingConstants.CENTER);
       // heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(heading,BorderLayout.NORTH);

        //craeting object of Jlable for clock
        clockLable=new JLabel("Clock");
        clockLable.setFont(font);
        clockLable.setHorizontalAlignment(SwingConstants.CENTER);
        clockLable.setForeground(Color.WHITE);
        this.add(clockLable,BorderLayout.SOUTH);

        Thread t=new Thread()
        {
            public void run()
            {

               try
               {
                   while(true)
                   {
                       String datetime = new Date().toLocaleString();
                       clockLable.setText(datetime);

                       Thread.sleep(1000);
                   }
               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
            }
        };
        t.start();

        // panal section
        mainPanal=new JPanel();
        mainPanal.setLayout(new GridLayout(3,3));
        for(int i =1;i<=9;i++)
        {
            JButton btn=new JButton();
            //btn.setIcon(new ImageIcon("src/img/o.png"));
            btn.setFont(fontox);
            btn.setBackground(Color.decode("#566573"));
            mainPanal.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(mainPanal,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton currentButton=(JButton)e.getSource();
        String str =currentButton.getName();
        //System.out.println(str);
        int name=Integer.parseInt(str.trim());

        if(gameover==true)
        {
            JOptionPane.showMessageDialog(this,"Game Over !");
            return;
        }

        // chances
        if(gameChances[name]==2)
        {
            if(activePlayar==1)
            {
                currentButton.setText("O");
                gameChances[name]=activePlayar;
                activePlayar=0;
            }
            else
            {
                currentButton.setText("X");
                gameChances[name]=activePlayar;
                activePlayar=1;
            }

            // find winner
            for(int []temp:wps)
            {
                if((gameChances[temp[0]]==gameChances[temp[1]]) && (gameChances[temp[1]]==gameChances[temp[2]]) && (gameChances[temp[2]]!=2) )
                {
                    winner=gameChances[temp[0]];
                    gameover=true;
                    JOptionPane.showMessageDialog(this,"Playar "+winner+" has won the game");
                    int i = JOptionPane.showConfirmDialog(this,"Do you want to play again ?");
                    if(i==0)
                    {
                        this.setVisible(false);
                        new MyGame();
                    }
                    else if (i==1)
                    {
                        System.exit(0);
                    }
                    else
                    {

                    }
                    System.out.println(i);
                    break;
                }
            }

            // ................draw logic
            int c=0;
            for(int x:gameChances)
            {
                if(x==2)
                {
                    c++;
                    break;
                }
            }

            if(c==0 && gameover==false)
            {
                JOptionPane.showMessageDialog(this,"Its Draw......");
                int i = JOptionPane.showConfirmDialog(this,"Do You want to play again ? ");
                if(i==0)
                {
                    this.setVisible(false);
                    new MyGame();
                }
                else if (i==1)
                {
                    System.exit(0);
                }
                gameover=true;
            }

        }
        else
        {
            JOptionPane.showMessageDialog(this,"Position Already Occupied");
        }
    }
}
