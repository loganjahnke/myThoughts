<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>myThoughts</title>
    <!-- Styling -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="myThoughts.css">
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
                <li><a id="login_trigger" href="#modal">Sign <span class="bold">in</span> | Sign <span class="bold">up</a></li>
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
    <div id="topicsMenu">
        <ul>
            <li class="blue"><a href="">Politics</a></li>
            <li class="green"><a href="">Environmental</a></li>
            <li class="red"><a href="">Religion</a></li>
            <li class="grey"><a href="categories.html">Categories</a></li>
            <li class="red"><a href="">Recent</a></li>
            <li class="green"><a href="">Featured</a></li>
            <li class="blue"><a href="">Trending</a></li>
        </ul>
    </div>
    <div id="debate">
        <h1> Winter is the best Season</h1>
    </div>
    <div id="wrapper">
        <p>
            What is <span class="bold"> myThoughts</span>
        </p>
        <div id="yourOpinions">
            <img class="clipart" src="http://www.clipartbest.com/cliparts/4ib/6nB/4ib6nB79T.png" />
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        </div>
        <div id="ourModeration">
            <img class="clipart" src="https://img.clipartfest.com/00923b3316ae11d903d8344caa3a5ce4_available-as-a-print-magnifying-glass-clipart-black-and-white_170-170.jpeg" />
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        </div>
        <div id="allDebate">
            <img class="clipart" src="https://maxcdn.icons8.com/Share/icon/Mobile//sms1600.png" />
            <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        </div>
    </div>
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
                    <input type="text" placeholder="username" name="username" />
                    <br />
                    <input type="password" placeholder="password" name="password" />
                    <br />
                    <div class="action_btns">
                        <div class="one_half"><a href="#" class="btn back_btn gray"><i class="fa fa-angle-double-left"></i> Back</a></div>
                        <div class="one_half last"><a id="login_button" href="#" class="btn green">Login</a></div>
                    </div>
                </form>
                <a href="#" class="forgot_password thin">Forgot password?</a>
            </div>
            <!-- Register Form -->
            <div class="user_register">
                <form name="register_form" method="POST" action="home">
                    <input type="text" placeholder="first name" name="firstname" />
                    <br />
                    <input type="text" placeholder="last name" name="lastname" />
                    <br />
                    <input type="email" placeholder="email" name="email" />
                    <br />
                    <input type="text" placeholder="username" name="username" />
                    <br />
                    <input type="password" placeholder="password" name="password" />
                    <br />
                    <div class="action_btns">
                        <div class="one_half"><a href="#" class="btn back_btn gray"><i class="fa fa-angle-double-left"></i> Back</a></div>
                        <div class="one_half last"><a id="register_button" href="#" class="btn green">Register</a></div>
                    </div>
                </form>
            </div>
        </section>
    </div>
    <script type="text/javascript" src="js/modal.js"></script>
</body>

</html>
