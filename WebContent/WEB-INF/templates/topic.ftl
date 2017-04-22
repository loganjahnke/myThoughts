<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>${topic.getTitle()}</title>
	<!-- Styling -->
	<#include "include/style.ftl">
	<link rel="stylesheet" href="css/topic.css">
	<!-- Scripts -->
	<#include "include/script.ftl">
	<script src="js/topic.js"></script>
</head>

<body>
	<#include "include/header.ftl">
	<div id="topicsMenu">
        <ul>
            <li class="blue"><a href="topics?category=Politics">Politics</a></li>
            <li class="green"><a href="topics?category=Environmental">Environmental</a></li>
            <li class="red"><a href="topics?category=Religion">Religion</a></li>
            <li class="grey"><a href="categories?">Categories</a></li>
            <li class="red"><a href="topics?category=Recent">Recent</a></li>
            <li class="green"><a href="topics?category=Featured">Featured</a></li>
            <li class="blue"><a href="topics?category=Trending">Trending</a></li>
        </ul>
    </div>
    <div id="container">
	    <div id="topic">
	    	<h1 class="bold">${topic.getTitle()}</h1>
	    	<div id="voteCount">
	    		<#if !visitor && nonadmin && user.doesLike(topic)>
	                <span class="bold green no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="upvote(${topic.getId()})"><i class="fa fa-caret-up"></i></a></span>
	                <span id="count-${topic.getId()}">${topic.getVote()}</span>
	                <span class="no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
	            <#elseif !visitor && nonadmin && user.doesDislike(topic)>
	                <span class="no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="upvote(${topic.getId()})"><i class="fa fa-caret-up"></i></a></span>
	                <span id="count-${topic.getId()}">${topic.getVote()}</span>
	                <span class="bold red no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
	            <#elseif !visitor && !nonadmin>
	                <span class="no-background" id="upvote-${topic.getId()}"><a><i class="fa fa-caret-up"></i></a></span>
	                <span id="count-${topic.getId()}">${topic.getVote()}</span>
	                <span class="no-background" id="downvote-${topic.getId()}"><a><i class="fa fa-caret-down"></i></a></span>
	            <#elseif !visitor>
	                <span class="no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="upvote(${topic.getId()})"><i class="fa fa-caret-up"></i></a></span>
	                <span id="count-${topic.getId()}">${topic.getVote()}</span>
	                <span class="no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
	            <#else>
	                <span class="no-background" id="upvote-${topic.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-up"></i></a></span>
	                <span id="count-${topic.getId()}">${topic.getVote()}</span>
	                <span class="no-background" id="downvote-${topic.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-down"></i></a></span>
	            </#if>
	    	</div>
	    	<h3 class="thin">${topic.getDescription()}</h3>
	    	<div id="topicFooter">
	    		<ul id="topic-categories">
		    		<#list topic.getCategories() as category>
		    			<li id="category" class="${category.getColor()}">${category.getName()}</li>	
		    		</#list>
		    	</ul>
		    	<span class="button" id="agreebtn"><a href="" onclick="agree(${topic.getId()})">Agree</a></span>
		    	<span class="button" id="disagreebtn"><a href="" onclick="disagree(${topic.getId()})">Disagree</a></span>
		    	<a class="no-decoration" id="user" href="user?username=${topic.getUser().getUsername()}">${topic.getUser().getUsername()}</a>
	            |
	            <span class="green no-background">${topic.getUser().getKarma()}<span class="bold">k</span></span>
	    	</div>
	    </div>
    </div>
    <div id="comments">
    	<#list comments as comment>
    		<#if comment.doeAgreeWithTopic(topic.getId())>
    			<div class="comment" id="agreeComment">
    				<h5 class="bold">${comment.getSubject()}</h5>
    				<p>${comment.getArgument()}</p>
    				<div id="voteCount">
			    		<#if !visitor && nonadmin && user.doesLike(comment)>
			                <span class="bold green no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="upvote(${comment.getId()})"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="downvote(${comment.getId()})"><i class="fa fa-caret-down"></i></a></span>
			            <#elseif !visitor && nonadmin && user.doesDislike(comment)>
			                <span class="no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="upvote(${comment.getId()})"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="bold red no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="downvote(${comment.getId()})"><i class="fa fa-caret-down"></i></a></span>
			            <#elseif !visitor && !nonadmin>
			                <span class="no-background" id="upvote-${comment.getId()}"><a><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a><i class="fa fa-caret-down"></i></a></span>
			            <#elseif !visitor>
			                <span class="no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="upvote(${comment.getId()})"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
			            <#else>
			                <span class="no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-down"></i></a></span>
			            </#if>
    				</div>
    				<div id="commentFooter">
    					<a class="no-decoration" href="user?username=${comment.getUser().getUsername()}">${comment.getUser().getUsername()}</a>
			            |
			            <span class="green no-background">${comment.getUser().getKarma()}<span class="bold">k</span></span>
			            <a href="" onclick="agree(${comment.getId()})"><span class="button" id="agreebtn">Agree</span></a>
	    				<a href="" onclick="disagree(${comment.getId()})"><span class="button" id="disagreebtn">Disagree</span></a>
    				</div>
    			</div>
    		<#else>
    			<div class="comment" id="disagreeComment">
    				<h5 class="bold">${comment.getSubject()}</h5>
    				<p>${comment.getArgument()}</p>
    				<div id="voteCount">
			    		<#if !visitor && nonadmin && user.doesLike(comment)>
			                <span class="bold green no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="upvote(${comment.getId()})"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="downvote(${comment.getId()})"><i class="fa fa-caret-down"></i></a></span>
			            <#elseif !visitor && nonadmin && user.doesDislike(comment)>
			                <span class="no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="upvote(${comment.getId()})"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="bold red no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="downvote(${comment.getId()})"><i class="fa fa-caret-down"></i></a></span>
			            <#elseif !visitor && !nonadmin>
			                <span class="no-background" id="upvote-${comment.getId()}"><a><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a><i class="fa fa-caret-down"></i></a></span>
			            <#elseif !visitor>
			                <span class="no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="upvote(${comment.getId()})"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="downvote(${topic.getId()})"><i class="fa fa-caret-down"></i></a></span>
			            <#else>
			                <span class="no-background" id="upvote-${comment.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-up"></i></a></span>
			                <span id="count-${comment.getId()}">${comment.getVote()}</span>
			                <span class="no-background" id="downvote-${comment.getId()}"><a class="fake-link" onclick="requestLogin()"><i class="fa fa-caret-down"></i></a></span>
			            </#if>
    				</div>
    				<div id="commentFooter">
    					<a class="no-decoration" href="user?username=${comment.getUser().getUsername()}">${comment.getUser().getUsername()}</a>
			            |
			            <span class="green no-background">${comment.getUser().getKarma()}<span class="bold">k</span></span>
			            <span class="button" id="agreebtn"><a href="" onclick="agree(${comment.getId()})">Agree</a></span>
	    				<span class="button" id="disagreebtn"><a href="" onclick="disagree(${comment.getId()})">Disagree</a></span>
    				</div>
    			</div>
    			
    		</#if>
    	</#list>
    </div>
    <#include "include/footer.ftl">
</body>
</html>