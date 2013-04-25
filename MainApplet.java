import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.regex.*;
class Score
{
    public static int score=0;
    public static final int scoreToWin=115;
}
public class MainApplet extends JApplet
{
    public String username;
    public void init()
    {
        username = JOptionPane.showInputDialog("Enter your name or programmer's alias here...\n\n"
            + "Entrez votre nom ou pseudonyme ici...");
        JOptionPane.showMessageDialog(null,"A group of computer hackers are attacking the secret government computer "
            + "known as X_0358.\nIt is up to you, " + username + ", to save the world from these hackers!\n\n"
            + "Une groupe de pirates informatiques attaquent l\' ordinateur secret du gouvernment "
            + "qui s\'appelle X_0358.\nC\'est a vous, " + username + " de sauver le monde de ces pirates informatiques!",
            "WARNING/AVERTISSEMENT",JOptionPane.WARNING_MESSAGE);
        Question q = Question.getQuestion();
        while(q!=null)
        {
            q.ask();
            q=Question.getQuestion();
        }
        if(Score.score >= Score.scoreToWin)
        {
            JOptionPane.showMessageDialog(null,"Congratulations, " + username + "! Vous avez sauve X_0358!");
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Desole, mais vouz n'avez pas sauve X_0358.\nEh bien. Bonne chance le prochaine fois!");
        }
    }
}
class Question
{
    public int difficulty;
    public String text;
    public String rightAnswer;
    public static Question[] questions = { new Question(1,"Qu'est-ce-que le mot \"ordinateur\" va dire?","computer"),
        new Question(1,"Comment est-ce qu'on dit \"cellphone\" en francais?","telephone mobile"),
        new Question(1,"Qu'est-ce-que le mot \"courriel\" va dire?","email"),
        new Question(1,"Traduire cette phrase: \"Cette programme ne marche pas!\"","This program does not work!"),
        new Question(1,"Comment est-ce qu'on dit cette phrase en francais: \"I bought a computer yesterday.\"",
            "[Jj]'?ai achete un ordinateur hier.?"),
        new Question(2,"Comment est-ce qu'on dit \"spam [email]\" en francais?","polluriel"),
        new Question(2,"Qu'est-ce que le mot \"pirater\" va dire?","([Tt]o )?[Hh]ack"),
        new Question(2,"Comment est-ce qu'on dit \"download\" en francais?","[Tt]elecharger"),
        new Question(2,"Traduire la phrase prochaine: \"Quelqu'un a pirate mon ordinateur, et maintenant je ne peux pas voire mon courriel.\"",
            "[Ss]ome((one)|(body)) hacked my computer,? and now [Ii] can(')?t see my email(.)?"),
        new Question(2,"Comment est-ce qu'on dit cette phrase en francais: \"I received a lot of spam yesterday.\"",
            "[Jj]'?ai recu beaucoup d[ue] polluriel hier(.)?"),
        new Question(2,"Qu'est-que c'est \"mobile device\" en francais?","([Uu]n )?appareil mobile"),
        new Question(3,"Qu'est-ce que \"un appareil mobile\" va dire?","([Aa] )?[Mm]obile [Dd]evice"),
        new Question(3,"Comment est-ce qu'on dit \"microchips\" en francais?","[Mm]icropuces"),
        new Question(3,"Traduire la phrase prochaine: \"Apres que j'ai telercharge cette programme, j'ai recu beaucoup de polluriel. Je pense que quelqu'un a pirate mon ordinateur.\"",
            "[Aa]fter [Ii] downloaded th((is)|(e)) program, I received a lot of spam. I think some((one)|(body)) hacked my computer(.)?"),
        new Question(3,"Comment est-ce qu'on dit cette phrase en francais: \"A computer hacker hacked a secret computer and stole a lot of information.\"",
            "[Uu]n pirate informatique a pirate un ordinateur secret et vole(beaucoup)? d[' ]?information."),
        new Question(3,"Traduire la phrase prochaine:\n\"I will download this new cool new game!",
            "[Jj]e vais telecharger un nouveau jeu fantastique!?"),
        new Question(3,"Traduire la phrase prochaine:\n\"Using a cellphone to play video games is stupid.\"",
            "[Uu]tiliser un telephone ((cellulaire)|(mobile)) pour jouer les jeux videos? est stupid(e)?(.)?"),
        new Question(3,"Qu'est-ce que \"un ecran tactile\" va dire?","([Aa] )? [Tt]ouch[ -]?[Ss]creen"),
        new Question(9001,"Traduire la phrase prochaine: \"ALEX IS AMAZING!!!\"",
            "ALEX EST ((SUPERSUPER)|(FANTASTIQUE)|(SUPER))(!){3,}")};
    public boolean isAlreadyAsked;
    public String[] rightMessages = {"Correct!","Super!","Fantastique!","C'est correcte!","Vous avez raison!",
        "Tres bien!"};
    public int rightMsgCount=rightMessages.length;
    public String[] wrongMessages = {"Vous avez tord!","Ce n'est pas correct!","Incorrect!",
        "ERREUR 02- L'utilisateur a fait une faute."};
    public int wrongMsgCount=wrongMessages.length;
    public Question(int difflevel, String text_, String rightAnswer_)
    {
        difficulty = difflevel;
        text = text_;
        rightAnswer = rightAnswer_;
    }
    public static Question getQuestion()
    {
        for(Question q : questions)
        {
            if(!q.isAlreadyAsked) return q;
        }
        return null;
    }
    public void ask()
    {
        String msg = "Niveau ";
        msg += (difficulty > 9000) ? "PLUS QUE 9000!!!\n" : (difficulty+"\n");
        msg += text;
        String answer = JOptionPane.showInputDialog(null,msg);
        try
        {
            if(Pattern.matches(rightAnswer, answer))
            {
                JOptionPane.showMessageDialog(null,rightMessages[(int)Math.random()*rightMsgCount]);
                Score.score += difficulty>9000 ? 0 : 3*difficulty;
            }
            else
            {
                JOptionPane.showMessageDialog(null,rightMessages[(int)Math.random()*wrongMsgCount]);
                Score.score -= difficulty>9000 ? 0 : 15-(3*difficulty);
            }
        }
        catch(Exception e)
        {}
        finally
        {
            isAlreadyAsked = true;
        }
    }
}
