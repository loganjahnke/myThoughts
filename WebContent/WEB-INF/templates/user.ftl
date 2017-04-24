<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>${viewing.getFirstname()} ${viewing.getLastname()}</title>
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
          	<input id="username" name="username" value="${viewing.getUsername()}" hidden />
	   		<button class="mt-button gray" type="submit" name="createdTopics">${viewing.getFirstname()}'s<br/>topics</button>
	        <button class="mt-button gray" type="submit" name="commentedTopics">${viewing.getFirstname()}'s<br/>comments</button>
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
					<#if viewing.getCreatedDate()?? >
						<th >

						Created Date
						</th>

					</#if>
					<#if nonadmin>
						<th>Karma</th>
					</#if>


					<tr>
						<td > ${viewing.getFirstname()}</td>
						<td > ${viewing.getLastname()}</td>
						<td > ${viewing.getUsername()}</td>
						<td > ${viewing.getEmail()}</td>
						<#if viewing.getCreatedDate()??>
							<td > ${viewing.getCreatedDate()}</td>
						</#if>
						<#if nonadmin>
							<td > ${viewing.getKarma()}</td>
						</#if>
					</tr>


			</table>

			<#if mypage>
			<button class="mt-button-tiny gray" onclick= "showPswdForm() ">Change Your Password</button>

			 <div id="changePassword">
			 <p>
		    	<form action="user" method="post">
		    		Current Password: <input type="password" name="oldPassword">
		    		New Password: <input type="password" name="newPassword">
		    		<input type="submit" name="changePswd" value="Change Password">
		    	</form>
	    	</p>
	    	</#if>
	    	</div>


      </div>

   	<#include "include/footer.ftl">




</body>

</html>


