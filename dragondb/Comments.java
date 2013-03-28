import java.io.*;
import java.util.*;
public class Comments
{
    public static void addComment(String name, String commentText)
    {
        File f = new File("comments.txt");
        FileWriter fw = new FileWriter(f);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        fw.write(df.format(new Date()) + "###" + name + "###" + commentText + "%%%\n");
    }
    public static int getAmountOfComments()
    {
        int result = 0;
        File f = new File("comments.txt");
        Scanner sc = new Scanner(f);
        while(true)
        {
            try
            {
                if(sc.nextLine().find("%%%") != -1) result++;
            }
            catch(Exception e)
            {
                break;
            }
        }
        return result;
    }
    public static String[][] getComments()
    {
        String completeText;
        String[][] result = new String[getAmountOfComments()][3];
        File f = new File("comments.txt");
        Scanner sc = new Scanner(f);
        while(true)
        {
            try
            {
                completeText += sc.nextLine();
            }
            catch(Exception e)
            {
                break;
            }
        }
        String[] comments = completeText.split("%%%");
        int i=0;
        for(String str : comments)
        {
            String[] fields = str.split("###");
            for(int j=0;j<3;j++)
            {
                result[i][j]=fields[j];
            }
            i++;
        }
        return result;
    }
}
