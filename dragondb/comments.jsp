<html>
  <head>
    <title>Comments</title>
  </head>
  <body>
    <h1>Comments</h1>
    <p>Feel free to comment on Dragon Database here.</p>
    <form action="comments.jsp" method="post">
      Enter your name or programmer's alias:&nbsp;
      <input type="text" name="Name">
      <br /><br />
      <textarea name="Comments" rows=6 cols=38></textarea>
      <input type="submit" value="Comment">
    </form>
    <br />
    <%! private static String name = request.getParameter("Name"); %>
    <%! private static String comment = request.getParameter("Comment"); %>
    <%! public static void addComment(String name, String commentText)
    {
        File f = new File("comments.txt");
        FileWriter fw = new FileWriter(f);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        fw.write(df.format(new Date()) + "###" + name + "###" + commentText + "%%%\n");
    } %>
    <%! public static int getAmountOfComments()
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
    } %>
    <%! public static String[][] getComments()
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
    } %>
    <% addComment(name,comment); %>
    <%! private static String[][] comments = getComments(); %>
    <%! private static int count = 0; %>
    <% for(String[] strs : comments) { %>
    <!-- strs[0] is the date; strs[1] is the name;  strs[2] is the comment. -->
    <p>On <%= strs[0] %>, <%= strs[1] %> wrote:<br><%= strs[2] %></p>
  </body>
</html>
