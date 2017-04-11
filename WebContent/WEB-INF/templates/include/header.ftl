<header id="header">
    <h1>
            <a href="home">
                <span class="thin blue no-background"><i class="fa fa-bars"></i> my<span class="bold">Thoughts</span>
                </span>
            </a>
        </h1>
    <nav class="links">
        <ul>
            <#if visitor == false>
              <form action ="" method ="post"> 
                <li><a href="user.html">Welcome, ${user.getFirstname()}</a></li>
                <#if nonadmin == true>
                    <li><a href="karma.html"><span class="green no-background">${user.getKarma()}<span class="bold">k</span></span></a></li>
                 	<li><a href="createpost">Create a Post</a></li>
                </#if>
                <li><a href="logout">Sign Out</a></li>
              </form>          

            <#else>
            	
                <ul>
                    <li><a id="login_trigger" href="index.html">Sign <span class="bold">in</span> | Sign <span class="bold">up</span></a></li>
                </ul>
            </#if>
        </ul>
    </nav>
    <nav class="main">
        <ul>
            <li class="search">
                <a class="fa-search" href="#search">Search</a>
                <form id="search" method="get" action="#">
                    <input type="text" name="query" placeholder="Search" />
                </form>
            </li>
        </ul>
    </nav>
</header>
