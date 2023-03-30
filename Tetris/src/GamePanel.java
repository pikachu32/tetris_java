import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH=600;
    static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=25;
    static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=200;
    boolean running=false;
    Timer timer;
    Random random;
    int pieces=0;
    int PIECE_UNITS=4;
    final int a[]=new int [PIECE_UNITS];
    final int b[]=new int [PIECE_UNITS];
    final int m[][]=new int[SCREEN_HEIGHT/UNIT_SIZE][SCREEN_WIDTH/UNIT_SIZE];
    final char c[][]=new char[SCREEN_HEIGHT/UNIT_SIZE][SCREEN_WIDTH/UNIT_SIZE];

    char PIECE_TYPE;
    char direction='D';
    int score=0;
    GamePanel(){
        random=new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        newPiece();
        running=true;
        timer=new Timer(DELAY, this);
        timer.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running)
        {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++)
                for(int j=0;j<SCREEN_WIDTH/UNIT_SIZE;j++)
                {
                    if(m[i][j]==1)
                    {
                        switch(c[i][j])
                        {
                            case 'O':
                                g.setColor(Color.yellow);
                                break;
                            case 'I':
                                g.setColor(new Color(24, 186, 232));
                                break;
                            case'T':
                                g.setColor(new Color(154, 18, 164));
                                break;
                            case 'S':
                                g.setColor(Color.green);
                                break;
                            case 'Z':
                                g.setColor(Color.red);
                                break;
                            case 'J':
                                g.setColor(Color.blue);
                                break;
                            case 'L':
                                g.setColor(Color.orange);
                                break;

                        };
                        g.fillRect(j*UNIT_SIZE,i*UNIT_SIZE,UNIT_SIZE,UNIT_SIZE);
                    }
                }

            for(int i=0;i<PIECE_UNITS;i++)
            {
                switch(PIECE_TYPE)
                {
                    case 'O':
                        g.setColor(Color.yellow);
                        break;
                    case 'I':
                        g.setColor(new Color(24, 186, 232));
                        break;
                    case'T':
                        g.setColor(new Color(154, 18, 164));
                        break;
                    case 'S':
                        g.setColor(Color.green);
                        break;
                    case 'Z':
                        g.setColor(Color.red);
                        break;
                    case 'J':
                        g.setColor(Color.blue);
                        break;
                    case 'L':
                        g.setColor(Color.orange);
                        break;

                };
                g.fillRect(a[i],b[i],UNIT_SIZE,UNIT_SIZE);
            }
        }
        else
            gameOver(g);
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2=getFontMetrics(g.getFont());
        g.drawString("Score:"+score, (SCREEN_WIDTH - metrics2.stringWidth("Score:"+score))/2, g.getFont().getSize());

    }
    public void newPiece()
    {
        score++;
        int p=random.nextInt(7);
        switch(p) {
            case 0://O
                PIECE_TYPE='O';
                a[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
                b[0] = 0;
                a[1] = a[0] + UNIT_SIZE;
                b[1] = 0;
                a[2] = a[0];
                b[2] = UNIT_SIZE;
                a[3] = a[2] + UNIT_SIZE;
                b[3] = UNIT_SIZE;
                break;
            case 1:
                //I
                PIECE_TYPE='I';
                a[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
                b[0] = 0;
                a[1] = SCREEN_WIDTH / 2;
                b[1] = 0;
                a[2] = SCREEN_WIDTH / 2 + UNIT_SIZE;
                b[2] = 0;
                a[3] = SCREEN_WIDTH / 2 + (2 * UNIT_SIZE);
                b[3] = 0;
                break;
            case 2://T
                PIECE_TYPE='T';
                a[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
                b[0] = 0;
                a[1] = a[0] - UNIT_SIZE;
                b[1] = UNIT_SIZE;
                a[2] = a[0];
                b[2] = UNIT_SIZE;
                a[3] = a[0] + UNIT_SIZE;
                b[3] = UNIT_SIZE;
                break;
            case 3://S
                PIECE_TYPE='S';
                a[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
                b[0] = 0;
                a[1] = a[0] + UNIT_SIZE;
                b[1] = 0;
                a[2] = a[0] - UNIT_SIZE;
                b[2] = UNIT_SIZE;
                a[3] = a[0];
                b[3] = UNIT_SIZE;
                break;
            case 4://Z
                PIECE_TYPE='Z';
                a[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
                b[0] = 0;
                a[1] = a[0] + UNIT_SIZE;
                b[1] = 0;
                a[2] = a[1];
                b[2] = UNIT_SIZE;
                a[3] = a[1]+UNIT_SIZE;
                b[3] = UNIT_SIZE;
                break;
            case 5://J
                PIECE_TYPE='Z';
                a[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
                b[0] = 0;
                a[1] = a[0];
                b[1] = UNIT_SIZE;
                a[2] = a[0] + UNIT_SIZE;
                b[2] = UNIT_SIZE;
                a[3] = a[2] + UNIT_SIZE;
                b[3] = UNIT_SIZE;
                break;
            case 6://L
                PIECE_TYPE='L';
                a[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
                b[0] = 0;
                a[1] = a[0];
                b[1] = UNIT_SIZE;
                a[2] = a[0] - UNIT_SIZE;
                b[2] = UNIT_SIZE;
                a[3] = a[2] - UNIT_SIZE;
                b[3] = UNIT_SIZE;
                break;
        };

    }
    public void add()
    {
        for(int i=0;i<PIECE_UNITS;i++)
        {
            m[b[i]/UNIT_SIZE][a[i]/UNIT_SIZE]=1;
            c[b[i]/UNIT_SIZE][a[i]/UNIT_SIZE]=PIECE_TYPE;

        }
    }
    public void rotatePiece() {

        int[]newa=new int[PIECE_UNITS];
        int[]newb=new int[PIECE_UNITS];
        for(int i=0;i<PIECE_UNITS;i++)
        {
            newb[i]=a[i];
            newa[i]=-b[i];
        }
        int k=a[0]-newa[0];
        int j=b[0]-newb[0];
        for(int i=0;i<PIECE_UNITS;i++)
        {
            newa[i]=newa[i]+k;
            newb[i]=newb[i]+j;
        }
        int valid=1;
        for(int i=0;i<PIECE_UNITS;i++) {
            if(m[newa[i]/UNIT_SIZE][newb[i]/UNIT_SIZE]==1)
                valid=0;
        }
        if(valid==1)
            for(int i=0;i<PIECE_UNITS;i++) {
                a[i] = newa[i];
                b[i] = newb[i];
            }
        /*
        for(int i=0;i<PIECE_UNITS;i++)
        {
            int aux=a[i];
            a[i]=b[i];
            b[i]=aux;
        }*/

    }
    public void checkPosition()
    {

        for(int i=0;i<PIECE_UNITS;i++)
            if(b[i]>=SCREEN_HEIGHT-UNIT_SIZE)
            {
                add();
                newPiece();
            }
        for(int i=0;i<PIECE_UNITS;i++)
            if(m[b[i]/UNIT_SIZE+1][a[i]/UNIT_SIZE]==1)
            {
                add();
                newPiece();
            }
    }
    public void checkLine() {
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            int z=0;
            for (int j = 0; j < SCREEN_WIDTH / UNIT_SIZE; j++) {
                if (m[i][j] == 1) {
                    z++;
                }
            }
            if(z==SCREEN_WIDTH/UNIT_SIZE)
                deleteLine(i);
        }
    }
    public void deleteLine(int x){
        for(int i=x;i>0;i--)
            for(int j=0;j<SCREEN_WIDTH/UNIT_SIZE;j++) {
                m[i][j] = m[i - 1][j];
                c[i][j] = c[i - 1][j];
            }
        score=score+10;

    }

    public void move()
    {
        int k=0;
        switch(direction)
        {
            case 'D':
                for(int i=0;i<PIECE_UNITS;i++)
                    b[i]=b[i]+UNIT_SIZE;
                break;
            case 'R':
                for(int i=0;i<PIECE_UNITS;i++)
                    if(a[i]>=SCREEN_WIDTH-UNIT_SIZE || m[b[i]/UNIT_SIZE][a[i]/UNIT_SIZE+1]==1)
                    {
                        k=1;
                    }
                if(k==0)
                for(int i=0;i<PIECE_UNITS;i++)
                    a[i]=a[i]+UNIT_SIZE;
                direction='D';
                break;
            case 'L':
                for(int i=0;i<PIECE_UNITS;i++)
                    if(a[i]<=0 || m[b[i]/UNIT_SIZE][a[i]/UNIT_SIZE-1]==1)
                    {
                        k=1;
                    }
                if(k==0)
                for(int i=0;i<PIECE_UNITS;i++)
                    a[i]=a[i]-UNIT_SIZE;
                direction='D';
                break;
        }

    }
    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1=getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2=getFontMetrics(g.getFont());
        g.drawString("Score:"+score, (SCREEN_WIDTH - metrics2.stringWidth("Score:"+score))/2, g.getFont().getSize());
    }
    public void checkTop()
    {
        for(int i=0;i<SCREEN_WIDTH/UNIT_SIZE;i++)
            if(m[0][i]==1)
                running=false;
        if(!running)
        {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkPosition();
            checkLine();
            checkTop();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    rotatePiece();
                    break;

            }

        }
    }
}
