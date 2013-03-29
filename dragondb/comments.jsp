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
      <textarea name="Comments" rows=6 cols=38>
      <input type="submit" value="Comment">
    </form>
    <br />
    <%! private static String name = request.getParameter("Name"); %>
    <%! private static String comment = request.getParameter("Comment"); %>
    <% Comments.addComment(name,comment); %>
    <%! private static String[][] comments = Comments.getComments(); %>
    <%! private static int count = 0; %>
    <% for(String[] strs : comments) { %>
    <!-- strs[0] is the date; strs[1] is the name;  strs[2] is the comment. -->
    <p>On <%= strs[0] %>, <%= strs[1] %> wrote:<br><%= strs[2] %></p>
  </body>
</html>
