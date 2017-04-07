<header id="header">
    <h1>
            <a href="home">
                <span class="thin blue no-background"><i class="fa fa-bars"></i> my<span class="bold">Thoughts</span>
                </span>
            </a>
        </h1>
    <nav class="links">
        <ul>
            <li><a href="user.html">Welcome, ${user.getFirstname()}</a></li>
            <li><a href="karma.html"><span class="green no-background">${user.getKarma()}<span class="bold">k</span></span></a></li>
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
