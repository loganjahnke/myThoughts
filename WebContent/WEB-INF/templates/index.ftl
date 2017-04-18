<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>myThoughts</title>
    <!-- Styling -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/landing.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/modal.css">
    <!-- Scripts -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
    <script type="text/javascript" src="js/skel.min.js"></script>
    <script type="text/javascript" src="js/header.js"></script>
</head>

<body>
    <header id="header">
        <h1>
            <a href="index.html">
                <span class="thin blue no-background"><i class="fa fa-bars"></i> my<span class="bold">Thoughts</span>
                </span>
            </a>
        </h1>
        <nav class="links">
            <ul>
                <li><a id="login_trigger" href="#modal">Sign <span class="bold">in</span> | Sign <span class="bold">up</span></a></li>
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
    <div id="topicsMenu">
        <ul>
            <li class="blue"><a href="topics?category=Politics">Politics</a></li>
            <li class="green"><a href="topics?category=Environmental">Environmental</a></li>
            <li class="red"><a href="topics?category=Religion">Religion</a></li>
            <li class="grey"><a href="categories">Categories</a></li>
            <li class="red"><a href="topics?category=Recent">Recent</a></li>
            <li class="green"><a href="topics?category=Featured">Featured</a></li>
            <li class="blue"><a href="topics?category=Trending">Trending</a></li>
        </ul>
    </div>
    <div id="debate"></div>
    <h1 id="welcome">Welcome to <span class="thin">my</span><span class="bold">Thoughts</span></h1>
    <ul id="category-list">
        <li>
            <a class="fake-link">
                <i class="fa fa-user-circle red"></i>
                <h3><span class="thin">your</span><span class="bold">Opinions</span></h3>
            </a>
            <p>Every person has their own unique outlook the world, informed by their experiences, personality, and desires. Here at myThoughts, we believe that every individual has a right to express that opinion. We all have something to contribute to the conversation and something to learn from each other. Remember to respect other people’s opinions as you would have them respect yours.</p>
        </li>
        <li>
            <a class="fake-link">
                <i class="fa fa-search blue"></i>
                <h3><span class="thin">our</span><span class="bold">Moderation</span></h3>
            </a>
            <p>We all want to be right and defend our points of view. While passionate debate is encouraged, however, arguments should not spiral into ad hominem attacks and unproductive squabbling. We the administrators of myThoughts, in order to protect the integrity of this forum and the security of our users, reserve the right to manage and moderate all debate, including the removal of offensive posts and comments.</p>
        </li>
        <li>
            <a class="fake-link">
                <i class="fa fa-gavel green"></i>
                <h3><span class="thin">all</span><span class="bold">Debate</span></h3>
            </a>
            <p>myThoughts will not discriminate among topics. We believe that knowledge and discourse is valuable on all levels, from “Which Star Wars movie is the best?” to “How can the US take a firm stand against the Syrian government without risking armed conflict with Russia?” Feel free to post any topic that crosses your mind, and you may find that someone else is anxious to engage.</p>
        </li>
    </ul>
    <!-- Login and Register Popup -->
    <div id="modal" class="popupContainer" style="display:none;">
        <header class="popupHeader blue no-background">
            <i class="fa fa-bars"></i> <span class="thin">my</span><span class="bold">Thoughts</span>
            <span class="modal_close"><i class="fa fa-times"></i></span>
        </header>
        <section class="popupBody">
            <!-- Social Login -->
            <div class="social_login">
                <div class="action_btns">
                    <div class="one_half"><a href="#" id="login_form" class="btn gray">Login</a></div>
                    <div class="one_half last"><a href="#" id="register_form" class="btn gray">Sign up</a></div>
                </div>
            </div>
            <!-- Username & Password Login form -->
            <div class="user_login">
                <form name="login_form" method="POST" action="home">
                    <input id="username-login" type="text" placeholder="username" name="username" />
                    <br />
                    <input id="password-login" type="password" placeholder="password" name="password" />
                    <br />
                    <div class="action_btns">
                        <div class="one_half"><a href="#" class="btn back_btn gray"><i class="fa fa-angle-double-left"></i> Back</a></div>
                        <div class="one_half last"><a id="login_button" href="#" class="btn green">Login</a></div>
                    </div>
                </form>
                <a href="#" class="forgot_password thin">Forgot password?</a>
                <a id="error-login" href="#" class="error-message thin"></a>
            </div>
            <!-- Register Form -->
            <div class="user_register">
                <form name="register_form" method="POST" action="home">
                    <input type="text" placeholder="first name" name="firstname" />
                    <br />
                    <input type="text" placeholder="last name" name="lastname" />
                    <br />
                    <input id="email-register" type="email" placeholder="email" name="email" />
                    <br />
                    <input id="username-register" type="text" placeholder="username" name="username" />
                    <br />
                    <input type="password" placeholder="password" name="password" />
                    <br />
                    <div class="action_btns">
                        <div class="one_half"><a href="#" class="btn back_btn gray"><i class="fa fa-angle-double-left"></i> Back</a></div>
                        <div class="one_half last"><a id="register_button" href="#" class="btn green">Register</a></div>
                    </div>
                </form>
                <a id="error-register" href="#" class="error-message thin"></a>
            </div>
        </section>
    </div>
    <script type="text/javascript" src="js/modal.js"></script>
</body>

</html>
