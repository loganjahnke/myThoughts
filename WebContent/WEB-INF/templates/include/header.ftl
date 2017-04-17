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
                <li><a href="user.html">Welcome, ${user.getFirstname()}</a></li>
                <#if nonadmin == true>
                    <li><a href="karma.html"><span class="green no-background">${user.getKarma()}<span class="bold">k</span></span></a></li>
                 	<li><a href="create-topic">Create a Post</a></li>
                </#if>
                <li><a href="logout">Sign Out</a></li>

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
                <form id="search" method="GET" action="search">
                    <input type="text" name="query" placeholder="Search" />
                </form>
            </li>
        </ul>
    </nav>
</header>
