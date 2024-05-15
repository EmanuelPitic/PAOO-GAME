package PaooGame.Display;

import javax.swing.*;
import java.awt.*;

/*! \class Display
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul frame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */

public class Display {

    //Draw images to the canvas
    //Display the canvas using the JFrame

    private JFrame frame;       /*!< fereastra principala a jocului*/
    private Canvas canvas;      /*!< "panza/tablou" in care se poate desena*/

    private String title;       /*!< titlul ferestrei*/
    private int width;          /*!< latimea ferestrei in pixeli*/
    private int height;         /*!< inaltimea ferestrei in pixeli*/



    /*! \fn Display(String title, int width, int height)
            \brief Constructorul cu parametri al clasei Display

            Retine proprietatile ferestrei proprietatile (titlu, latime, inaltime)
            in variabilele membre deoarece vor fi necesare pe parcursul jocului.

            \param title Titlul ferestrei.
            \param width Latimea ferestrei in pixeli.
            \param height Inaltimea ferestrei in pixeli.
         */
    public Display(String title, int width, int height)
    {
        this.title=title;
        this.width=width;
        this.height=height;
        frame=null;

        CreateDisplay();

    }

    /*! \fn private void CreateDisplay()
        \brief Construieste/creaza fereastra si seteaza toate proprietatile
        necesare: dimensiuni, pozitionare in centrul ecranului, operatia de
        inchidere, invalideaza redimensionarea ferestrei, afiseaza fereastra.

     */
    private void CreateDisplay(){

            /// Daca fereastra a mai fost construita intr-un apel anterior
            /// se renunta la apel
        if (frame!=null)
        {
            return;
        }
            /// Aloca memorie pentru obiectul de tip fereastra si seteaza denumirea
            /// ce apare in bara de titlu
        frame=new JFrame(title);
            /// Seteaza dimensiunile ferestrei in pixeli
        frame.setSize(width,height);
            /// Operatia de inchidere (fereastra sa poata fi inchisa atunci cand
            /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
            /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
            /// program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
            /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
            /// care trebuie setata aceasta proprietate
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.GRAY);




        frame.setFocusable(true);
        if(frame.isVisible())
        {
            frame.requestFocusInWindow();
        }



        canvas=new Canvas();




        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));





        frame.add(canvas);


            /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
            /// ca tot ce contine sa poate fi afisat complet
        frame.pack();




    }


    /*! \fn public Canvas GetCanvas()
       \brief Returneaza "panza" pe care se deseneaza
   */
    public Canvas GetCanvas()
    {
        return canvas;
    }



    /*! \fn public JFrame GetFrame()
       \brief Returneaza fereastra jocului.
   */
    public JFrame GetFrame()
    {
        return frame;
    }

}
