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
	        <button class="mt-button-tiny gray" type="submit" name="commentedTopics">See topics you commented on</button>
	        </#if>
	      </form>
	       
	
	    
	    <#if wrongPassword??>
	    <div id= "pswdMsg">
	    	<#if wrongPassword>
	    		Incorrect current password, try again
	    	<#else>
	    		Password successfully changed
	    	</#if>
	    </div>
	    </#if>
	    
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
					<#if user.getCreatedDate()?? >
						<th >
					
						Created Date
						</th>
						
					</#if>
					<th>Karma</th>

		
					<tr>
						
						
						<td > ${user.getFirstname()}</td>
						<td > ${user.getLastname()}</td>
						<td > ${user.getUsername()}</td>
						<td > ${user.getEmail()}</td>
						<#if user.getCreatedDate()?? >
							<td > ${user.getCreatedDate()}</td>
						</#if>
						<td > ${user.getKarma()}</td>
					</tr>
		
		
			</table>
			
			<button class="mt-button-tiny gray" onclick= "showPswdForm() ">Change Your Password</button>
			
			 <div id="changePassword">
			 <p>
		    	<form action="user" method="post">
		    		Current Password: <input type="password" name="oldPassword">
		    		New Password: <input type="password" name="newPassword">
		    		<input type="submit" name="changePswd" value="Change Password">
		    	</form>
	    	</p>
	    	 
	    	</div>
	    	<#include "include/footer.ftl">
	    
      </div> 

    
    
    
    
</body>

</html>


        