<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>${user.getFirstname()} ${user.getLastname()}</title>
    <!-- Styling -->
    <#include "include/style.ftl">
    <link rel="stylesheet" href="css/registered-index.css">
    <link rel="stylesheet" href="css/modal.css">
    <link rel="stylesheet" href="css/user.css">
    <!-- Scripts -->
    <#include "include/script.ftl">
    <script type="text/javascript" src="js/changePassword.js"></script>
</head>

<body>
    <#include "include/header.ftl">

    
   
        <div id ="content">
          <form action="user" method="post">
          	<#if nonadmin>
	   		<button class="mt-button-tiny gray" type="submit" name="createdTopics">See topics you created</button>
	   		</#if>

	   		<button class="mt-button-tiny gray" type="submit" name="changePswd">Change Your Password</button>
	   		
	   		<#if nonadmin>
	        <button class="mt-button-tiny gray" type="submit" name="commentedTopics">See topics you commented on</button>
	        </#if>
	    </form>
	    
	    <table class="datatable">
	
					<!--Prints out the columns as table heads -->

					<th >
						First Name
					</th>
					<th >
						Last Name
					</th>
					<th >
						User Name
					</th>
					<th >
						Email
					</th>
					<th >
						Created Date
					</th>

		
					<tr>
						
						
						<td > ${user.getFirstname()}</td>
						<td > ${user.getLastname()}</td>
						<td > ${user.getUsername()}</td>
						<td > ${user.getEmail()}</td>
						<td > ${user.getCreatedDate()}</td>
					</tr>
		
		
			</table>
      </div> 
    
	
	
	
    
    <#include "include/footer.ftl">
    
    
</body>

</html>


        