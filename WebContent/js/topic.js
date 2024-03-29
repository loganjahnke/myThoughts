function upvote(id) {
    var name = "upvote-" + id;
    if (!document.getElementById(name).classList.contains('green')) {
        $.post("feature", {
            id: id,
            do: "upvote"
        }).done(function(responseText) {
            if (document.getElementById("downvote-" + id).classList.contains('red')) {
                document.getElementById("count-" + id).innerHTML -= -2;
            } else {
                document.getElementById("count-" + id).innerHTML -= -1;
            }
            document.getElementById(name).classList.add('green');
            document.getElementById(name).classList.add('bold');
            document.getElementById("downvote-" + id).classList.remove('red');
            document.getElementById("downvote-" + id).classList.remove('bold');
        });
    }
}

function downvote(id) {
    var name = "downvote-" + id;
    if (!document.getElementById(name).classList.contains('red')) {
        $.post("feature", {
            id: id,
            do: "downvote"
        }).done(function(responseText) {
            if (document.getElementById("upvote-" + id).classList.contains('green')) {
                document.getElementById("count-" + id).innerHTML -= 2;
            } else {
                document.getElementById("count-" + id).innerHTML -= 1;
            }
            document.getElementById("upvote-" + id).classList.remove('green');
            document.getElementById("upvote-" + id).classList.remove('bold');
            document.getElementById(name).classList.add('red');
            document.getElementById(name).classList.add('bold');
        });
    }
}

function agree(id) {
    if (!document.getElementById("topic").classList.contains('green')) {
        $.post("feature", {
            id: id,
            do: "agreeTopic"
        }).done(function(responseText) {
            document.getElementById("topic").classList.remove('red');
            document.getElementById("topic").classList.remove('grey');
            document.getElementById("topic").classList.remove('gray');
            document.getElementById("topic").classList.remove('light-grey');
            document.getElementById("topic").classList.add('green');
        });
    }
}

function disagree(id) {
    if (!document.getElementById("topic").classList.contains('red')) {
        $.post("feature", {
            id: id,
            do: "disagreeTopic"
        }).done(function(responseText) {
            document.getElementById("topic").classList.remove('green');
            document.getElementById("topic").classList.remove('grey');
            document.getElementById("topic").classList.remove('gray');
            document.getElementById("topic").classList.remove('light-grey');
            document.getElementById("topic").classList.add('red');
        });
    }
}

function addComment(id, agree) {
    $.post("feature", {
        id: id,
        subj: document.getElementById("commentSubject").value,
        arg: document.getElementById("commentArgument").value,
        agree: agree,
        do: "addComment"
    }).done(function(responseText) {
        location.reload();
        if(agree == true) {
            var newDiv = document.getElementById("agreeComment");
            newDiv.innerHTML = "";
            var subject = "<h5 class = 'bold'>" + document.getElementById("commentSubject") + "</h5>";
            var argument = "<p>" + document.getElementById("commentArgument") + "</p>";
            var vote = document.getElementById("voteCount");
            var footer = document.getElementById("commentFooter");
            $newDiv.append(subject, argument, vote, footer);
            document.getElementById("agreeComments").append($newDiv);
        }
        else {
        	var $newDiv = document.getElementById("disagreeComment");

        }
    });
}
