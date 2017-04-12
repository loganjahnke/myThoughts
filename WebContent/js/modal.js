$("#login_trigger").leanModal({ top: 200, overlay: 0.6, closeButton: ".modal_close" });
$("#register_trigger").leanModal({ top: 200, overlay: 0.6, closeButton: ".modal_close" });

$(function() {
    // Calling Login Form
    $("#login_form").click(function() {
        $(".social_login").hide();
        $(".user_login").show();
        return false;
    });

    // Calling Register Form
    $("#register_form").click(function() {
        $(".social_login").hide();
        $(".user_register").show();
        $(".header_title").text('Register');
        return false;
    });

    // Going back to Social Forms
    $(".back_btn").click(function() {
        $(".user_login").hide();
        $(".user_register").hide();
        $(".social_login").show();
        $(".header_title").text('Login');
        return false;
    });

    $("#login_button").click(function() {
        $.post("login", {
            username: $("#username-login").val(),
            password: $("#password-login").val()
        }).done(function(responseText) {
            if (responseText == "good") {
                document.login_form.submit();
            } else if (responseText == "bad") {
                $("username-login").addClass("error-input");
                $("password-login").addClass("error-input");
                $("#error-login").text("Uh-oh... recheck username and password.");
            }
        });
    });

    $("#register_button").click(function() {
        $.post("register", {
            email: $("#email-register").val(),
            username: $("#username-register").val()
        }).done(function(responseText) {
            if (responseText == "good") {
                document.register_form.submit();
            } else if (responseText == "username") {
                $("#username-register").addClass("error-input");
                $("#error-register").text("That username is already in use!");
            } else if (responseText == "email") {
                $("#email-register").addClass("error-input");
                $("#error-register").text("That email is already in use!");
            } else if (responseText == "usernameemail" || responseText == "emailusername") {
                $("#email-register").addClass("error-input");
                $("#username-register").addClass("error-input");
                $("#error-register").text("That email and username are already in use!");
            }
        });
    });
})
